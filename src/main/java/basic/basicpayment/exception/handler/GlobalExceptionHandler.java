package basic.basicpayment.exception.handler;

import basic.basicpayment.exception.ErrorResponse;
import basic.basicpayment.exception.exceptions.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidParameterException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // === 400 ===
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidParameterException.class})
    public ErrorResponse InvalidParameterException(InvalidParameterException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorResponse MethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String msg = exception.getMessage();
        try {
            msg = exception.getBindingResult().getFieldError().getDefaultMessage();
        } catch (Exception ignore) {}

        return new ErrorResponse(HttpStatus.BAD_REQUEST, msg);
    }

    // === 404 ===
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotFoundException.class)
    public ErrorResponse ItemNotFoundException(ItemNotFoundException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

}
