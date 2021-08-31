package com.example.PersonalisedMobilePainDiary.viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.PersonalisedMobilePainDiary.entity.PainRecord;
import com.example.PersonalisedMobilePainDiary.repository.PainRecordRepository;

import java.util.List;

public class PainRecordViewModel extends ViewModel {
    private PainRecordRepository pRepository;
    private MutableLiveData<List<PainRecord>> allPainRecords;
    public PainRecordViewModel () {
        allPainRecords=new MutableLiveData<>();
    }
    public void setAllPainRecords(List<PainRecord> painRecords) {
        allPainRecords.setValue(painRecords);
    }
    public LiveData<List<PainRecord>> getAllPainRecords() {
        return pRepository.getAllPainrecords();
    }
    public void initalizeVars(Activity activity){
        pRepository = new PainRecordRepository(activity);
    }
    public void insert(PainRecord painrecord) {
        pRepository.insert(painrecord);
    }
    public void insertAll(PainRecord... painRecords) {
        pRepository.insertAll(painRecords);
    }
    public void deleteAll() {
        pRepository.deleteAll();
    }
    public void update(PainRecord... painRecords) {
        pRepository.updatePainrecords(painRecords);
    }
    public void updateByID(final int painrecordID, final int intensity,
                           final String location, final String mood, final int steps, final String date, final String temperature, final String humidity, final String pressure, final String email) {
        pRepository.updatePainrecordByID(painrecordID, intensity, location, mood, steps,date, temperature, humidity, pressure, email);
    }
    public void updateByDate(final String date2, final int intensity,
                           final String location, final String mood, final int steps, final String date, final String temperature, final String humidity, final String pressure, final String email) {
        pRepository.updatePainrecordByDate(date2, intensity, location, mood, steps,date, temperature, humidity, pressure, email);
    }
    public PainRecord findByID(int painrecordID){
        return pRepository.findByID(painrecordID);
    }
}
