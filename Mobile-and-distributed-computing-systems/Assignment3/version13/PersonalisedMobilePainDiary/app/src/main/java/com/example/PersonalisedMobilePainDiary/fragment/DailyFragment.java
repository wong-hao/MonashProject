package com.example.PersonalisedMobilePainDiary.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.PersonalisedMobilePainDiary.MainActivity;
import com.example.PersonalisedMobilePainDiary.R;
import com.example.PersonalisedMobilePainDiary.User;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentDailyBinding;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentPainBinding;

public class DailyFragment extends Fragment {
    private FragmentDailyBinding binding;
    public DailyFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDailyBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        String email = User.getInstance().getEmail();
        binding.textView3.setText(email);
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}