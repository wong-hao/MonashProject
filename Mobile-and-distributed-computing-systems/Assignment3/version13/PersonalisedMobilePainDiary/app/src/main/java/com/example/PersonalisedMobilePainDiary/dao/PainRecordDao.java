package com.example.PersonalisedMobilePainDiary.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.PersonalisedMobilePainDiary.entity.PainRecord;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PainRecordDao {
    @Query("SELECT * FROM painrecord")
    LiveData<List<PainRecord>> getAll();
    @Query("SELECT * FROM painrecord WHERE uid = :painrecordID LIMIT 1")
    PainRecord findByID(int painrecordID);
    @Insert
    void insertAll(PainRecord... painRecords);
    @Insert
    long insert(PainRecord painRecord);
    @Delete
    void delete(PainRecord painRecord);
    @Update(onConflict = REPLACE)
    void updatePainRecords(PainRecord... painRecords);
    @Query("UPDATE painrecord SET pain_intensity_level=:intensity, location_of_pain=:location, mood_level=:mood, steps_taken=:steps WHERE uid = :id")
    void updateByID(int id, int intensity, String location, String mood, int steps);
    @Query("DELETE FROM painrecord")
    void deleteAll();
}
