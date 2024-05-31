package org.usuarios.dto;

import lombok.Data;

@Data
public class ErrorResponse {

    public ErrorResponse(String message) {
        this.message = message;
    }

        private String message;
}
