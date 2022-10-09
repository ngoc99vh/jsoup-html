package com.example.read_rss_jsoup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.read_rss_jsoup.MainActivity;
import com.example.read_rss_jsoup.R;
import com.example.read_rss_jsoup.RSS;

import java.util.ArrayList;

public class Adapter_Item_RSS extends RecyclerView.Adapter<Adapter_Item_RSS.ViewHolder> {
    Context context;
    ArrayList<RSS> listRss ;

    public Adapter_Item_RSS(Context context, ArrayList<RSS> listRss) {
        this.context = context;
        this.listRss = listRss;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_rss,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Item_RSS.ViewHolder holder, int position) {
        RSS rss = listRss.get(position);
        holder.title.setText(rss.getTitle());
        holder.link.setText(rss.getLink());
    }

    @Override
    public int getItemCount() {
        return listRss.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, link;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            link = itemView.findViewById(R.id.link);
        }
    }
}
