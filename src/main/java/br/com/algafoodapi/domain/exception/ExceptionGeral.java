package br.com.algafoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionGeral extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ExceptionGeral(String message) {
        super(message);
    }
}
