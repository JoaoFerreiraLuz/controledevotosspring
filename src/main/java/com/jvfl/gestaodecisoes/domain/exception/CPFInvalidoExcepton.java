package com.jvfl.gestaodecisoes.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CPFInvalidoExcepton extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CPFInvalidoExcepton(String mensagem) {
        super(mensagem);
    }
}
