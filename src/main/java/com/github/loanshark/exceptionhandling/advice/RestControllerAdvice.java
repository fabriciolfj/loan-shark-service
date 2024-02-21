package com.github.loanshark.exceptionhandling.advice;

import com.github.loanshark.exceptionhandling.dto.ErrorDTO;
import com.github.loanshark.exceptionhandling.dto.ErrorDetailsDTO;
import com.github.loanshark.exceptionhandling.exceptions.LoanNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class RestControllerAdvice {

    private final MessageSource messageSource;

    private static final String MESSAGE_VALIDATION = "validação dos campos da requisição";

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity handleLoanNotFoundException(final LoanNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorDTO.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorDTO.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(MESSAGE_VALIDATION)
                        .details(mappingError(e))
                        .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(final ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorDTO.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(MESSAGE_VALIDATION)
                        .details(mappingError(e))
                        .build());
    }

    private List<ErrorDetailsDTO> mappingError(final ConstraintViolationException e) {
        return e.getConstraintViolations()
                .stream().map(obj -> {
                    final String message = obj.getMessage();
                    final String name;

                    if (obj instanceof FieldError) {
                        name = ((FieldError) obj).getField();
                    } else {
                        name = obj.getPropertyPath().toString();
                    }

                    return ErrorDetailsDTO
                            .builder()
                            .field(name)
                            .message(message)
                            .build();
                }).collect(Collectors.toList());
    }

    private List<ErrorDetailsDTO> mappingError(final MethodArgumentNotValidException e) {
        return e.getBindingResult().getAllErrors()
                .stream().map(obj -> {
                    final String message = messageSource.getMessage(obj, LocaleContextHolder.getLocale());
                    final String name;

                    if (obj instanceof FieldError) {
                        name = ((FieldError) obj).getField();
                    } else {
                        name = obj.getObjectName();
                    }

                    return ErrorDetailsDTO
                            .builder()
                            .field(name)
                            .message(message)
                            .build();
                }).collect(Collectors.toList());
    }
}
