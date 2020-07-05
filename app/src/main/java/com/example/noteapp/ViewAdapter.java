package com.example.noteapp;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {
    LayoutInflater inflate;
    List<Note> notes;

    public ViewAdapter(Context context, List<Note> notes) {
        this.inflate = LayoutInflater.from(context);
        this.notes = notes;

    }

    public void updateNotes(List<Note> notes) {
        this.notes = notes;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflate.inflate(R.layout.new_view_notes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter.ViewHolder holder, int position) {
        String title = notes.get(position).getTitle();
        String date = notes.get(position).getDate();
        String time = notes.get(position).getTime();
        //long id = notes.get(position).getID();
        holder.title.setText(title);
        holder.date.setText(date);
        holder.time.setText(time);
        holder.img.setImageResource(R.drawable.ic_baseline_delete_24);
    }

    @Override
    public int getItemCount() {
        System.out.println(notes.size()+"hallelujah");
        return notes.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, date, time;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cardTitle);
            date = itemView.findViewById(R.id.cardDate);
            time = itemView.findViewById(R.id.cardTime);
            img = itemView.findViewById(R.id.imageView);
            //Log.d("hello","hi");
            //System.out.println("yessir");
        }

    }
}
