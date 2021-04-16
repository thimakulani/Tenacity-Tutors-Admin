package com.thima.my_tutor_admin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.models.SubjectsModel;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectViewHolder> {
    private List<SubjectsModel> Items = new ArrayList<>();

    public SubjectAdapter(List<SubjectsModel> items) {
        Items = items;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_subject, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        holder.txt_subject_name.setText(Items.get(position).getSubject());
        holder.fab_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                new SweetAlertDialog(holder.itemView.getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this!")
                        .setConfirmText("Yes, delete it!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog)
                            {
                                FirebaseFirestore.getInstance()
                                        .collection("Subjects")
                                        .document(Items.get(position).getId())
                                        .delete();
                                sDialog.setTitleText("Deleted!")
                                        .setContentText("Your subject has been deleted!")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Items.size();
    }
}
class SubjectViewHolder extends RecyclerView.ViewHolder{
    public MaterialTextView txt_subject_name;
    public FloatingActionButton fab_delete;
    public SubjectViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_subject_name = itemView.findViewById(R.id.row_txt_subject_name);
        fab_delete = itemView.findViewById(R.id.row_fab_delete_subject);
    }
}