package me.sadraa.detoxiom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by sadra on 10/15/17.
 */
@Database (entities = {QuoteDbModel.class},version = 1)
public abstract class QuoteDb extends RoomDatabase {
    static final String DATABASE_NAME = "QuoteDbModel";
    private static QuoteDb INSTANCE;
    public abstract QuoteDao quoteDao();

    public static QuoteDb getQuoteDb(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                            QuoteDb.class,
                                            "QuoteDataBase").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
