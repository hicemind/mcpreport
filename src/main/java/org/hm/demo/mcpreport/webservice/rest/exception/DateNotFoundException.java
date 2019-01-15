package org.hm.demo.mcpreport.webservice.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Date not found")
public class DateNotFoundException extends RuntimeException {

}