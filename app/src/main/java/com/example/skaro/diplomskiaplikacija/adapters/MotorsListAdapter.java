package com.example.skaro.diplomskiaplikacija.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.skaro.diplomskiaplikacija.R;
import com.example.skaro.diplomskiaplikacija.entities.Motor;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Skaro on 31.07.2016..
 */
public class MotorsListAdapter extends RecyclerView.Adapter<MotorsListAdapter.ViewHolder> {

    List<Motor> motorsList;
    private OnMotorClickListener listener;

    public MotorsListAdapter(OnMotorClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Motor motor = motorsList.get(position);
        holder.motorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(motor);
            }
        });
        holder.motorNameTextView.setText(motor.name);
        // TODO change resize height and width
        Picasso.with(holder.itemView.getContext()).load(motor.imageURL).resize(300, 300).centerCrop().into(holder.motorImageView);

    }

    @Override
    public int getItemCount() {
        if (null == motorsList) return 0;
        return motorsList.size();
    }

    public void setMotorsList(List<Motor> motorsList) {
        this.motorsList = motorsList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView motorImageView;
        public TextView motorNameTextView;
        public RelativeLayout motorLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            motorNameTextView = (TextView) itemView.findViewById(R.id.motor_name);
            motorImageView = (ImageView) itemView.findViewById(R.id.motor_image_view);
            motorLayout = (RelativeLayout) itemView.findViewById(R.id.motor_layout);
        }
    }

    public interface OnMotorClickListener {
        void onClick(Motor motor);
    }
}



