package com.openshop.creativemindsdevtask.data.model.room.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {RepoItem.class}, version =2, exportSchema = false)


public abstract class RoomManger extends RoomDatabase {
    public abstract RoomDao roomDao();

    public static RoomManger roomManger;

    public static synchronized RoomManger getInistance(Context context) {
        if (roomManger == null) {
            roomManger = Room.databaseBuilder(context.getApplicationContext(), RoomManger.class,
                    "WooWdatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return roomManger;
    }
}
