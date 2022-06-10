package pl.ilpiu.clothingfactory.exception;

import lombok.Getter;
import org.hibernate.JDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;


@Getter
public class CustomExceptionResponse {

    private String errorCode;
    private String message;
    private HttpStatus httpStatus;

    public CustomExceptionResponse(CustomException customException) {
        this.errorCode = customException.getErrorCode();
        this.message = customException.getMessage();
        this.httpStatus = customException.getHttpStatus();
    }

    public CustomExceptionResponse(RuntimeException runtimeException) {
        this.errorCode = "400";
        this.message = runtimeException.getMessage();
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public CustomExceptionResponse(JDBCException jdbcException) {
        this.errorCode = String.valueOf("SQL ERROR: " + jdbcException.getSQLState());
        this.message = jdbcException.getCause().getMessage();
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public CustomExceptionResponse(MethodArgumentNotValidException ex) {
        this.errorCode = "400";
        this.message = ex.toString();
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
