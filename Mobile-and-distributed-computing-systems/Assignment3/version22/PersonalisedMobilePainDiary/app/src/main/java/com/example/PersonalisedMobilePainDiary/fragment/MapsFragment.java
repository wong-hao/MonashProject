package com.example.PersonalisedMobilePainDiary.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.example.PersonalisedMobilePainDiary.R;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentMapsBinding;


public class MapsFragment extends Fragment {
    private FragmentMapsBinding binding;
    public MapsFragment(){}
    String city;
    String address;
    BaiduMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMapsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //初始化搜索模块
        GeoCoder mSearch = GeoCoder.newInstance();

        OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                if (null != geoCodeResult && null != geoCodeResult.getLocation()) {
                    if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                        //没有检索到结果
                        return;
                    } else {
                        double latitude = geoCodeResult.getLocation().latitude;
                        double longitude = geoCodeResult.getLocation().longitude;
                        //System.out.println(latitude);
                        //System.out.println(longitude);
                        map = binding.bmapView.getMap();
                        LatLng ll = new LatLng(latitude,longitude);
                        MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
                        map.animateMapStatus(update);

                        BitmapDescriptor bitmap = BitmapDescriptorFactory
                                .fromResource(R.drawable.icon_marka);
                        OverlayOptions option = new MarkerOptions()
                                .position(ll)
                                .icon(bitmap);
                        map.addOverlay(option);
                    }
                }
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

            }

        };

        binding.Button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city = binding.editText7.getText().toString();
                address = binding.editText8.getText().toString();
                mSearch.setOnGetGeoCodeResultListener(listener);
                mSearch.geocode(new GeoCodeOption()
                        .city(city)
                        .address(address));
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