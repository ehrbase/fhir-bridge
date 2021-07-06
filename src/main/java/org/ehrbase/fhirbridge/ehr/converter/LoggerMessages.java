package org.ehrbase.fhirbridge.ehr.converter;

public enum LoggerMessages {

    INVOKE_EXCEPTION ("Exception occurred when invoking method, error: ");

    private String errorMessage;

    LoggerMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

   public static String printInvokeError(Exception exception){
        return INVOKE_EXCEPTION.errorMessage + exception.toString();
    }

}
