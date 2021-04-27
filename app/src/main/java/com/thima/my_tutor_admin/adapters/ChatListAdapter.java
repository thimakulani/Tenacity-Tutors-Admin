package com.thima.my_tutor_admin.adapters;

import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.dialogs.MessegingDialogFragment;
import com.thima.my_tutor_admin.models.StudentsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListViewHolder> {
    List<String> Items;
    androidx.fragment.app.FragmentManager fm;
    public ChatListAdapter(List<String> items, androidx.fragment.app.FragmentManager childFragmentManager) {
        Items = items;
        fm = childFragmentManager;
    }
    @NonNull
    @Override
    public ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat_list, parent, false);
        return new ChatListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListViewHolder holder, int position)
    {
        FirebaseFirestore.getInstance()
                .collection("Students")
                .document(Items.get(position))
                .addSnapshotListener((value, error) -> {
                    if(value != null) {
                        StudentsModel std = value.toObject(StudentsModel.class);
                        holder.txt_row_name.setText(String.format("%s %s", Objects.requireNonNull(std).getName(), std.getSurname()));
                    }
                });

        holder.itemView.setOnClickListener(v -> {
            MessegingDialogFragment messegingDialogFragment = new MessegingDialogFragment(Items.get(position));
            messegingDialogFragment.show(fm.beginTransaction(), "");
        });
    }

    @Override
    public int getItemCount() {
        return Items.size();
    }
}
class ChatListViewHolder extends RecyclerView.ViewHolder{
    public MaterialTextView txt_row_name;
    public ChatListViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_row_name = itemView.findViewById(R.id.row_chat_list_name);

    }
}
