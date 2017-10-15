package me.sadraa.detoxiom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by sadra on 10/15/17.
 */
@Database (entities = {QuoteDbModel.class},version = 1)
public abstract class QuoteDb extends RoomDatabase {
    static final String DATABASE_NAME = "QuoteDbModel";
    public abstract QuoteDao quoteDao();
}
