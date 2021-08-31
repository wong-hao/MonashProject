package com.example.PersonalisedMobilePainDiary.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.PersonalisedMobilePainDiary.dao.PainRecordDao;
import com.example.PersonalisedMobilePainDiary.database.PainRecordDatabase;
import com.example.PersonalisedMobilePainDiary.entity.PainRecord;

import java.util.List;

public class PainRecordRepository {
    private PainRecordDao dao;
    private LiveData<List<PainRecord>> allPainRecords;
    private PainRecord painrecord;

    public PainRecordRepository(Application application) {
        PainRecordDatabase db = PainRecordDatabase.getInstance(application);
        dao = db.painrecordDao();
    }

    public LiveData<List<PainRecord>> getAllPainRecords() {
        allPainRecords = dao.getAll();
        return allPainRecords;
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

    public void insertAll(final PainRecord... painRecords) {
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insertAll(painRecords);
            }
        });
    }

    public void updatePainRecords(final PainRecord... painRecords) {
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.updatePainRecords(painRecords);
            }
        });
    }

    public void updatePainRecordByID(final int painrecordID, final int intensity,
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
                PainRecord runPainRecord= dao.findByID(painrecordID);
                setPainrecord(runPainRecord);
            }
        });
        return painrecord;
    }
    public void setPainrecord(PainRecord painrecord){
        this.painrecord=painrecord;
    }
}
