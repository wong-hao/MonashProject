package com.example.PersonalisedMobilePainDiary.repository;

import android.app.Activity;
import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.PersonalisedMobilePainDiary.dao.PainRecordDao;
import com.example.PersonalisedMobilePainDiary.database.PainRecordDatabase;
import com.example.PersonalisedMobilePainDiary.entity.PainRecord;

import java.util.List;

public class PainRecordRepository {
    private PainRecordDao dao;
    private LiveData<List<PainRecord>> allPainrecords;
    private PainRecord painrecord;

    public PainRecordRepository(Activity activity) {
        PainRecordDatabase db = PainRecordDatabase.getInstance(activity);
        dao = db.painrecordDao();
    }

    public LiveData<List<PainRecord>> getAllPainrecords() {
        allPainrecords = dao.getAll();
        return allPainrecords;
    }

    public void insert(final PainRecord painrecord) {
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(painrecord);
            }
        });
    }

    public void deleteAll() {
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteAll();
            }
        });
    }

    public void delete(final PainRecord painrecord) {
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.delete(painrecord);
            }
        });
    }

    public void insertAll(final PainRecord... painrecords) {
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insertAll(painrecords);
            }
        });
    }

    public void updatePainrecords(final PainRecord... painrecords) {
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.updatePainRecords(painrecords);
            }
        });
    }

    public void updatePainrecordByID(final int painrecordID, final int intensity,
                                   final String location, final String mood, final int steps) {
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.updateByID(painrecordID, intensity, location, mood, steps);
            }
        });
    }
    public PainRecord findByID(final int painrecordID){
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                PainRecord runPainrecord= dao.findByID(painrecordID);
                setPainrecord(runPainrecord);
            }
        });
        return painrecord;
    }
    public void setPainrecord(PainRecord painrecord){
        this.painrecord=painrecord;
    }
}
