package com.example.PersonalisedMobilePainDiary.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.search.geocode.GeoCoder;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentMapsBinding;


public class MapsFragment extends Fragment {
    private FragmentMapsBinding binding;
    public MapsFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMapsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //初始化搜索模块
        //geoCoder = GeoCoder.newInstance();


        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}