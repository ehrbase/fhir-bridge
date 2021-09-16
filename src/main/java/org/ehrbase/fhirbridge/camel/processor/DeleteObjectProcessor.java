package org.ehrbase.fhirbridge.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.util.ObjectHelper;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.ehrbase.fhirbridge.minio.MinioService;

public class DeleteObjectProcessor implements Processor {

    public static final String BEAN_ID = "deleteObjectProcessor";

    private final MinioService minioService;

    public DeleteObjectProcessor(MinioService minioService) {
        this.minioService = minioService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        if (ObjectHelper.isNotEmpty(exchange.getIn().getHeader(CamelConstants.MINIO_OBJECT))) {
            String object = exchange.getIn().getHeader(CamelConstants.MINIO_OBJECT, String.class);
            minioService.removeObject(object);
        }
    }
}
