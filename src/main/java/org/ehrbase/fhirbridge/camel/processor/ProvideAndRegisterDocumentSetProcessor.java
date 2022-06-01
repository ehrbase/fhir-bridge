package org.ehrbase.fhirbridge.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Document;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.DocumentEntry;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.SubmissionSet;
import org.openehealth.ipf.commons.ihe.xds.core.requests.builder.ProvideAndRegisterDocumentSetBuilder;

public class ProvideAndRegisterDocumentSetProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        SubmissionSet submissionSet = new SubmissionSet();
        ProvideAndRegisterDocumentSetBuilder provideAndRegisterDocumentSetBuilder = new ProvideAndRegisterDocumentSetBuilder(true, submissionSet);
        Document document = new Document();
        DocumentEntry documentEntry = new DocumentEntry();
        document.setDocumentEntry(documentEntry);
        provideAndRegisterDocumentSetBuilder.withDocument(document);
        provideAndRegisterDocumentSetBuilder.build();
       // provideAndRegisterDocumentSet.setSubmissionSet();

    }
}
