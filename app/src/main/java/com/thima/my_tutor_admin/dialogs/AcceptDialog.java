package com.thima.my_tutor_admin.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.models.AppointmentModel;
import com.thima.my_tutor_admin.models.TutorModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AcceptDialog extends DialogFragment {


    AppointmentModel appointmentModel;
    String name;



    public AcceptDialog(AppointmentModel appointmentModel, String name) {
        this.appointmentModel = appointmentModel;
        this.name = name;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private Context ctx;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_accept_dialog, container, false);
        ctx = view.getContext();
        ConnectViews(view);

        return view;
    }
    private String tutor_id = null;
    private MaterialButton btn_select_tutor;
    private final List<TutorModel> Items = new ArrayList<>();
    private void ConnectViews(View view) {
        MaterialTextView txt_stud_name = view.findViewById(R.id.dlg_app_name);
        MaterialTextView txt_dates = view.findViewById(R.id.dlg_app_date_time);
        MaterialTextView txt_subject = view.findViewById(R.id.dlg_app_subject);
       btn_select_tutor = view.findViewById(R.id.dlg_app_select_tutor);
        MaterialButton btn_submit = view.findViewById(R.id.dlg_app_submit);
        txt_dates.setText(appointmentModel.getDate());
        txt_subject.setText(appointmentModel.getSubject());
        txt_stud_name.setText(name);

        btn_select_tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseFirestore.getInstance()
                        .collection("Tutors")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                if(!queryDocumentSnapshots.isEmpty()){
                                    Items.clear();
                                    Items.addAll(queryDocumentSnapshots.toObjects(TutorModel.class));
                                    String []arr = new String[Items.size()];
                                    int i = 0;
                                    for (TutorModel s : Items) {
                                        arr[i] = String.format("%s %s", s.getName(), s.getSurname());
                                        i++;
                                    }
                                    SelectTutor(arr, Items);
                                }
                            }
                        });
            }
        });

        btn_submit.setOnClickListener(v -> {
            Toast.makeText(ctx, tutor_id, Toast.LENGTH_SHORT).show();
            if(tutor_id != null){
                HashMap<String, Object> data = new HashMap<>();
                data.put("status", "Approved");
                data.put("tutor_id", tutor_id);
                FirebaseFirestore
                        .getInstance()
                        .collection("Appointments")
                        .document(appointmentModel.getId())
                        .update(data);
                dismiss();
            }
        });


    }
    private void SelectTutor(String[] arr, List<TutorModel> items)
    {

        //Toast.makeText(ctx, arr[0], Toast.LENGTH_SHORT).show();
        try {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
            alertDialog.setCancelable(false);
            alertDialog.setTitle("SELECT A TUTOR");
            alertDialog.setIcon(R.drawable.tenacity);
            alertDialog.setSingleChoiceItems(arr, -1, (dialog, which) -> {
                btn_select_tutor.setText(arr[which]);
                tutor_id = items.get(which).getId();
            });
            alertDialog.setPositiveButton("CONTINUE", (dialog, which) -> dialog.dismiss());
            alertDialog.setNegativeButton("CANCEL", (dialog, which) -> {
                dialog.dismiss();
                btn_select_tutor.setText("SELECT TUTOR");
                tutor_id = null;
            });
            alertDialog.show();
        }
        catch (Exception ex)
        {
            Toast.makeText(ctx, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onStart()
    {
        super.onStart();
        Objects.requireNonNull(getDialog()).getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}