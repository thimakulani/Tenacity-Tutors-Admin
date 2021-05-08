package com.thima.my_tutor_admin.dialogs;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.models.SubjectsModel;

import java.util.HashMap;
import java.util.Objects;


public class AddSubjectDialogFragment extends DialogFragment {
    public AddSubjectDialogFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_subject_dialog, container, false);
        ConnectViews(view);
        return view;
    }

    private void ConnectViews(View view) {
        FloatingActionButton fab_close = view.findViewById(R.id.fab_close_add_subject);
        TextInputEditText InputSubject  = view.findViewById(R.id.input_subject_name);
        MaterialButton btn_add_subject = view.findViewById(R.id.btn_add_subject);

        fab_close.setOnClickListener(v -> dismiss());
        btn_add_subject.setOnClickListener(v -> {
            if(InputSubject.getText().toString().isEmpty()){
                InputSubject.setError("Cannot be empty");
                return;
            }
            SubjectsModel subject = new SubjectsModel(InputSubject.getText().toString().trim(), null);
            FirebaseFirestore.getInstance()
                    .collection("Subjects")
                    .add(subject).addOnSuccessListener(documentReference -> {
                        documentReference.update("id", documentReference.getId());
                        new SweetAlertDialog(view.getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Success!!")
                                .setContentText("Successfully added.")
                                .setConfirmText("OK")
                                .setConfirmClickListener(sweetAlertDialog -> {
                                    sweetAlertDialog.dismiss();
                                    InputSubject.getText().clear();
                                });

                    });




        });
    }
    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog()).getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}