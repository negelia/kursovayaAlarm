package com.example.kursovayaalarm;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>  {
    private final LayoutInflater inflater;
    private final List<Model> movies;

    RecyclerAdapter(Context context, List<Model> movies) {
        this.movies = movies;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        Model state = movies.get(position);
        holder.nameMovie.setText(state.getName());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameMovie;
        ViewHolder(View view){
            super(view);
            nameMovie = itemView.findViewById(R.id.txt);
        }
    }
}

