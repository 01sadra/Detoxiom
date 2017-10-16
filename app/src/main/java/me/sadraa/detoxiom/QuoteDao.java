package me.sadraa.detoxiom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by sadra on 10/15/17.
 */
@Dao
public interface QuoteDao {
    @Query("SELECT * FROM QuoteDbModel")
    List<QuoteDbModel> getAll();


    @Insert
    void insertOne(QuoteDbModel quoteDbModel);

    @Delete
    void deleteOne(QuoteDbModel quoteDbModel);


}
