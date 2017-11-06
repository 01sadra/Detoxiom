package me.sadraa.detoxiom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by sadra on 10/15/17.
 */
//we can assume this class as an database provider class.
@Database (entities = {QuoteDbModel.class},version = 1)
public abstract class QuoteDb extends RoomDatabase {
    //First we should create an object from Dao class
    public abstract QuoteDao quoteDao();

    //DataBase object is an expensive object. we use singleton method to sure we only create 1 object at a time.
    // more info(https://www.tutorialspoint.com/design_pattern/singleton_pattern.html)
    private static QuoteDb INSTANCE;
    public static QuoteDb getQuoteDb(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                            QuoteDb.class,
                                            "QuoteDataBase")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
