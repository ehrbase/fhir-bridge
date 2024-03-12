package org.ehrbase.fhirbridge.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.ehrbase.fhirbridge.exception.dto.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.nonNull;
import static org.ehrbase.fhirbridge.exception.ExceptionsTemplate.AN_IO_EXCEPTION_OCCURED_WHILE_LOADING_CUSTOM_PROFILES;
import static org.ehrbase.fhirbridge.exception.ExceptionsTemplate.errorMap;

@Slf4j
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InputOutputException.class)
    public ResponseEntity<ErrorDetails> handleTokenErrors(
            InputOutputException exception) {

        var errors = Map.of( exception.getEntity().getSimpleName(),
                exception.getEntityId() );

        ErrorDetails errorDetails = ErrorDetails
                .builder()
                .messageId( errorMap.get( AN_IO_EXCEPTION_OCCURED_WHILE_LOADING_CUSTOM_PROFILES ).getId() )
                .argumentsList( errorMap.get( AN_IO_EXCEPTION_OCCURED_WHILE_LOADING_CUSTOM_PROFILES ).getArgumentsList() )
                .message( AN_IO_EXCEPTION_OCCURED_WHILE_LOADING_CUSTOM_PROFILES )
                .details( errors )
                .build();
        log.debug(exception.getMessage(), exception);
        return ResponseEntity.badRequest().body( errorDetails );
    }

    @ExceptionHandler(IODetailedException.class)
    public ResponseEntity<ErrorDetails> handleIODetailedException(
            IODetailedException exception) {

        var className = nonNull(exception.getEntity()) ? exception.getEntity().getSimpleName() : null;
        var description = exception.getMessage();

        var errors = Map.of( "Error Message",
                nonNull(exception.getMessage()) ? exception.getMessage() : description);
        ErrorDetails errorDetails = ErrorDetails
                .builder()
                .messageId( nonNull(errorMap.get( exception.getParamValue())) ? errorMap.get( exception.getParamValue() ).getId() : -1)
                .argumentsList( nonNull(exception.getEntity()) ? Arrays.asList(className, description) : new ArrayList<>() )
                .message( exception.getMessage() )
                .details( errors )
                .build();
        log.debug(exception.getMessage(), exception);
        return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( errorDetails );
    }

    @ExceptionHandler(ParsingTemplateException.class)
    public ResponseEntity<ErrorDetails> handleParsingException(
            ParsingTemplateException exception) {

        var className = nonNull(exception.getEntity()) ? exception.getEntity().getSimpleName() : null;
        var description = exception.getMessage();

        var errors = Map.of( "Error Message",
                nonNull(exception.getMessage()) ? exception.getMessage() : description);
        ErrorDetails errorDetails = ErrorDetails
                .builder()
                .messageId( nonNull(errorMap.get( exception.getParamValue())) ? errorMap.get( exception.getParamValue() ).getId() : -1)
                .argumentsList( nonNull(exception.getEntity()) ? Arrays.asList(className, description) : new ArrayList<>() )
                .message( exception.getMessage() )
                .details( errors )
                .build();
        log.debug(exception.getMessage(), exception);
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

}
