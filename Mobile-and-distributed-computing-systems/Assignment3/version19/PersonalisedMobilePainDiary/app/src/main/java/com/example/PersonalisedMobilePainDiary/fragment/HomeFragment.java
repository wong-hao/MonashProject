package com.example.PersonalisedMobilePainDiary.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.PersonalisedMobilePainDiary.User;
import com.example.PersonalisedMobilePainDiary.Weather;
import com.example.PersonalisedMobilePainDiary.bean.GetWeather;
import com.example.PersonalisedMobilePainDiary.bean.RequestServices;
import com.example.PersonalisedMobilePainDiary.databinding.HomeFragmentBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private HomeFragmentBinding binding;
    public HomeFragment(){}
    String temperature;
    String humidity;
    String pressure;
    String obsTime;
    String date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        getTodayWeather();

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //获取今天的天气数据
    private void getTodayWeather(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://devapi.qweather.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.i("LHD","1");
        RequestServices requestServices = retrofit.create(RequestServices.class);
        Call<ResponseBody> call = requestServices.getString();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        //Log.i("LHD",response.body().toString());
                        String result = response.body().string();
                        //System.out.println(result);

                        JSONObject jsonObject = new JSONObject(result);
                        JSONObject now = jsonObject.getJSONObject("now");

                        temperature = now.getString("temp");
                        humidity = now.getString("humidity");
                        pressure = now.getString("pressure");
                        obsTime = now.getString("obsTime");
                        date = obsTime.substring(0,10);
                        //System.out.println(temperature);
                        //System.out.println(humidity);
                        //System.out.println(pressure);
                        //System.out.println(obstime);

                        binding.textView4.setText("Temperature (°C): " + temperature);
                        binding.textView5.setText("Humidity (%): " +  humidity);
                        binding.textView6.setText("Pressure (KPa): " + pressure);
                        binding.textView7.setText("Current date: " + date);
                        binding.textView8.setText("The precise update time: " + obsTime);

                        Weather.getInstance().setMessage(temperature,humidity,pressure,date);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("LHD","访问失败");

            }
        });

    }

}