package com.example.PersonalisedMobilePainDiary.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.PersonalisedMobilePainDiary.R;
import com.example.PersonalisedMobilePainDiary.Steps;
import com.example.PersonalisedMobilePainDiary.User;
import com.example.PersonalisedMobilePainDiary.Weather;
import com.example.PersonalisedMobilePainDiary.database.PainRecordDatabase;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentPainBinding;
import com.example.PersonalisedMobilePainDiary.entity.PainRecord;
import com.example.PersonalisedMobilePainDiary.viewmodel.PainRecordViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PainFragment extends Fragment {

    private FragmentPainBinding binding;
    public PainFragment(){}
    PainRecordDatabase db = null;
    PainRecordViewModel model;

    //String[] array = getResources().getStringArray(R.array.star);
    private String[] array1 = {"0","1","2","3","4","5","6","7","8","9","10"};
    private String[] array2 = {"back","neck","head","knees","hips","abdomen","elbows","shoulders","shins","jaw","facial"};
    int intensity;
    String location;
    String mood;
    int goal = 10000;
    int steps;
    String date;
    String temperature;
    String humidity;
    String pressure;
    String email;
    int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        model = new ViewModelProvider(requireActivity()).get(PainRecordViewModel.class);
        model.initalizeVars(getActivity());

        ArrayAdapter<String> Adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,array1);
        binding.Spinner1.setPrompt("Pain intensity level");
        binding.Spinner1.setAdapter(Adapter1);
        binding.Spinner1.setSelection(0,true);
        binding.Spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"Pain intensity level that you choose is ???"+array1[position],Toast.LENGTH_SHORT).show();
                intensity = Integer.parseInt(array1[position]);
                //System.out.println(intensity);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> Adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,array2);
        binding.Spinner2.setPrompt("The location of pain");
        binding.Spinner2.setAdapter(Adapter2);
        binding.Spinner2.setSelection(0,true);
        binding.Spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"The location of pain that you choose is ???"+array2[position],Toast.LENGTH_SHORT).show();
                location = array2[position];
                if(location.isEmpty()){
                    Toast.makeText(getActivity(),"Location is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                //System.out.println(location);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SimpleAdapter simpleAdapter =new SimpleAdapter(getActivity(), getListData(), R.layout.pic_item,
                new String[]{"npic","namepic"}, new int[]{R.id.Image,R.id.Text});
        binding.Spinner3.setPrompt("Mood level");
        binding.Spinner3.setAdapter(simpleAdapter);
        binding.Spinner3.setSelection(0,true);
        binding.Spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);

                Toast.makeText(getActivity(),"The mood level of you is "+
                        map.get("namepic").toString(),Toast.LENGTH_SHORT).show();
                mood = map.get("namepic").toString();
                if(mood.isEmpty()){
                    Toast.makeText(getActivity(),"Mood is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                //System.out.println(mood);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.editText5.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Goal is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                goal= Integer.parseInt(binding.editText5.getText().toString());
                //System.out.println("Goal set: "+goal);
                Steps.getInstance().setGoal(goal);

                if(binding.editText6.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Steps take is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                steps = Integer.parseInt(binding.editText6.getText().toString());
                //System.out.println("Steps_taken set: "+steps);
                Steps.getInstance().setSteps_taken(steps);


                date = Weather.getInstance().getDate();
                if(date == null){
                    Toast.makeText(getActivity(),"Weather API is not working",Toast.LENGTH_SHORT).show();
                    return;
                }
                model.getAllPainRecords().observe(getViewLifecycleOwner(), new
                        Observer<List<PainRecord>>() {
                            @Override
                            public void onChanged(List<PainRecord> painRecords) {

                                for(PainRecord temp : painRecords){
                                    if(date.equals(temp.getDate())){
                                        Toast.makeText(getActivity(),"One entry allowed per day",Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            }

                        });
                temperature = Weather.getInstance().getTemperature();
                if(temperature == null){
                    Toast.makeText(getActivity(),"Weather API is not working",Toast.LENGTH_SHORT).show();
                    return;
                }
                humidity = Weather.getInstance().getHumidity();
                if(humidity == null){
                    Toast.makeText(getActivity(),"Weather API is not working",Toast.LENGTH_SHORT).show();
                    return;
                }
                pressure = Weather.getInstance().getPressure();
                if(pressure == null){
                    Toast.makeText(getActivity(),"Weather API is not working",Toast.LENGTH_SHORT).show();
                    return;
                }
                email = User.getInstance().getEmail();

                //System.out.println("Intensity: " + intensity);
                //System.out.println("Location: " + location);
                //System.out.println("Mood: " + mood);
                //System.out.println("Steps: " + steps);
                //System.out.println("Temperature: " + temperature);
                //System.out.println("Humidity: " +humidity);
                //System.out.println("Pressure: " +pressure);
                //System.out.println("Date: " +date);
                //System.out.println("Email: " +email);

                PainRecord painRecord = new PainRecord(intensity,location,mood,steps,date,temperature,humidity,pressure,email);

                //System.out.println("Intensity: " + painRecord.getIntensity());
                //System.out.println("Location: " + painRecord.getLocation());
                //System.out.println("Mood: " + painRecord.getMood());
                //System.out.println("Steps: " + painRecord.getSteps());

                model.insert(painRecord);
                Toast.makeText(getActivity(),"Save Successfully!",Toast.LENGTH_SHORT).show();
            }
        });

        binding.Button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.editText9.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"ID is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                id = Integer.parseInt(binding.editText9.getText().toString());
                //System.out.println(id);

                if(binding.editText5.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Goal is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                goal= Integer.parseInt(binding.editText5.getText().toString());
                if(binding.editText6.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Steps take is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                steps = Integer.parseInt(binding.editText6.getText().toString());

                date = Weather.getInstance().getDate();
                if(date == null){
                    Toast.makeText(getActivity(),"Weather API is not working",Toast.LENGTH_SHORT).show();
                    return;
                }
                model.getAllPainRecords().observe(getViewLifecycleOwner(), new
                        Observer<List<PainRecord>>() {
                            @Override
                            public void onChanged(List<PainRecord> painRecords) {

                                for(PainRecord temp : painRecords){
                                    if(temp.getDate().equals(date)){
                                        Toast.makeText(getActivity(),"One enrtry allowed per day!",Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            }

                        });

                temperature = Weather.getInstance().getTemperature();
                if(temperature == null){
                    Toast.makeText(getActivity(),"Weather API is not working",Toast.LENGTH_SHORT).show();
                    return;
                }
                humidity = Weather.getInstance().getHumidity();
                if(humidity == null){
                    Toast.makeText(getActivity(),"Weather API is not working",Toast.LENGTH_SHORT).show();
                    return;
                }
                pressure = Weather.getInstance().getPressure();
                if(pressure == null){
                    Toast.makeText(getActivity(),"Weather API is not working",Toast.LENGTH_SHORT).show();
                    return;
                }

                email = User.getInstance().getEmail();

                //System.out.println("Intensity: " + intensity);
                //System.out.println("Location: " + location);
                //System.out.println("Mood: " + mood);
                //System.out.println("Steps: " + steps);
                //System.out.println("Temperature: " + temperature);
                //System.out.println("Humidity: " +humidity);
                //System.out.println("Pressure: " +pressure);
                //System.out.println("Date: " +date);
                //System.out.println("Email: " +email);

                model.updateByID(id,intensity,location,mood,steps,date,temperature,humidity,pressure,email);
                Toast.makeText(getActivity(),"Edit Successfully!",Toast.LENGTH_SHORT).show();


            }
        });

        return view;
    }

    public List<Map<String, Object>> getListData() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("npic", R.drawable.verygood);
        map.put("namepic", "very good");
        list.add(map);

        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("npic", R.drawable.good);
        map2.put("namepic", "good");
        list.add(map2);

        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("npic", R.drawable.average);
        map3.put("namepic", "average");
        list.add(map3);

        HashMap<String, Object> map4 = new HashMap<String, Object>();
        map4.put("npic", R.drawable.low);
        map4.put("namepic", "low");
        list.add(map4);

        HashMap<String, Object> map5 = new HashMap<String, Object>();
        map5.put("npic", R.drawable.verylow);
        map5.put("namepic", "very low");
        list.add(map5);

        return list;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}