package com.guilherme.controle_estoque.exception;

import java.time.LocalDateTime;

public record ApiError(
         int status,
         String error,
         String message,
         String path,
         LocalDateTime timestamp
) {
}

