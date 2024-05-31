package me.danilomarchesani.openwikipedia.errors;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ArticleHistoryNotFoundException extends RuntimeException {
    public ArticleHistoryNotFoundException(String message) {
        super(message);
    }

    public ArticleHistoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}
