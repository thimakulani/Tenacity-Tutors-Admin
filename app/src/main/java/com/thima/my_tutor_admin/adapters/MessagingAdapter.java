package com.thima.my_tutor_admin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.models.MessageModel;

import java.util.ArrayList;
import java.util.List;

public class MessagingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<MessageModel> Items = new ArrayList<>();

    public MessagingAdapter(List<MessageModel> items) {
        Items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (Items.get(position).getSender_id() != ""){
            return R.layout.row_recieved;
        }else{
            return R.layout.row_sent;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == R.layout.row_recieved){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recieved, parent, false);
            return new MessageReceiveViewHolder(view);
        }
        else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sent, parent, false);
            return new MessageSentViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() == R.layout.row_sent){
            MessageReceiveViewHolder v_type = (MessageReceiveViewHolder) holder;

        }
        else{
            MessageSentViewHolder v_type = (MessageSentViewHolder) holder;

        }
    }

    @Override
    public int getItemCount() {
        return Items.size();
    }
}
class MessageSentViewHolder extends RecyclerView.ViewHolder{
    public MaterialTextView row_sent_date;
    public MaterialTextView row_sent_message;
    public MessageSentViewHolder(@NonNull View itemView) {
        super(itemView);
        row_sent_message = (MaterialTextView)itemView.findViewById(R.id.row_sent_message);
    }
}
class MessageReceiveViewHolder extends RecyclerView.ViewHolder{
    public MaterialTextView row_received_date;
    public MaterialTextView row_received_message;
    public MessageReceiveViewHolder(@NonNull View itemView) {
        super(itemView);
        row_received_message = (MaterialTextView)itemView.findViewById(R.id.row_received_message);
    }
}
