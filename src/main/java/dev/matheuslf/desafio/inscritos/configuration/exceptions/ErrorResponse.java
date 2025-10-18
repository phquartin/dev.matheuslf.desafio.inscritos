package dev.matheuslf.desafio.inscritos.configuration.exceptions;

import java.util.List;

public record ErrorResponse(
        List<String> messages,
        String status,
        String path,
        String timestamp,
        String errorType
        ) {
}
