package com.example.touristguide.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.touristguide.database.models.Stays;

import java.util.List;

@Dao
public interface StayDao {
    @Insert
    void insertOneStay(Stays stay);

    @Insert
    void insertMultipleStays(List<Stays> stays);

    @Update
    void updateOneStay(Stays stay);

    @Delete
    void deleteOneStay(Stays stay);

    @Query("SELECT * FROM Stays")
    List<Stays> getAllStays();

    @Query("SELECT * FROM Stays where id = :id")
    List<Stays> getOneStay (long id);

    @Query("SELECT * FROM Stays where userId = :userId")
    List<Stays> getStaysByUserId(long userId);
}

