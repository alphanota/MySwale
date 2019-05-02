package com.alphanota.myswale;

import android.content.Context;

import com.alphanota.myswale.db.dao.SwaleDao;
import com.alphanota.myswale.model.Swale;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Swale.class}, version = 1)
public abstract class SwaleRoomDatabase extends RoomDatabase {
    public abstract SwaleDao swaleDao();

    private static volatile SwaleRoomDatabase INSTANCE;

    public static SwaleRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SwaleRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SwaleRoomDatabase.class, "swale_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
