package com.example.PersonalisedMobilePainDiary.bean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestServices {
    //请求方式为GET，参数为basil2style，因为没有变量所以下面getString方法也不需要参数
    @GET("v7/weather/now?key=f2cd136515814b24895759a101dce318&location=120.74686,31.26857")
    //定义返回的方法，返回的响应体使用了ResponseBody
    Call<ResponseBody> getString();

}
