package com.example.PersonalisedMobilePainDiary.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.PersonalisedMobilePainDiary.MainActivity;
import com.example.PersonalisedMobilePainDiary.R;
import com.example.PersonalisedMobilePainDiary.User;
import com.example.PersonalisedMobilePainDiary.bean.RecycleAdapter;
import com.example.PersonalisedMobilePainDiary.bean.RecycleBean;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentDailyBinding;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentPainBinding;
import com.example.PersonalisedMobilePainDiary.entity.PainRecord;
import com.example.PersonalisedMobilePainDiary.viewmodel.PainRecordViewModel;

import java.util.ArrayList;
import java.util.List;

public class DailyFragment extends Fragment {
    private FragmentDailyBinding binding;
    public DailyFragment(){}
    PainRecordViewModel model;
    private List<RecycleBean> data = new ArrayList<>();

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

                        //String allPainrecords = "";
                        for(PainRecord temp : painRecords){
                            //String painrecordstr = (" ID: " + temp.getId() + " Intensity: " + temp.getIntensity() + " Location: " + temp.getLocation() +  " Mood: " + temp.getMood() + " Steps: " + temp.getSteps() + " Date: " + temp.getDate() + " Temperature: " + temp.getTemperature() + " Humidity: " + temp.getHumidity() + " Pressure: " + temp.getPressure() + " Email: " + temp.getEmail());
                            //allPainrecords = allPainrecords + System.getProperty("line.separator") + painrecordstr;


                            //RecycleBean recycleBean = new RecycleBean(temp.getId(),temp.getIntensity(),temp.getLocation(),temp.getMood(), temp.getSteps(),temp.getDate(),temp.getTemperature(),temp.getPressure(),temp.getPressure(),temp.getEmail());

                            RecycleBean recycleBean = new RecycleBean();
                            recycleBean.setId(temp.getId());
                            recycleBean.setIntensity(temp.getIntensity());
                            recycleBean.setLocation(temp.getLocation());
                            recycleBean.setMood(temp.getMood());
                            recycleBean.setSteps(temp.getSteps());
                            recycleBean.setDate(temp.getDate());
                            recycleBean.setTemperature(temp.getTemperature());
                            recycleBean.setHumidity(temp.getHumidity());
                            recycleBean.setPressure(temp.getPressure());
                            recycleBean.setEmail(temp.getEmail());

                            data.add(recycleBean);
                        }
                        //binding.textView9.setText("All data: " + allPainrecords);
                    }

                });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recycleview.setLayoutManager(linearLayoutManager);

        RecycleAdapter recycleAdapter = new RecycleAdapter(data,getContext());
        binding.recycleview.setAdapter(recycleAdapter);
        recycleAdapter.notifyDataSetChanged();


        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}