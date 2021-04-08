package com.thima.my_tutor_admin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.models.StudentsModel;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter  extends RecyclerView.Adapter<StudentHolder>{
    List<StudentsModel> Items = new ArrayList<>();

    public StudentAdapter(List<StudentsModel> items) {
        Items = items;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_students, parent, false);
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        holder.txt_names.setText(Items.get(position).getName());
        holder.txt_surname.setText(Items.get(position).getSurname());
    }

    @Override
    public int getItemCount() {
        return Items.size();
    }
}
class StudentHolder extends RecyclerView.ViewHolder{
    public MaterialTextView txt_names;
    public MaterialTextView txt_surname;
    public StudentHolder(@NonNull View itemView) {
        super(itemView);
        txt_names = itemView.findViewById(R.id.row_stud_name);
        txt_surname = itemView.findViewById(R.id.row_stud_surname);
    }
}
