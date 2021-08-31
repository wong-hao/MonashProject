package com.example.PersonalisedMobilePainDiary.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.PersonalisedMobilePainDiary.MainActivity;
import com.example.PersonalisedMobilePainDiary.R;
import com.example.PersonalisedMobilePainDiary.User;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentDailyBinding;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentPainBinding;
import com.example.PersonalisedMobilePainDiary.entity.PainRecord;
import com.example.PersonalisedMobilePainDiary.viewmodel.PainRecordViewModel;

import java.util.List;

public class DailyFragment extends Fragment {
    private FragmentDailyBinding binding;
    public DailyFragment(){}
    PainRecordViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDailyBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        model = new ViewModelProvider(requireActivity()).get(PainRecordViewModel.class);
        model.initalizeVars(getActivity());
        model.getAllPainRecords().observe(getViewLifecycleOwner(), new
                Observer<List<PainRecord>>() {
                    @Override
                    public void onChanged(List<PainRecord> painRecords) {

                        String allPainrecords = "";
                        for(PainRecord temp : painRecords){
                            String painrecordstr = (" ID: " + temp.getId() + " Intensity: " + temp.getIntensity() + " Location: " + temp.getLocation() +  " Mood: " + temp.getMood() + " Steps: " + temp.getSteps());
                            allPainrecords = allPainrecords + System.getProperty("line.separator") + painrecordstr;
                        }
                        binding.textView9.setText("All data: " + allPainrecords);
                    }

                });

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}