package me.danilomarchesani.openwikipedia.errors;

public class ArticleNotFoundException extends RuntimeException{

    public ArticleNotFoundException() {
        super();
    }

    public ArticleNotFoundException(Throwable cause) {
        super(cause);
    }

    public ArticleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArticleNotFoundException(String message) {
        super(message);
    }
}
