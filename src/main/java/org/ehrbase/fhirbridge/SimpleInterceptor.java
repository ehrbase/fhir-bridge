package org.ehrbase.fhirbridge;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Interceptor
public class SimpleInterceptor {

    private final Logger logger = LoggerFactory.getLogger(SimpleInterceptor.class);

    @Hook(Pointcut.SERVER_PROCESSING_COMPLETED)
    public void handle() {
        logger.debug("SimpleInterceptor::handle");
    }
}
