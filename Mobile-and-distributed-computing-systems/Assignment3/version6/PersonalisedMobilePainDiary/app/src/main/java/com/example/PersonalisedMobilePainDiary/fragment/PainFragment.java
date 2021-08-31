package com.example.PersonalisedMobilePainDiary.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.PersonalisedMobilePainDiary.database.PainRecordDatabase;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentPainBinding;
import com.example.PersonalisedMobilePainDiary.viewmodel.PainRecordViewModel;

public class PainFragment extends Fragment {
    private FragmentPainBinding binding;
    public PainFragment(){}
    PainRecordDatabase db = null;
    PainRecordViewModel model;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        model = new ViewModelProvider(requireActivity()).get(PainRecordViewModel.class);

        binding.Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String message = binding.editText.getText().toString();
                //if (!message.isEmpty() ) {
                //    model.setMessage(message);
                //}
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