package com.jvfl.gestaodecisoes.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BusinessExcepton extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BusinessExcepton() {   }

    public BusinessExcepton(String mensagem) {
        super(mensagem);
    }
}
