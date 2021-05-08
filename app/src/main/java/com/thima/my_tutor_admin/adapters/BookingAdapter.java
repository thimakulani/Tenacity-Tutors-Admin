package com.thima.my_tutor_admin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.dialogs.TutoringDialogFragment;
import com.thima.my_tutor_admin.models.AppointmentModel;
import com.thima.my_tutor_admin.models.StudentsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookingAdapter extends RecyclerView.Adapter<BookingViewHolder> {
    List<AppointmentModel> Items = new ArrayList<>();
    private FragmentManager fm;

    public BookingAdapter(List<AppointmentModel> items, FragmentManager supportFragmentManager) {
        Items = items;
        fm = supportFragmentManager;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        /*if(Items.get(position).getStatus().equals("Approved")){
            holder.row_booking_btn_accept.setVisibility(View.VISIBLE);
            holder.row_booking_btn_decline.setVisibility(View.VISIBLE);
        }
        else{*/
            holder.row_booking_btn_accept.setVisibility(View.GONE);
            holder.row_booking_btn_decline.setVisibility(View.GONE);
     //   }
        holder.row_booking_date_time.setText(String.format("%s %s", Items.get(position).getDate(), Items.get(position).getTime()));
        holder.row_booking_status.setText(Items.get(position).getStatus());
        holder.row_booking_grade.setText(Items.get(position).getGrade());
        holder.row_booking_subject.setText(Items.get(position).getSubject());
        if(Items.get(position).getStud_id().length()>1){
            FirebaseFirestore.getInstance()
                    .collection("Students")
                    .document(Items.get(position).getStud_id())
                    .addSnapshotListener((value, error) -> {
                        if(value !=null){
                            StudentsModel s = value.toObject(StudentsModel.class);
                            holder.row_booking_name.setText(Objects.requireNonNull(s).getName());
                        }
                    });
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TutoringDialogFragment(Items.get(position))
                        .show(fm.beginTransaction(), "");

            }
        });

    }

    @Override
    public int getItemCount() {
        return Items.size();
    }
}
class BookingViewHolder extends RecyclerView.ViewHolder {

    public MaterialTextView row_booking_name;
    public MaterialTextView row_booking_grade;
    public MaterialTextView row_booking_subject;
    public MaterialTextView row_booking_date_time;
    public MaterialTextView row_booking_status;
    public MaterialButton row_booking_btn_accept;
    public MaterialButton row_booking_btn_decline;
    public BookingViewHolder(@NonNull View itemView) {
        super(itemView);
        row_booking_subject = (MaterialTextView)itemView.findViewById(R.id.row_booking_subject);
        row_booking_grade = (MaterialTextView)itemView.findViewById(R.id.row_booking_grade);
        row_booking_name = (MaterialTextView)itemView.findViewById(R.id.row_booking_name);
        row_booking_date_time = (MaterialTextView)itemView.findViewById(R.id.row_booking_date_time);
        row_booking_status = (MaterialTextView)itemView.findViewById(R.id.row_booking_status);
        row_booking_btn_accept = (MaterialButton)itemView.findViewById(R.id.row_booking_accept);
        row_booking_btn_decline = (MaterialButton)itemView.findViewById(R.id.row_booking_btn_decline);
    }
}
