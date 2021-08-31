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
       holder.textView9.setText(String.valueOf(data.get(position).getId()));

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

        public RecycleViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            textView9 = itemView.findViewById(R.id.textView9);
        }
    }
}
