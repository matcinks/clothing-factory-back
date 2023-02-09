package pl.clothingfactory.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomException extends RuntimeException {

    protected String errorCode;
    protected HttpStatus httpStatus;

    public CustomException(String message) {
        super(message);
    }
}