package org.hm.demo.mcpreport.webservice.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestExceptionHandler {

    Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * Handles invalid dates, and return a bad request.
     * The MethodArgumentTypeMismatchException can be throw by others endpoints or others parameters,
     * but for this example, I'll simplify because I only have and endpoint with one parameter.
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleConverterErrors(HttpServletRequest req, MethodArgumentTypeMismatchException exception) {
        logger.error("Invalid date: "+req.getParameter("date"));
        return ResponseEntity.badRequest().body("Bad Request: [Invalid date]");
    }

    //TODO handle more errors. Only one was wrote just to be an example.

}
