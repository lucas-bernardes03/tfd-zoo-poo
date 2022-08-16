package com.poo.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CPFInvalidoException extends Exception {
    public CPFInvalidoException(String message) {
        super(message);
    }
}
