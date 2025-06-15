package com.icicaf.example.demo_api_rest.exception;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        int code,
        String message,
        String path,
        LocalDateTime timestamp
) {}
