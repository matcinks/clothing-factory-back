package pl.clothingfactory.exception;


import io.jsonwebtoken.ExpiredJwtException;
import org.hibernate.JDBCException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.format.DateTimeParseException;

@ControllerAdvice
@RestController
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<CustomExceptionResponse> handleCustomExceptions(CustomException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(new CustomExceptionResponse(ex));
    }

    @ExceptionHandler(JDBCException.class)
    public final ResponseEntity<CustomExceptionResponse> handleAllHttpExceptions(JDBCException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomExceptionResponse(ex));
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public final ResponseEntity<CustomExceptionResponse> handleAllHttpExceptions(InvalidDataAccessApiUsageException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomExceptionResponse(ex));
    }

    @ExceptionHandler(value = { AccessDeniedException.class })
    public  ResponseEntity<CustomExceptionResponse> handleUnauthorizedException(AccessDeniedException ex ) {
        CustomException ce = new CustomException(ex.getMessage());
        ce.errorCode = "403";
        ce.httpStatus = HttpStatus.FORBIDDEN;
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CustomExceptionResponse(ce));
    }

    @ExceptionHandler(DateTimeParseException.class)
    public final ResponseEntity<CustomExceptionResponse> handleDataTimeExceptions(DateTimeParseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomExceptionResponse(ex));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomExceptionResponse(ex));
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public final ResponseEntity<CustomExceptionResponse> handleObjectOptimisticLockingFailureException(ObjectOptimisticLockingFailureException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomExceptionResponse(ex));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<CustomExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomExceptionResponse(ex));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public final ResponseEntity<CustomExceptionResponse> handleJwtException(ExpiredJwtException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CustomExceptionResponse(ex));
    }

}