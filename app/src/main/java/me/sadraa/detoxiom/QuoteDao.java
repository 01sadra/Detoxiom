package me.sadraa.detoxiom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by sadra on 10/15/17.
 */
//In Room orm like retrofit we should create an interface class that works like a contract. we should use annotation @Dao(Database access object)
@Dao
public interface QuoteDao {
    //Again like retrofit we use annotation for defining a method
    @Query("SELECT * FROM QuoteDbModel")
    List<QuoteDbModel> getAll();
    //Fortunately Room orm understand some method and we don't need to writing Unsecured Queries. Room also can convert data to  model by automate. MAGIC
    @Insert
    void insertOne(QuoteDbModel quoteDbModel);
    //I actually will not use this :).
    @Delete
    void deleteOne(QuoteDbModel quoteDbModel);


}
