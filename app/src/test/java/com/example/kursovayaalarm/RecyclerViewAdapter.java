package com.example.kursovayaalarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Model> results;

    public RecyclerViewAdapter(Context context, List<Model> results) {
        this.inflater = LayoutInflater.from(context);
        this.results = results;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model model = results.get(position);
        holder.alarmNameTest.setText(model.getAlarmName());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView alarmNameTest;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            alarmNameTest = itemView.findViewById(R.id.swither);
        }
    }
}
