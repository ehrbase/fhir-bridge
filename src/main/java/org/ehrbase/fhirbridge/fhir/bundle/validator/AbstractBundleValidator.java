package org.ehrbase.fhirbridge.fhir.bundle.validator;

import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.fhirbridge.fhir.support.Resources;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("java:S6212")
public abstract class AbstractBundleValidator implements FhirTransactionValidator {

    @Override
    public void validateRequest(Object payload, Map<String, Object> parameters) {
        Bundle bundle = (Bundle) payload;

        // validateBundleType(bundle);
        MemberValidator memberValidator = new MemberValidator();
        validateEqualPatientIds(bundle);

        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            setUrlMemberLists(entry, memberValidator);
            checkForClientIds(entry);//has to be after setUrlMemberList because of fullUrl validation
            if(bundle.getType()!= Bundle.BundleType.DOCUMENT){
                validateRequestElement(entry);
            }
        }
        memberValidator.validateMembers();
    }

    void checkForClientIds(Bundle.BundleEntryComponent entry) {
        if (entry.getResource().getId().startsWith("Observation/")) { // Since the id is the FullURL if there is no resource.id, we have to check for Observation/. This is because if there is a resource.id, an Observation/ is attached
            throw new UnprocessableEntityException("Ids assigned by the client are not supported by the Fhir bridge. Please delete the resource.id " + entry.getResource().getId().replace("Observation/", ""));
        }
    }

    void setUrlMemberLists(Bundle.BundleEntryComponent entry, MemberValidator memberValidator) {
        memberValidator.addFullUrls(entry.getFullUrl());
        if (isObservation(entry)) {
            Observation observation = (Observation) entry.getResource();
            if (!observation.getHasMember().isEmpty()) {
                memberValidator.setHasMembersList(observation.getHasMember());
                memberValidator.deleteFullUrl(entry.getFullUrl()); //Since the Observation is not a Member of itself
            }
        }
    }

    void validateEqualPatientIds(Bundle bundle) {
        Optional<Identifier> subject = Optional.empty();

        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            Identifier current = Resources.getSubject(entry.getResource())
                    .map(Reference::getIdentifier)
                    .orElseThrow(() -> new UnprocessableEntityException("Ensure that the subject id has the following format :         " +
                            "\"subject\": {\n" +
                            "          \"identifier\": {\n" +
                            "            \"system\": \"urn:ietf:rfc:4122\",\n" +
                            "            \"value\": \"example\"\n" +
                            "          }\n" +
                            "        },"));

            if (subject.isPresent() && !StringUtils.equals(subject.get().getValue(), current.getValue())) {
                throw new InternalErrorException("subject.reference ids all have to be equal! A Fhir Bridge Bundle cannot reference to different Patients !");
            }

            subject = Optional.of(current);
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
                throw new UnprocessableEntityException("The request.url and the resourceType of the entry need to be equal!");
            }
        }
    }

    @Override
    public void validateResponse(Object payload, Map<String, Object> parameters) {
        throw new UnsupportedOperationException("Response validation is not supported");
    }

}



