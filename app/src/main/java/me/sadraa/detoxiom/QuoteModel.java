package me.sadraa.detoxiom;

/**
 * Created by sadra on 10/8/17.
 */
public class QuoteModel {
    private boolean ok;
    private Result result;

    public boolean isOk() {
        return ok;
    }

    public Result getResult() {
        return result;
    }


}
class Result {
    private String quote;
    private String author;
    private String quote_link;
    private String author_link;

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }

    public String getQuote_link() {
        return quote_link;
    }

    public String getAuthor_link() {
        return author_link;
    }


}




