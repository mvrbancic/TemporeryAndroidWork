package com.example.skaro.diplomskiaplikacija;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Skaro on 31.07.2016..
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;

    List<GetDataAdapter> motorsName;

    public RecyclerViewAdapter(List<GetDataAdapter> motorsName, Context context){

        super();
        this.context = context;
        this.motorsName = motorsName;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.ImageTitleNameView.setText(motorsName.get(position).getName());

    }

    @Override
    public int getItemCount() {

        return motorsName.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ImageTitleNameView;
        public NetworkImageView networkImageView ;

        public ViewHolder(View itemView) {

            super(itemView);

            ImageTitleNameView = (TextView) itemView.findViewById(R.id.textView_item) ;

            networkImageView = (NetworkImageView) itemView.findViewById(R.id.VollyNetworkImageView1) ;

        }
    }
}



