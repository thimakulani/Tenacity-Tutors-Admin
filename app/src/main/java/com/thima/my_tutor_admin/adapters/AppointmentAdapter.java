package com.thima.my_tutor_admin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.dialogs.AcceptDialog;
import com.thima.my_tutor_admin.models.AppointmentModel;
import com.thima.my_tutor_admin.models.StudentsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentViewHolder> {
    List<AppointmentModel> Items = new ArrayList<>();
    private final FragmentManager fm;
    public AppointmentAdapter(List<AppointmentModel> items, FragmentManager fm) {
        Items = items;
        this.fm = fm;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_appointment, parent, false);

        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {

        try {
            holder.row_app_date_time.setText(Items.get(position).getDate() + " " +Items.get(position).getTime());
            holder.row_app_status.setText(Items.get(position).getStatus());

            if(!Items.get(position).getStud_id().isEmpty())
            {
                FirebaseFirestore.getInstance()
                        .collection("Students")
                        .document(Items.get(position).getStud_id())
                        .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                if(value !=null){
                                    StudentsModel s = value.toObject(StudentsModel.class);
                                    holder.row_app_name.setText(s.getName());
                                }
                            }
                        });
            }

            if(Items.get(position).getStatus().equals("Rejected")){
                holder.row_app_btn_accept.setVisibility(View.GONE);
                holder.row_app_btn_decline.setVisibility(View.GONE);
            }
            if(Items.get(position).getStatus().equals("Approved")){
                holder.row_app_btn_accept.setVisibility(View.GONE);
                holder.row_app_btn_decline.setVisibility(View.GONE);
            }
            holder.row_app_btn_accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("status", "Approved");
                    FirebaseFirestore
                            .getInstance()
                            .collection("Appointments")
                            .document(Items.get(position).getId())
                            .update(data);
                }
            });
            holder.row_app_btn_decline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("status", "Approved");
                    FirebaseFirestore
                            .getInstance()
                            .collection("Rejected")
                            .document(Items.get(position).getId())
                            .update(data);
                }
            });
        }
        catch (Exception ex){
            Toast.makeText(holder.itemView.getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return Items.size();
    }
}
class AppointmentViewHolder extends RecyclerView.ViewHolder {

    public MaterialTextView row_app_name;
    public MaterialTextView row_app_tutor_name;
    public MaterialTextView row_app_date_time;
    public MaterialTextView row_app_status;
    public MaterialButton row_app_btn_accept;
    public MaterialButton row_app_btn_decline;
    public AppointmentViewHolder(@NonNull View itemView) {
        super(itemView);
        row_app_tutor_name = (MaterialTextView)itemView.findViewById(R.id.row_app_tutor_name);
        row_app_name = (MaterialTextView)itemView.findViewById(R.id.row_app_name);
        row_app_date_time = (MaterialTextView)itemView.findViewById(R.id.row_app_date_time);
        row_app_status = (MaterialTextView)itemView.findViewById(R.id.row_app_status);
        row_app_btn_accept = (MaterialButton)itemView.findViewById(R.id.row_app_accept);
        row_app_btn_decline = (MaterialButton)itemView.findViewById(R.id.row_app_btn_decline);
    }
}
