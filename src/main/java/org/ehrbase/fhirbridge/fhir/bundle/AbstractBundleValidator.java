package org.ehrbase.fhirbridge.fhir.bundle;

import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractBundleValidator implements FhirTransactionValidator {


    class MemberValidator {
        List<String> fullUrlList = new ArrayList<>();
        List<String> hasMembersList = new ArrayList<>();

        private void addFullUrls(String fullUrl) {
            addListEntry(this.fullUrlList, fullUrl);
        }

        private void deleteFullUrl(String fullUrl) {
            fullUrlList.remove(fullUrl);
        }

        void addListEntry(List<String> list, String entry){
            if(entry.startsWith("urn:uuid:")){
                list.add(entry);
            }else{
                throw new UnprocessableEntityException(entry + " is not a valid format. The fullUrl has to be an UUID and start with: urn:uuid:, example urn:uuid:04121321-4af5-424c-a0e1-ed3aab1c349d");
            }
        }

        public void setHasMembersList(List<Reference> hasMembersList) {
            if (memberListNotAlreadySet()) {
                for (Reference reference : hasMembersList) {
                    addListEntry(this.hasMembersList, reference.getReference());
                }
            } else {
                throw new UnprocessableEntityException("More then one Observation has the hasMember Attribute, this is not supported by the Fhir-bridge");

            }
        }

        boolean memberListNotAlreadySet() {
            return this.hasMembersList.size() == 0;
        }

        public void validateMembers() {
            if (!fullUrlList.equals(hasMembersList)) {
                System.out.println(fullUrlList);
                System.out.println(hasMembersList);
                throw new UnprocessableEntityException("The urls of the entries within the Bundle are not matching the hasMember.references. Make sure that every Url contained in the observation with hasMembers is contained and correct.");
            }
        }
    }

    @Override
    public void validateRequest(Object payload, Map<String, Object> parameters) {
        Bundle bundle = (Bundle) payload;

        validateBundleType(bundle);
        MemberValidator memberValidator = new MemberValidator();

        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            setUrlMemberLists(entry, memberValidator);
            validateRequestElement(entry);
        }
        memberValidator.validateMembers();
    }

    void setUrlMemberLists(Bundle.BundleEntryComponent entry, MemberValidator memberValidator){
        memberValidator.addFullUrls(entry.getFullUrl());
        if (isObservation(entry)) {
            Observation observation = (Observation) entry.getResource();
            if (observation.getHasMember().size() > 0) {
                memberValidator.setHasMembersList(observation.getHasMember());
                memberValidator.deleteFullUrl(entry.getFullUrl()); //Since the Observation is not a Member of itself
            }
        }
    }

    boolean isObservation(Bundle.BundleEntryComponent entry) {
        return entry.getResource().getResourceType().toString().equals("Observation");
    }

    void validateBundleType(Bundle bundle) {
        if (bundle.getType() != Bundle.BundleType.TRANSACTION) {
            throw new UnprocessableEntityException("The Fhir bridge does currently support only Bundles of the type transaction");
        }
    }

    void validateRequestElement(Bundle.BundleEntryComponent entry) {
        Bundle.BundleEntryRequestComponent request = entry.getRequest();
        validateHttpVerb(request);
        validateUrl(request, entry);
    }

    void validateHttpVerb(Bundle.BundleEntryRequestComponent request) {
        if (request.getMethod() != Bundle.HTTPVerb.POST) {
            throw new UnprocessableEntityException("Only the request method POST is supported !");
        }
    }

    void validateUrl(Bundle.BundleEntryRequestComponent request, Bundle.BundleEntryComponent entry) {
        Resource resource = entry.getResource();

        if (!Objects.equals(request.getUrl(), resource.getResourceType().name())) {
            if (request.getUrl().contains("/")) {
                throw new UnprocessableEntityException("The Fhir bridge does not supports id assignment from the client side, please delete the ids, request url should looke like this example: \"url\":\"Observation\"");
            } else {
                throw new UnprocessableEntityException("The request.url and the resourceType of the entry have to be the same !");
            }
        }
    }


    @Override
    public void validateResponse(Object payload, Map<String, Object> parameters) {
        throw new UnsupportedOperationException("Response validation is not supported");
    }


    private String getEhrUID(Bundle bundle) {
        String ehrUID = "";
        for (Bundle.BundleEntryComponent bundleEntry : bundle.getEntry()) {
            checkIfPatientRessourceNotPresent(bundleEntry);
            Observation observation = (Observation) bundleEntry.getResource();
            ehrUID = getEntryEhrUID(ehrUID, observation);

        }
        return ehrUID;
    }

    private String getEntryEhrUID(String ehrUID, Observation observation) {
        if (ehrUID.equals("") || referencesSameEhrUID(ehrUID, observation)) {
            return observation.getSubject().getReference().split(":")[2];
        }
        throw new InternalErrorException("The subject Ids of the profile within the Bundle reference different Patient. They must refer to one identical Patient!");

    }

    private boolean referencesSameEhrUID(String ehrUID, Observation observation) {
        return ehrUID.equals(observation.getSubject().getReference().split(":")[2]);
    }

    private void checkIfPatientRessourceNotPresent(Bundle.BundleEntryComponent bundle) {
        if (bundle.getResource().getResourceType().toString().equals("Patient")) {
            throw new InternalErrorException("Patient as a resource is not allowed to be contained !");
        }
    }


}
