package me.danilomarchesani.openwikipedia.errors;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DailyArticleNotFoundException extends RuntimeException {

    public DailyArticleNotFoundException(String message) {
        super(message);
    }

    public DailyArticleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}
