package com.example.PersonalisedMobilePainDiary.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Data;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;

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
import com.example.PersonalisedMobilePainDiary.bean.AlarmReceiver;
import com.example.PersonalisedMobilePainDiary.bean.FirebaseWorker;
import com.example.PersonalisedMobilePainDiary.database.PainRecordDatabase;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentPainBinding;
import com.example.PersonalisedMobilePainDiary.entity.PainRecord;
import com.example.PersonalisedMobilePainDiary.viewmodel.PainRecordViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    String date2;
    String temperature;
    String humidity;
    String pressure;
    String email;
    int id;
    String[] date_room = new String[14400];
    int m = 0;
    int n = 0;
    boolean flag = true;

    Data data1 = null;
    Data data2 = null;
    Data data3 = null;
    Data data4 = null;
    Data data5 = null;
    Data data6 = null;
    Data data7 = null;
    Data data8 = null;
    Data data9 = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        model = new ViewModelProvider(requireActivity()).get(PainRecordViewModel.class);
        model.initalizeVars(getActivity());

        model.getAllPainRecords().observe(getViewLifecycleOwner(), new
                Observer<List<PainRecord>>() {
                    @Override
                    public void onChanged(List<PainRecord> painRecords) {

                        for(PainRecord temp : painRecords){
                            //System.out.println("Date in the room database: " + temp.getDate());
                            date_room[m++] = temp.getDate();
                        }
                        n = m;
                    }

                });

        ArrayAdapter<String> Adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,array1);
        binding.Spinner1.setPrompt("Pain intensity level");
        binding.Spinner1.setAdapter(Adapter1);
        binding.Spinner1.setSelection(0,true);
        binding.Spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"Pain intensity level that you choose is ："+array1[position],Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(),"The location of pain that you choose is ："+array2[position],Toast.LENGTH_SHORT).show();
                location = array2[position];
                //System.out.println(location);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SimpleAdapter simpleAdapter =new SimpleAdapter(getActivity(), getListData(), R.layout.clip,
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
                //System.out.println(mood);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(location==null){
                    Toast.makeText(getActivity(),"Pain Location is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mood==null){
                    Toast.makeText(getActivity(),"Mood is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(binding.editText5.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Goal is empty, take the defalut value " + goal,Toast.LENGTH_SHORT).show();
                }else{
                    goal= Integer.parseInt(binding.editText5.getText().toString());
                }
                //System.out.println("Goal set: "+goal);
                Steps.getInstance().setGoal(goal);

                if(binding.editText6.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Steps take is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                steps = Integer.parseInt(binding.editText6.getText().toString());
                //System.out.println("Steps_taken set: "+steps);
                Steps.getInstance().setSteps_taken(steps);


                temperature = Weather.getInstance().getTemperature();
                if(temperature == null){
                    Toast.makeText(getActivity(),"Weather API is not working",Toast.LENGTH_SHORT).show();
                    return;
                }
                humidity = Weather.getInstance().getHumidity();

                pressure = Weather.getInstance().getPressure();

                for(int i=0;i<n;i++){
                    if(Weather.getInstance().getDate().equals(date_room[i])){
                        flag = false;
                    }
                    //System.out.println("Date in room: " + date_room[i]);
                }

                if(flag == false){
                    Toast.makeText(getActivity(),"One entry allowed per day",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    date = Weather.getInstance().getDate();
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

                data1 = new Data.Builder().putInt("intensity",intensity).build();
                data2 = new Data.Builder().putString("location",location).build();
                data3 = new Data.Builder().putString("mood",mood).build();
                data4 = new Data.Builder().putInt("steps",steps).build();
                data5 = new Data.Builder().putString("date",date).build();
                data6 = new Data.Builder().putString("temperature",temperature).build();
                data7 = new Data.Builder().putString("humidity",humidity).build();
                data8 = new Data.Builder().putString("pressure",pressure).build();
                data9 = new Data.Builder().putString("email",email).build();
                PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(FirebaseWorker.class,15, TimeUnit.MINUTES)
                        .setInputData(data1)
                        .setInputData(data2)
                        .setInputData(data3)
                        .setInputData(data4)
                        .setInputData(data5)
                        .setInputData(data6)
                        .setInputData(data7)
                        .setInputData(data8)
                        .setInputData(data9)
                        .build();
                WorkManager.getInstance(getActivity()).enqueue(periodicWorkRequest);


                Toast.makeText(getActivity(),"Save Successfully!",Toast.LENGTH_SHORT).show();
            }
        });

        binding.Button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                if(binding.editText9.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"ID is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                id = Integer.parseInt(binding.editText9.getText().toString());
                */
                if(location==null){
                    Toast.makeText(getActivity(),"Location is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mood==null){
                    Toast.makeText(getActivity(),"Mood is empty",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(binding.editText9.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Date is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                date2 = binding.editText9.getText().toString();

                if(binding.editText5.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Goal is empty, take the defalut value " + goal,Toast.LENGTH_SHORT).show();
                }else{
                    goal= Integer.parseInt(binding.editText5.getText().toString());
                }
                //System.out.println("Goal set: "+goal);
                Steps.getInstance().setGoal(goal);

                if(binding.editText6.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Steps take is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                steps = Integer.parseInt(binding.editText6.getText().toString());
                //System.out.println("Steps_taken set: "+steps);
                Steps.getInstance().setSteps_taken(steps);

                temperature = Weather.getInstance().getTemperature();
                if(temperature == null){
                    Toast.makeText(getActivity(),"Weather API is not working",Toast.LENGTH_SHORT).show();
                    return;
                }

                date = Weather.getInstance().getDate();

                humidity = Weather.getInstance().getHumidity();

                pressure = Weather.getInstance().getPressure();

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

                //model.updateByID(id,intensity,location,mood,steps,date,temperature,humidity,pressure,email);
                model.updateByDate(date2, intensity,location,mood,steps,date2,temperature,humidity,pressure,email);
                Toast.makeText(getActivity(),"Edit Finish",Toast.LENGTH_SHORT).show();


            }
        });

        binding.pick.setIs24HourView(true);

        binding.Button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,binding.pick.getHour());
                calendar.set(Calendar.MINUTE,binding.pick.getMinute()-2);
                calendar.set(Calendar.SECOND,0);
                AlarmManager manager= (AlarmManager) getActivity().getSystemService(getContext().ALARM_SERVICE);
                manager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                Toast.makeText(getActivity(), "Clock is set", Toast.LENGTH_SHORT).show();

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