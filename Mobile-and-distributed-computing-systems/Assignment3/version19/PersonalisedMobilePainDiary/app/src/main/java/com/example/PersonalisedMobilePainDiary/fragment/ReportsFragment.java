package com.example.PersonalisedMobilePainDiary.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.PersonalisedMobilePainDiary.R;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentDailyBinding;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentReportsBinding;
import com.example.PersonalisedMobilePainDiary.entity.PainRecord;
import com.example.PersonalisedMobilePainDiary.viewmodel.PainRecordViewModel;

import java.util.List;

public class ReportsFragment extends Fragment {
    private FragmentReportsBinding binding;
    public ReportsFragment(){}
    PainRecordViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReportsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        model = new ViewModelProvider(requireActivity()).get(PainRecordViewModel.class);
        model.initalizeVars(getActivity());
        model.getAllPainRecords().observe(getViewLifecycleOwner(), new
                Observer<List<PainRecord>>() {
                    @Override
                    public void onChanged(List<PainRecord> painRecords) {

                        String allPainrecords = "";
                        for(PainRecord temp : painRecords){
                            String painrecordstr = (" ID: " + temp.getId() + " Intensity: " + temp.getIntensity() + " Location: " + temp.getLocation() +  " Mood: " + temp.getMood() + " Steps: " + temp.getSteps() + " Date: " + temp.getDate() + " Temperature: " + temp.getTemperature() + " Humidity: " + temp.getHumidity() + " Pressure: " + temp.getPressure() + " Email: " + temp.getEmail());
                        }
                    }

                });
        model.findByID(0);

        binding.switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getActivity(),"Checked!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),"Not Checked!",Toast.LENGTH_SHORT).show();
                }
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