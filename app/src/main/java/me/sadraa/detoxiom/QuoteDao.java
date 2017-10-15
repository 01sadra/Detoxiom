package me.sadraa.detoxiom;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by sadra on 10/15/17.
 */

public interface QuoteDao {
    @Query("SELECT * FROM QuoteDbModel")
    List<QuoteDbModel> getAll();

    @Query("SELECT column FROM table ORDER BY RANDOM() LIMIT 1")
    QuoteDbModel getRandom();

    @Insert
    void insertOne(QuoteDbModel quoteDbModel);

    @Delete
    void deleteOne(QuoteDbModel quoteDbModel);


}
