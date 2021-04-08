package com.thima.my_tutor_admin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.dialogs.MessegingDialogFragment;
import com.thima.my_tutor_admin.models.StudentsModel;

import java.util.ArrayList;
import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListViewHolder> {
    List<StudentsModel> Items = new ArrayList<>();
    public ChatListAdapter(List<StudentsModel> items) {
        Items = items;
    }
    @NonNull
    @Override
    public ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat_list, parent, false);
        return new ChatListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListViewHolder holder, int position) {
        holder.txt_row_name.setText(Items.get(position).getName() + " " + Items.get(position).getSurname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessegingDialogFragment messegingDialogFragment = new MessegingDialogFragment("uid");
            }
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
