package com.thima.my_tutor_admin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.models.CommuniqueModel;

import java.util.ArrayList;
import java.util.List;

public class CommuniqueAdapter extends RecyclerView.Adapter<CommuniqueViewHolder> {
    List<CommuniqueModel> Items = new ArrayList<>();

    public CommuniqueAdapter(List<CommuniqueModel> items) {
        Items = items;
    }

    @NonNull
    @Override
    public CommuniqueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_students, parent, false);

        return new CommuniqueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommuniqueViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return Items.size();
    }
}
class CommuniqueViewHolder extends RecyclerView.ViewHolder {

    public CommuniqueViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
