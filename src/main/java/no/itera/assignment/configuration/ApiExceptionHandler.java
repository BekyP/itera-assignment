package no.itera.assignment.configuration;

import lombok.extern.slf4j.Slf4j;
import no.itera.assignment.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleGeneralException(Exception ex) {
    log.error(ex.getMessage(), ex);
    return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseEntity<Object> handleException(Exception ex, HttpStatus httpStatus) {
    ErrorResponseDto errorResponseDTO = new ErrorResponseDto(httpStatus.toString(),
        ex.getMessage());
    return ResponseEntity.status(httpStatus).body(errorResponseDTO);
  }
}
