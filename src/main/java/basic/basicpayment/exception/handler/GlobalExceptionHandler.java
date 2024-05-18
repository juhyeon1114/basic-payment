package basic.basicpayment.exception.handler;

import basic.basicpayment.exception.ErrorResponse;
import basic.basicpayment.exception.exceptions.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // === 404 ===
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotFoundException.class)
    public ErrorResponse ItemNotFoundException(ItemNotFoundException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

}
