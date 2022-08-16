package com.poo.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HorarioInvalidoException extends Exception {
    public HorarioInvalidoException(String message) {
        super(message);
    }
}
