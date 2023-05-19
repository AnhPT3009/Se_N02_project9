package com.example.appqr.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.appqr.R;
import com.example.appqr.models.Sk;

import java.util.List;

public class SkAdapter extends RecyclerView.Adapter<SkAdapter.MyViewHolder> {
     private List<Sk> s;


    public SkAdapter(List<Sk> sklist) {
        s = sklist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Sk sk = s.get(position);
        holder.bind(sk);
    }

    @Override
    public int getItemCount() {
        return s.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_3);
            textView = itemView.findViewById(R.id.event_nameee);
        }

        public void bind(Sk sk) {
            textView.setText(sk.getName());
            Glide.with(itemView.getContext()).load(sk.getPicture()).into(imageView);
        }
    }}
