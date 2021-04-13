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
import com.thima.my_tutor_admin.models.Communique;

import java.util.ArrayList;
import java.util.List;

public class CommuniqueAdapter extends RecyclerView.Adapter<CommuniqueViewHolder> {
    private List<Communique> Items = new ArrayList<>();

    public CommuniqueAdapter(List<Communique> items) {
        Items = items;
    }

    @NonNull
    @Override
    public CommuniqueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_communique, parent, false);

        return new CommuniqueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommuniqueViewHolder holder, int position) {
        holder.txt_dates.setText(Items.get(position).getDatetime());
        holder.txt_message.setText(Items.get(position).getMessage());
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
                                        .collection("Announcements")
                                        .document(Items.get(position).getId())
                                        .delete();
                                sDialog.setTitleText("Deleted!")
                                        .setContentText("Your imaginary file has been deleted!")
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
class CommuniqueViewHolder extends RecyclerView.ViewHolder {

    public MaterialTextView txt_message;
    public MaterialTextView txt_dates;
    public FloatingActionButton fab_delete;
    public CommuniqueViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_dates = itemView.findViewById(R.id.row_communique_dates);
        txt_message = itemView.findViewById(R.id.row_communique_message);
        fab_delete = itemView.findViewById(R.id.row_communique_fab_delete);
    }
}
