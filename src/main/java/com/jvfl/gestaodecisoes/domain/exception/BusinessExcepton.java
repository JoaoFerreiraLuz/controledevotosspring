package com.jvfl.gestaodecisoes.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BusinessExcepton extends RuntimeException {

        private static final long serialVersionUID = 1L;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public BusinessExcepton() {
    }

    public BusinessExcepton(String mensagem) {
            super(mensagem);
        }
}
