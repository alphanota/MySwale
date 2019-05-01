package com.alphanota.myswale;

import android.content.Context;

import com.alphanota.myswale.db.dao.SwaleDao;

import androidx.room.Room;
import androidx.room.RoomDatabase;

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
