package com.thima.my_tutor_admin.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.activities.MainActivity;
import com.thima.my_tutor_admin.interfaces.FragmentClickInterface;


import java.util.HashMap;
import java.util.Objects;


public class SignupFragment extends Fragment {

    public String TAG = ":SIGN UP FRAGMENT";
    private final FragmentClickInterface clickInterface;

    public SignupFragment(FragmentClickInterface clickInterface){
        this.clickInterface = clickInterface;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_signup, container, false);
        ConnectViews(view);
        return view;
    }
    private void ConnectViews(View view) {
        MaterialButton btnBackToLogin = (MaterialButton) view.findViewById(R.id.sign_up_btn_login);
        MaterialButton btnRegister = (MaterialButton) view.findViewById(R.id.sign_up_btn_register);

        TextInputEditText InputName = (TextInputEditText) view.findViewById(R.id.sign_up_input_names);
        TextInputEditText InputLastname = (TextInputEditText) view.findViewById(R.id.sign_up_input_lastname);
        TextInputEditText InputEmail = (TextInputEditText) view.findViewById(R.id.sign_up_input_email);
        TextInputEditText InputPhone = (TextInputEditText) view.findViewById(R.id.sign_up_input_phone_number);
        TextInputEditText InputPassword = (TextInputEditText) view.findViewById(R.id.sign_up_input_password);

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean check = false;
                if (InputName.getText().toString().trim().isEmpty()) {
                    InputName.setError("Cannot be empty");
                    check = true;
                }
                if (InputLastname.getText().toString().trim().isEmpty()) {
                    InputLastname.setError("Cannot be empty");
                }
                if (Objects.requireNonNull(InputEmail.getText()).toString().trim().isEmpty()) {
                    InputEmail.setError("Cannot be empty");
                    check = true;
                }
                if (InputPhone.getText().toString().trim().isEmpty()) {
                    InputPhone.setError("Cannot be empty");
                    check = true;
                }
                if (InputPassword.getText().toString().trim().isEmpty()) {
                    InputPassword.setError("Cannot be empty");
                    check = true;
                }

                if (!check)
                {
                    SweetAlertDialog pDialog = new SweetAlertDialog(view.getContext(), SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Loading");
                    pDialog.setCancelable(false);
                    pDialog.show();



                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(InputEmail.getText().toString().trim(), InputPassword.getText().toString().trim())
                            .addOnSuccessListener(authResult -> {

                                //StudModel studModel = new StudModel(InputName.getText().toString(), InputLastname.getText().toString(), InputEmail.getText().toString(), InputPhone.getText().toString());
                                HashMap<String, Object> data = new HashMap<>();
                                data.put("name", InputName.getText().toString().trim());
                                data.put("surname", InputLastname.getText().toString().trim());
                                data.put("phone", InputPhone.getText().toString().trim());
                                data.put("email", InputEmail.getText().toString().trim());
                                FirebaseFirestore.getInstance()
                                        .collection("Mentors")
                                        .document(authResult.getUser().getUid())
                                        .set(data);

                                pDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                pDialog.setTitleText("Success!");
                                pDialog.setContentText("Your profile has been successfully created");
                                pDialog.setConfirmText("OK").setOnDismissListener(new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialog) {
                                        pDialog.dismissWithAnimation();
                                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                                        startActivity(intent);
                                    }
                                });



                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            pDialog.setTitleText("Oops");
                            pDialog.setContentText(e.getMessage());
                            pDialog.setConfirmText("OK").setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    pDialog.dismissWithAnimation();
                                }
                            });
                        }
                    }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                        }
                    });
                }
            }
        });

        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInterface.LoginClick();
            }
        });
    }

}