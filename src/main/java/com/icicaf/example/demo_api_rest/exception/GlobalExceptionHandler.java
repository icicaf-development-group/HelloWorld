package com.icicaf.example.demo_api_rest.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 - Endpoint no encontrado
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleNoHandlerFound(HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, "El recurso solicitado no existe", request);
    }

    // 400 - Body JSON malformado
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleMalformedJson(HttpServletRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, "El formato del cuerpo de la solicitud no es válido", request);
    }

    // 400 - Validación de DTO con @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleValidationError(MethodArgumentNotValidException ex, HttpServletRequest request) {
        var fieldError = ex.getBindingResult().getFieldError();
        String detalle = fieldError != null ? fieldError.getField() + ": " + fieldError.getDefaultMessage() : "Validación fallida";
        return buildError(HttpStatus.BAD_REQUEST, detalle, request);
    }

    // 400 - Validaciones de query params o path variables
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    // 500 - Cualquier otro error inesperado
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleGenericError(Exception ex, HttpServletRequest request) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", request);
    }

    // 💡 Builder común
    private ApiErrorResponse buildError(HttpStatus status, String mensaje, HttpServletRequest request) {
        return new ApiErrorResponse(
                status.value(),
                mensaje,
                request.getRequestURI(),
                LocalDateTime.now()
        );
    }
}
