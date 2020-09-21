package com.openshop.creativemindsdevtask.data.model.room.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoomDao {
    @Insert
    void addItem(RepoItem... item);

    @Delete
    void removeItem(RepoItem... item);

    @Query("select * from RepoItem")
    List<RepoItem> getAll();

    @Query("delete from RepoItem")
    void delAll();





}
