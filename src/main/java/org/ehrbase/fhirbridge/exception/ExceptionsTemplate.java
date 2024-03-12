package org.ehrbase.fhirbridge.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.ehrbase.fhirbridge.exception.dto.ExceptionDto;

public class ExceptionsTemplate {
    public static String AN_IO_EXCEPTION_OCCURED_WHILE_LOADING_CUSTOM_PROFILES = "An I/O exception occurred while loading custom profiles";
    public static String AN_IO_EXCEPTION_OCCURED_DURING_INITIALIZATION = "An I/O exception occurred during initialization";
    public static String AN_ERROR_OCCURRED_WHILE_PARSING_TEMPLATE = "An error occurred while parsing template [%s]";


    public static Map<String, ExceptionDto> errorMap = new HashMap<>();

    public void populateExceptionMap() {
        errorMap.put(AN_IO_EXCEPTION_OCCURED_WHILE_LOADING_CUSTOM_PROFILES, new ExceptionDto( 1, new ArrayList<>() ) );
        errorMap.put(AN_IO_EXCEPTION_OCCURED_DURING_INITIALIZATION, new ExceptionDto( 2, new ArrayList<>() ) );
        errorMap.put(AN_ERROR_OCCURRED_WHILE_PARSING_TEMPLATE, new ExceptionDto( 3, new ArrayList<>() ) );//1 parameter
    }
}
