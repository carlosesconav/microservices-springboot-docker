package com.devsu.accounts.domain.exception;

import com.devsu.accounts.domain.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class ServiceException extends Exception {

    private String code;
    private String message;

    /**
     * Instantiates a new Service exception.
     */
    public ServiceException() {
        super();
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param exceptionEnum the exception enum
     */
    public ServiceException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.message = exceptionEnum.getMessage();
        this.code = exceptionEnum.getHttpCode();

    }
}
