package com.thima.my_tutor_admin.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.models.Communique;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class AddCommuniqueDialogFragment extends DialogFragment {

    public AddCommuniqueDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStyle(STYLE_NO_FRAME, R.style.FullScreenDialogStyle);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_communique_dialog, container, false);
        ConnectView(view);
        return view;
    }

    private void ConnectView(View view) {
        FloatingActionButton fab_close = view.findViewById(R.id.fab_close);
        TextInputEditText InputMessage = view.findViewById(R.id.InputCommunique);
        MaterialButton BtnAdd = view.findViewById(R.id.btn_add_communique);


        fab_close.setOnClickListener(v -> dismiss());

        BtnAdd.setOnClickListener(v -> {

            if(InputMessage.getText().toString().isEmpty()){
                InputMessage.setError("Provide Message");
                return;
            }
            try {
                String currentDate = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
                Communique communique = new Communique(currentDate, InputMessage.getText().toString().trim(), null);

                FirebaseFirestore.getInstance()
                        .collection("Announcements")
                        .add(communique).addOnSuccessListener(documentReference -> documentReference.update("id", documentReference.getId()));
                dismiss();
            }
            catch(Exception ex){
                Toast.makeText(view.getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog()).getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }
}