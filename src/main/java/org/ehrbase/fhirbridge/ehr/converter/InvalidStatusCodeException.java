package org.ehrbase.fhirbridge.ehr.converter;

public class InvalidStatusCodeException extends ConversionException {

    public InvalidStatusCodeException(String statusCode) {
       super("The status code: "+statusCode+" is not supported by the fhir bridge, since it does not accept unfinished instances. If an fix is necessary, please contact the administrator of the Bridge. Supported are either final, amended, registered or preliminary");
    };

}
