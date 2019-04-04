package com.smart.sales.manager.controller;



import java.util.Date;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.smart.sales.manager.exception.model.ResourceNotFoundException;
import com.smart.sales.manager.response.model.ErrorDetails;

import io.jsonwebtoken.ExpiredJwtException;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@Autowired
	private MessageSource messageSource;
	
	private String processFieldErrors(Set<ConstraintViolation<?>> exs, Locale currentLocale) {
			StringBuilder stringBuilder =new StringBuilder();
		for(ConstraintViolation<?> constraintViolation:exs) {
			if(constraintViolation != null) {
				String[] propertyname=  {constraintViolation.getPropertyPath().toString()};	
				String message=messageSource.getMessage(constraintViolation.getMessage(),propertyname,currentLocale);
				stringBuilder.append(message).append("<br/>");
			}else {
				constraintViolation=null;
			}
		}
		return stringBuilder.toString();
    }
 
    
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ErrorDetails> handleContraintNotSatified(ConstraintViolationException ex,
			WebRequest request) {	
		String message=processFieldErrors(ex.getConstraintViolations(),request.getLocale());		
		ErrorDetails errorDetails = new ErrorDetails(new Date(),message,messageSource.getMessage("constraint.exception",null, request.getLocale()),HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<ErrorDetails> usernameNotFoundOrActivated(UsernameNotFoundException ex,
			WebRequest request) {	
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),messageSource.getMessage("user.notfound",null, request.getLocale()),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TransactionSystemException.class)
	public final ResponseEntity<ErrorDetails> handleContraintNotSatified(TransactionSystemException ex,
			WebRequest request) {	
		Throwable trThrowable=ex.getMostSpecificCause();
		if(trThrowable instanceof ConstraintViolationException)
			return handleContraintNotSatified(((ConstraintViolationException) trThrowable), request);
		ErrorDetails errorDetails = new ErrorDetails(new Date(),trThrowable.getMessage(),messageSource.getMessage("constraint.exception",null, request.getLocale()),HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<ErrorDetails> handleContraintNotSatified(DataIntegrityViolationException ex,
			WebRequest request) {	
		String message=ex.getMostSpecificCause().getMessage();
		ErrorDetails errorDetails = new ErrorDetails(new Date(),message,messageSource.getMessage("constraint.exception",null, request.getLocale()),HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public final ResponseEntity<ErrorDetails> handleRestAccessDeniedException(EmptyResultDataAccessException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),messageSource.getMessage("resource.notfound",null, request.getLocale()),ex.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(ResourceNotFoundException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),messageSource.getMessage("resource.notfound",null, request.getLocale()),ex.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(NoSuchElementException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(NoSuchElementException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),messageSource.getMessage("resource.notfound",null, request.getLocale()),ex.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	
	  @ExceptionHandler(BadCredentialsException.class) public final
	  ResponseEntity<ErrorDetails> handleBadCredentails(BadCredentialsException ex,  WebRequest request) 
	  {  
		  ErrorDetails errorDetails = new ErrorDetails(new Date(),messageSource.getMessage("unauthorised.access",null,request.getLocale()),ex.getMessage(),HttpStatus.UNAUTHORIZED.value()); 
		  return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED); 
	  }
	 
	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<ErrorDetails> handleRestAccessDeniedException(AccessDeniedException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),messageSource.getMessage("access.denied",null, request.getLocale()),ex.getMessage(),HttpStatus.NOT_FOUND.value());
		//ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),"You are not authorize to access these resources",HttpStatus.FORBIDDEN.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}
	@ExceptionHandler(ExpiredJwtException.class)
	public final ResponseEntity<ErrorDetails> handleRestExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),messageSource.getMessage("token.expired",null, request.getLocale()),ex.getMessage(),HttpStatus.GATEWAY_TIMEOUT.value());
		//ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),"You are not authorize to access these resources",HttpStatus.FORBIDDEN.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.GATEWAY_TIMEOUT);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/*
	 * @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
 
        return processFieldErrors(fieldErrors);
    } */
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    String errorMessage = ex.getBindingResult().getFieldErrors().stream()
	                 .map(DefaultMessageSourceResolvable::getDefaultMessage)
	                 .findFirst()
	                 .orElse(ex.getMessage());
	    ErrorDetails errorDetails = new ErrorDetails(new Date(),errorMessage,messageSource.getMessage("constraint.exception",null, request.getLocale()),HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	    
	  }

	 
    
	
}