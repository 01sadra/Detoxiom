package me.sadraa.detoxiom;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by sadra on 10/15/17.
 */
@Entity(tableName = "QuoteDbModel")
public class QuoteDbModel {

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    @PrimaryKey(autoGenerate = true)
    private int quoteId;

    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name ="quote")
    private String quote;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
