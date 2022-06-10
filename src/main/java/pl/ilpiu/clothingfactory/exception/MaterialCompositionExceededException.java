package pl.ilpiu.clothingfactory.exception;

import org.springframework.http.HttpStatus;

public class MaterialCompositionExceededException extends CustomException {

    private static final String ERROR_CODE = "E002";

    public MaterialCompositionExceededException(String message) {
        super(message);
        this.errorCode = ERROR_CODE;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }


}
