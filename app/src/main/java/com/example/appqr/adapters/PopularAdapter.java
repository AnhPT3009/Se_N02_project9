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
import com.example.appqr.listener.EventClickListener;
import com.example.appqr.models.Sk;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.MyViewHolder> {
    private List<Sk> skList;
    private EventClickListener listener;

    public PopularAdapter(List<Sk> skList, EventClickListener listener) {
        this.skList = skList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Sk sk = skList.get(position);
        holder.bind(sk);
    }

    @Override
    public int getItemCount() {
        return skList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView image1;
        private TextView eventName;
        private EventClickListener listener;

        public MyViewHolder(@NonNull View itemView, EventClickListener listener) {
            super(itemView);
            image1 = itemView.findViewById(R.id.image_1);
            eventName = itemView.findViewById(R.id.event_name);
            this.listener = listener;
        }

        public void bind(Sk sk) {
            Glide.with(itemView).load(sk.getPicture()).into(image1);
            eventName.setText(sk.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPopularClick(sk);
                }
            });
        }
    }
}
