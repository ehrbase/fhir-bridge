package org.ehrbase.fhirbridge.customComparators;

import com.nedap.archie.rm.RMObject;
import liquibase.pro.packaged.S;
import org.ehrbase.fhirbridge.ehr.Composition;
import org.apache.commons.io.IOUtils;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.fhirbridge.ehr.ResourceTemplateProvider;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ProblemDiagnoseEvaluation;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.javers.common.exception.JaversException;
import org.javers.core.Javers;
import org.javers.core.diff.Diff;
import org.springframework.core.io.ClassPathResource;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class CompositionComparator {

    public Diff compareCompositions(String paragonFilePath, Composition mappedComposition, Javers javers)
            throws IOException, IllegalAccessException, IntrospectionException, InvocationTargetException {

        Class<? extends Composition> clazz = mappedComposition.getClass();

        RMObject composition = new CanonicalJson().unmarshal(IOUtils.toString(new ClassPathResource(paragonFilePath).getInputStream(), StandardCharsets.UTF_8), com.nedap.archie.rm.composition.Composition.class);
        ResourceTemplateProvider resourceTemplateProvider = new ResourceTemplateProvider("classpath:/opt/");
        resourceTemplateProvider.afterPropertiesSet();

        Flattener cut = new Flattener(resourceTemplateProvider);
        Composition paragonComposition = cut.flatten(composition, clazz);
        Diff diff = javers.compare(paragonComposition, mappedComposition);
        if (diff.hasChanges()) {
            //  debugJaversTest(javers, ((D4LQuestionnaireComposition)paragonComposition).getProblemDiagnose().get(0), ((D4LQuestionnaireComposition)mappedComposition).getProblemDiagnose().get(0));;
            debugJaversTest(javers, paragonComposition, mappedComposition);
        }
        return diff;
    }

    private void debugJaversTest(Javers javers, Object paragonComposition, Object mappedComposition) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        for (PropertyDescriptor propertyDescriptor :
                Introspector.getBeanInfo(paragonComposition.getClass()).getPropertyDescriptors()) {
            Method method = propertyDescriptor.getReadMethod();
            try {
                //    String methodName = propertyDescriptor.getReadMethod().toString().substring(propertyDescriptor.getReadMethod().toString().indexOf("get")).replace("get","").replace("()", "");
             //   System.out.println(propertyDescriptor.getReadMethod());
                System.out.println(method.invoke(paragonComposition));
                System.out.println(method.invoke(mappedComposition));
                System.out.println(method.invoke(mappedComposition).equals(method.invoke(paragonComposition)));
                Diff diff = javers.compare(method.invoke(paragonComposition), method.invoke(mappedComposition));
                if (diff.hasChanges()) {
                    diff.getChanges().forEach(change -> System.out.println("=====> Difference appears in " + propertyDescriptor.getReadMethod() + " \n at value: " + change));
                     recursive(javers, method.invoke(paragonComposition), method.invoke(mappedComposition));
                } else {
                    diff.getChanges().forEach(change -> System.out.println("=====> Difference appears in " + propertyDescriptor.getReadMethod() + " \n at value: " + change));
                }

            } catch (JaversException je) {
                if (!je.toString().contains("COMPARING_TOP_LEVEL_VALUES_NOT_SUPPORTED")) {
                    throw je;
                }
            }
        }
    }

    private void recursive(Javers javers, Object paragonComposition, Object mappedComposition) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        if (paragonComposition instanceof Collection<?>) {
            if (((Collection<?>) paragonComposition).size() == ((Collection<?>) mappedComposition).size()) {
                for (int i = 0; i < ((Collection<?>) paragonComposition).size(); i++) {
     /*               System.out.println(((Collection<?>) paragonComposition).toArray()[i]);
                    System.out.println(((Collection<?>) mappedComposition).toArray()[i]);*/
                    debugJaversTest(javers, ((Collection<?>) paragonComposition).toArray()[i], ((Collection<?>) mappedComposition).toArray()[i]);
                }
            }else{
                System.out.println("More indexes found");
            }
        }
    }
  /*  private void debugJaversTest(Javers javers, ProblemDiagnoseEvaluation paragonComposition, ProblemDiagnoseEvaluation mappedComposition) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        for (PropertyDescriptor propertyDescriptor :
                Introspector.getBeanInfo(ProblemDiagnoseEvaluation.class).getPropertyDescriptors()) {
            Method method = propertyDescriptor.getReadMethod();
            try {
                String methodName = propertyDescriptor.getReadMethod().toString().substring(propertyDescriptor.getReadMethod().toString().indexOf("get")).replace("get","").replace("()", "");
                System.out.println(propertyDescriptor.getReadMethod().toString());
                Diff diff = javers.compare(method.invoke(paragonComposition), method.invoke(mappedComposition));
                diff.getChanges().forEach(change -> System.out.println("=====> Difference appears in " + methodName + " \n at value: " + change));
            } catch (JaversException je) {
                if (!je.toString().contains("COMPARING_TOP_LEVEL_VALUES_NOT_SUPPORTED")) {
                    throw je;
                }
            }
        }
    }*/
}
