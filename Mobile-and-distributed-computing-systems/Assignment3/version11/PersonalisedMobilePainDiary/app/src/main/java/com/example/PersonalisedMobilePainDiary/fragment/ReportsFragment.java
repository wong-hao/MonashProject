package com.example.PersonalisedMobilePainDiary.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.PersonalisedMobilePainDiary.R;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentDailyBinding;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentReportsBinding;

public class ReportsFragment extends Fragment {
    private FragmentReportsBinding binding;
    public ReportsFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReportsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}