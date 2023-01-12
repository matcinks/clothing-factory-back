package pl.ilpiu.clothingfactory.exception;

import org.springframework.http.HttpStatus;

public class ObjectVersionInconsistentException extends CustomException {

        private static final String ERROR_CODE = "E003";

        public ObjectVersionInconsistentException(String message) {
            super(message);
            this.errorCode = ERROR_CODE;
            this.httpStatus = HttpStatus.CONFLICT;
        }


}
