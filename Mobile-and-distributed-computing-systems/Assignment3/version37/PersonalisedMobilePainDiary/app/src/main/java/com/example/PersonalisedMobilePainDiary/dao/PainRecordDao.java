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
    void insertAll(PainRecord... painrecords);
    @Insert
    void insert(PainRecord painrecord);
    @Delete
    void delete(PainRecord painrecord);
    @Update(onConflict = REPLACE)
    void updatePainRecords(PainRecord... painrecord);
    @Query("UPDATE painrecord SET pain_intensity_level=:intensity, location_of_pain=:location, mood_level=:mood, steps_taken=:steps, `current_date`=:date, current_temperature=:temperature, current_humidity=:humidity, current_pressure=:pressure, user_email=:email WHERE uid = :id")
    void updateByID(int id, int intensity, String location, String mood, int steps, String date, String temperature, String humidity, String pressure, String email);
    @Query("UPDATE painrecord SET pain_intensity_level=:intensity, location_of_pain=:location, mood_level=:mood, steps_taken=:steps, `current_date`=:date, current_temperature=:temperature, current_humidity=:humidity, current_pressure=:pressure, user_email=:email WHERE `current_date` = :date2")
    void updateByDate(String date2, int intensity, String location, String mood, int steps, String date, String temperature, String humidity, String pressure, String email);
    @Query("DELETE FROM painrecord")
    void deleteAll();
}
