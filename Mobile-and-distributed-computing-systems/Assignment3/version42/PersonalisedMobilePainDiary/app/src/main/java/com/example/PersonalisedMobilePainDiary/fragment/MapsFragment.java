package com.example.PersonalisedMobilePainDiary.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.PersonalisedMobilePainDiary.R;
import com.example.PersonalisedMobilePainDiary.databinding.FragmentMapsBinding;
import com.mapbox.api.geocoding.v5.MapboxGeocoding;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.api.geocoding.v5.models.GeocodingResponse;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerView;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerViewManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


public class MapsFragment extends Fragment {
    private FragmentMapsBinding binding;
    public MapsFragment(){}
    private MapView mapView;
    private MarkerViewManager markerViewManager;
    private static final String TAG = "mapstag";
    String location;
    String longitude;
    String latitude;
    double longitude_double;
    double latitude_double;

    String query_location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String token = getString(R.string.mapbox_access_token);
        Mapbox.getInstance(getActivity(), token);

        super.onCreate(savedInstanceState);
        binding = FragmentMapsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        //getActivity().setContentView(R.layout.activity_main);

        binding.Button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.editText7.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Location is empty",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    query_location = binding.editText7.getText().toString();
                }



                MapboxGeocoding mapboxGeocoding = MapboxGeocoding.builder()
                        .accessToken("pk.eyJ1IjoiaGFvd29uZzE5OTciLCJhIjoiY2tvOXNlaXZ6MDM1MTJ1c2F6bXJmbzdmeCJ9.eXGnKiBcuAwqdbOMmLo-TQ")
                        .query(query_location)
                        .build();


                mapboxGeocoding.enqueueCall(new retrofit2.Callback<GeocodingResponse>() {
                    @Override
                    public void onResponse(Call<GeocodingResponse> call, Response<GeocodingResponse> response) {

                        List<CarmenFeature> results = response.body().features();

                        if (results.size() > 0) {

                            // Log the first results Point.
                            Point firstResultPoint = results.get(0).center();
                            //System.out.println(firstResultPoint.coordinates().toString());
                            location = firstResultPoint.coordinates().toString();
                            longitude = location.substring(1, location.indexOf(","));
                            longitude_double = Double.parseDouble(longitude);
                            //System.out.println("longitude_double_1: " + longitude_double);
                            latitude = location.substring(location.indexOf(",")+2, location.length()-1);
                            latitude_double = Double.parseDouble(latitude);
                            //System.out.println("latitude_double: " + latitude_double);

                            Log.d(TAG, "onResponse: " + firstResultPoint.toString());

                        } else {

                            // No result for your request were found.
                            Log.d(TAG, "onResponse: No result found");

                        }
                    }

                    @Override
                    public void onFailure(Call<GeocodingResponse> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

                final LatLng latLng= new LatLng(latitude_double, longitude_double);
                mapView = (MapView) binding.mapView;
                mapView.onCreate(savedInstanceState);
                mapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                            @Override
                            public void onStyleLoaded(@NonNull Style style) {
                                markerViewManager = new MarkerViewManager(mapView, mapboxMap);

                                CameraPosition position = new CameraPosition.Builder()
                                        .target(latLng)
                                        .zoom(13)
                                        .build();
                                mapboxMap.setCameraPosition(position);

                                /*
                                mapboxMap.addMarker(new MarkerOptions()
                                        .position(latLng)
                                        .title("Here you are"));

                                 */


                                View customView = LayoutInflater.from(getActivity()).inflate(
                                        R.layout.marker_view_bubble, null);
                                customView.setLayoutParams(new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
                                TextView titleTextView = customView.findViewById(R.id.marker_window_title);
                                titleTextView.setText("Here is");

                                TextView snippetTextView = customView.findViewById(R.id.marker_window_snippet);
                                snippetTextView.setText("Your location");

                                MarkerView markerView = new MarkerView(latLng, customView);

                                markerViewManager.addMarker(markerView);
                            }
                        });
                    }
                });

            }
        });

        double x = 39.906864375;
        double y = 116.391296125;

        //System.out.println("longitude_double_2: " + longitude_double);
        //System.out.println("y: " + y);
//lat and long are hardcoded here but could be provided at run time
        final LatLng latLng= new LatLng(x, y);
        mapView = (MapView) binding.mapView;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        CameraPosition position = new CameraPosition.Builder()
                                .target(latLng)
                                .zoom(13)
                                .build();
                        mapboxMap.setCameraPosition(position);

                    }
                });
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (markerViewManager != null) {
            markerViewManager.onDestroy();
        }
        mapView.onDestroy();
    }


}