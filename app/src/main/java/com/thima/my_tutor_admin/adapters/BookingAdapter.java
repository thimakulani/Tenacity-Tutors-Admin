package com.thima.my_tutor_admin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.models.AppointmentModel;

import java.util.ArrayList;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingViewHolder> {
    List<AppointmentModel> Items = new ArrayList<>();

    public BookingAdapter(List<AppointmentModel> items) {
        Items = items;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return Items.size();
    }
}
class BookingViewHolder extends RecyclerView.ViewHolder {

    public MaterialTextView row_booking_name;
    public MaterialTextView row_booking_subject;
    public MaterialTextView row_booking_tutor_name;
    public MaterialTextView row_booking_date_time;
    public MaterialTextView row_booking_status;
    public MaterialButton row_booking_btn_accept;
    public MaterialButton row_booking_btn_decline;
    public BookingViewHolder(@NonNull View itemView) {
        super(itemView);
        row_booking_subject = (MaterialTextView)itemView.findViewById(R.id.row_booking_subject);
        row_booking_name = (MaterialTextView)itemView.findViewById(R.id.row_booking_name);
        row_booking_date_time = (MaterialTextView)itemView.findViewById(R.id.row_booking_date_time);
        row_booking_status = (MaterialTextView)itemView.findViewById(R.id.row_booking_status);
        row_booking_btn_accept = (MaterialButton)itemView.findViewById(R.id.row_booking_accept);
        row_booking_btn_decline = (MaterialButton)itemView.findViewById(R.id.row_booking_btn_decline);
    }
}
