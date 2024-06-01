package me.danilomarchesani.openwikipedia.errors;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This Class is made for intercepts every exception that can be raised.
 * @author Danilo M. 01/06/2024
 */
@RestControllerAdvice
public class GlobalApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex) {
        logger.error("Error occurred: " + ex.getMessage() + " Caused by" + ex.getCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotCreatedException.class)
    public ResponseEntity<?> handleUserNotCreatedException(UserNotCreatedException ex) {
        logger.error("Error occurred: " + ex.getMessage() + " Caused by" + ex.getCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<?> handleRoleNotFoundException(RoleNotFoundException ex) {
        logger.error("Error occurred: " + ex.getMessage() + " Caused by" + ex.getCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<?> handleArticleNotFoundException(ArticleNotFoundException ex) {
        logger.error("Error occurred: " + ex.getMessage() + " Caused by" + ex.getCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ArticleHistoryNotFoundException.class)
    public ResponseEntity<?> handleArticleHistoryNotFoundException(ArticleHistoryNotFoundException ex) {
        logger.error("Error occurred: " + ex.getMessage() + " Caused by" + ex.getCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DailyArticleNotFoundException.class)
    public ResponseEntity<?> handleDailyArticleNotFoundException(DailyArticleNotFoundException ex) {
        logger.error("Error occurred: " + ex.getMessage() + " Caused by" + ex.getCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRaisedRunTimeException(RuntimeException ex) {
        logger.error("Error occurred: " + ex.getMessage() + " Caused by" + ex.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleRaisedException(Exception ex) {
        logger.error("Error occurred: " + ex.getMessage() + " Caused by" + ex.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
