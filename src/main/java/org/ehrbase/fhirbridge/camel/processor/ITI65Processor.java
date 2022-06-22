package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.DataFormatException;
import ca.uhn.fhir.parser.IParser;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.ehrbase.fhirbridge.fhir.support.Bundles;
import org.ehrbase.fhirbridge.ihe.xds.ITITrace;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Binary;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.DocumentManifest;
import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component(ITI65Processor.BEAN_ID)

public class ITI65Processor implements Processor {

    FhirContext ctx = FhirContext.forR4();
    public static final String BEAN_ID = "iti65Processor";

    @Override
    public void process(Exchange exchange) throws Exception {
        Bundle bundle = (Bundle) exchange.getIn().getBody(Resource.class);
        setSaveInput(exchange, bundle);
        for(Bundle.BundleEntryComponent entry: bundle.getEntry()){
            if(entry.getResource().getResourceType().equals(ResourceType.Binary)){
                Binary binary = (Binary) entry.getResource();
                String fhirInstance = new String(binary.getData(), StandardCharsets.UTF_8);   //string with "UTF-8" encoding
                IParser parser = ctx.newJsonParser();
                try{
                    Bundle bundleNew = parser.parseResource(Bundle.class, fhirInstance);
                    exchange.getIn().setBody(bundleNew);
                    exchange.getMessage().setHeader(CamelConstants.PROFILE, Bundles.getTransactionProfile(bundleNew));
                }catch(DataFormatException e){
                    IBaseResource resource = parser.parseResource(fhirInstance);
                    exchange.getIn().setBody(resource);
                    exchange.getMessage().setHeader(CamelConstants.PROFILE, Optional.empty());
                }
            }
        }
    }

    private void setSaveInput(Exchange exchange, Bundle bundle) {
        DocumentManifest documentManifest = null; // cant be null since fhir profile requires it
        DocumentReference documentReference = null; // cant be null since fhir profile requires it
        for(Bundle.BundleEntryComponent entry: bundle.getEntry()){
            if(entry.getResource().getResourceType().equals(ResourceType.DocumentManifest)){
                documentManifest = (DocumentManifest) entry.getResource();
            }else if(entry.getResource().getResourceType().equals(ResourceType.DocumentReference)){
                documentReference = (DocumentReference) entry.getResource();
            }
        }
        ITITrace itiTrace = new ITITrace(documentManifest, documentReference);
        exchange.getIn().setHeader("iti65-trace", itiTrace);
    }
}
