package me.danilomarchesani.openwikipedia.errors;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotCreatedException extends RuntimeException {

    public UserNotCreatedException(String message) {
        super(message);
    }

    public UserNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
