package com.thima.my_tutor_admin.adapters;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.models.MessageModel;

import java.util.ArrayList;
import java.util.List;

public class MessagingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<MessageModel> Items;

    public MessagingAdapter(List<MessageModel> items) {
        Items = new ArrayList<>();
        Items = items;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
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
    public int getItemViewType(int position) {
        if(Items.get(position).getUid().equals(FirebaseAuth.getInstance().getUid()))
        {
            return R.layout.row_sent;
        }
        else{
            return R.layout.row_recieved;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() == R.layout.row_recieved){
            MessageReceiveViewHolder v_type = (MessageReceiveViewHolder) holder;
            v_type.row_received_message.setText(Items.get(position).getText());
        }
        else{
            MessageSentViewHolder v_type = (MessageSentViewHolder) holder;
            v_type.row_sent_message.setText(Items.get(position).getText());
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
