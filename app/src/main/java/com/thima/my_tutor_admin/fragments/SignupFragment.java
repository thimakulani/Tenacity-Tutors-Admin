package com.thima.my_tutor_admin.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.interfaces.FragmentClickInterface;


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
        TextInputEditText InputPassword = (TextInputEditText) view.findViewById(R.id.sign_up_input_password);
        TextInputEditText InputPassword2 = (TextInputEditText) view.findViewById(R.id.sign_up_input_password_2);

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
                if (InputPassword.getText().toString().trim().isEmpty()) {
                    InputPassword.setError("Cannot be empty");
                    check = true;
                }
                if (!InputPassword.getText().toString().trim().equals(InputPassword2.getText().toString().trim())) {
                    InputPassword2.setError("Password does not match");
                    check = true;
                }
                if (check == false){

                        
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