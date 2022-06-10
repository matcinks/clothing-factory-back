package pl.ilpiu.clothingfactory.exception;

import org.springframework.http.HttpStatus;

public class ObjectNotFoundInDBException extends CustomException {

    private static final String ERROR_CODE = "E001";

    public ObjectNotFoundInDBException(String message) {
        super(message);
        this.errorCode = ERROR_CODE;
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

}
