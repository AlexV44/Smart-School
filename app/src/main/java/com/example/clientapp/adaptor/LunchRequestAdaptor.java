package com.example.clientapp.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientapp.R;
import com.example.clientapp.model.LunchRequest;

import java.util.List;

public class LunchRequestAdaptor extends RecyclerView.Adapter<LunchRequestAdaptor.ViewHolder> {
    private List<LunchRequest> lunchRequestList;

    public LunchRequestAdaptor(List<LunchRequest> lunchRequestList) {
        this.lunchRequestList = lunchRequestList;
    }

    @NonNull
    @Override
    public LunchRequestAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_lunch_requests, parent, false);
        return new LunchRequestAdaptor.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull LunchRequestAdaptor.ViewHolder holder, int position) {
        holder.classNameRequest.setText(String.valueOf(lunchRequestList.get(position).getClassName()));
        holder.studentsCountRequest.setText(String.valueOf(lunchRequestList.get(position).getStudentsQuantity()));
        holder.freeStudentsCountRequest.setText(String.valueOf(lunchRequestList.get(position).getFreeStudentsQuantity()));
        holder.requestTime.setText(String.valueOf(lunchRequestList.get(position).getRequestTime()));
    }

    @Override
    public int getItemCount() {
        return lunchRequestList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView classNameRequest, studentsCountRequest, freeStudentsCountRequest, requestTime;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            classNameRequest = itemView.findViewById(R.id.classNameRequest);
            studentsCountRequest = itemView.findViewById(R.id.studentsCountRequest);
            freeStudentsCountRequest = itemView.findViewById(R.id.freeStudentsCountRequest);
            requestTime = itemView.findViewById(R.id.requestTime);
        }
    }
}
