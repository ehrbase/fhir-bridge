package org.ehrbase.fhirbridge.camel.processor;

import com.nedap.archie.rm.RMObject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.client.flattener.Unflattener;
import org.ehrbase.fhirbridge.ehr.Composition;
import org.ehrbase.fhirbridge.ehr.ResourceTemplateProvider;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class MappingDebugger implements Processor, MessageSourceAware {
    private MessageSourceAccessor messages;

    @Override
    public void process(Exchange exchange) throws Exception {
        Composition composition = (Composition) exchange.getProperty("Composition");

        ResourceTemplateProvider resourceTemplateProvider = new ResourceTemplateProvider("classpath:/opt/");
        resourceTemplateProvider.afterPropertiesSet();

       /* Unflattener unflattener = new Unflattener(resourceTemplateProvider);

        RMObject rmObject = unflattener.unflatten(composition);*/
        exchange.getMessage().setBody("asdasd");
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}
