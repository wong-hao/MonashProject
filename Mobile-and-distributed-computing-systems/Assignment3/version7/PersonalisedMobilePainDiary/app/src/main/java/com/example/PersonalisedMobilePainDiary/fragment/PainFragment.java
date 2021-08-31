package com.example.PersonalisedMobilePainDiary.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.PersonalisedMobilePainDiary.LoginActivity;
import com.example.PersonalisedMobilePainDiary.R;
import com.example.PersonalisedMobilePainDiary.database.PainRecordDatabase;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentPainBinding;
import com.example.PersonalisedMobilePainDiary.viewmodel.PainRecordViewModel;

public class PainFragment extends Fragment {

    private FragmentPainBinding binding;
    public PainFragment(){}
    PainRecordDatabase db = null;
    PainRecordViewModel model;

    //String[] array = getResources().getStringArray(R.array.star);
    private String[] array1 = {"1","2","3","4","5","6","7","8","9"};
    private String[] array2 = {"back","neck","head","knees","hips","abdomen","elbows","shoulders","shins","jaw","facial"};

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

                int goal = 10000;
                goal= Integer.parseInt(binding.editText5.getText().toString());

                int steps = 0;
                steps = Integer.parseInt(binding.editText6.getText().toString());
            }
        });

        ArrayAdapter<String> Adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,array1);
        binding.Spinner1.setPrompt("Pain intensity level");
        binding.Spinner1.setAdapter(Adapter1);
        binding.Spinner1.setSelection(0);
        binding.Spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"Pain intensity level that you choose is ："+array1[position],Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> Adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,array2);
        binding.Spinner2.setPrompt("The location of pain");
        binding.Spinner2.setAdapter(Adapter2);
        binding.Spinner2.setSelection(0);
        binding.Spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"The location of pain that you choose is ："+array2[position],Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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