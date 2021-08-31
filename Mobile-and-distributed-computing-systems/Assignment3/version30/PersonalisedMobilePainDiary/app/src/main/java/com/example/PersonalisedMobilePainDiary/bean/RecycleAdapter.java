package com.example.PersonalisedMobilePainDiary.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.PersonalisedMobilePainDiary.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder> {

    private List<RecycleBean> data;
    private Context context;

    public RecycleAdapter(List<RecycleBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recycleview_item,null);
        return new RecycleViewHolder(view);

        //LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //View view = layoutInflater.inflate(R.layout.recycleview_item,parent,false);
        //return new RecycleViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull RecycleAdapter.RecycleViewHolder holder, int position) {
       holder.textView9.setText("ID: " + String.valueOf(data.get(position).getId()));
       holder.textView10.setText("Pain intensity level: " + String.valueOf(data.get(position).getIntensity()));
       holder.textView11.setText("Location of pain: " + data.get(position).getLocation());
       holder.textView12.setText("Mood: " + data.get(position).getMood());
       holder.textView13.setText("Steps taken today: " + String.valueOf(data.get(position).getSteps()));
       holder.textView14.setText("Current date: " + data.get(position).getDate());
       holder.textView15.setText("Current temperature: " + data.get(position).getTemperature());
       holder.textView16.setText("Current humidity: " + data.get(position).getHumidity());
       holder.textView17.setText("Current pressure: " + data.get(position).getPressure());
       holder.textView18.setText("User email: " + data.get(position).getEmail());
       holder.textView19.setText("");

        //RecycleBean recycleBean = data.get(position);
        //System.out.println("M1S:" + recycleBean.getId());
        //System.out.println("M2S:" + recycleBean.getTemperature());
        //System.out.println("M3S:" + recycleBean.getEmail());
        //holder.textView9.setText(String.valueOf(recycleBean.getId()));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {

        TextView textView9;
        TextView textView10;
        TextView textView11;
        TextView textView12;
        TextView textView13;
        TextView textView14;
        TextView textView15;
        TextView textView16;
        TextView textView17;
        TextView textView18;
        TextView textView19;

        public RecycleViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            textView9 = itemView.findViewById(R.id.textView9);
            textView10 = itemView.findViewById(R.id.textView10);
            textView11 = itemView.findViewById(R.id.textView11);
            textView12 = itemView.findViewById(R.id.textView12);
            textView13 = itemView.findViewById(R.id.textView13);
            textView14 = itemView.findViewById(R.id.textView14);
            textView15 = itemView.findViewById(R.id.textView15);
            textView16 = itemView.findViewById(R.id.textView16);
            textView17 = itemView.findViewById(R.id.textView17);
            textView18 = itemView.findViewById(R.id.textView18);
            textView19 = itemView.findViewById(R.id.textView19);

        }
    }
}
