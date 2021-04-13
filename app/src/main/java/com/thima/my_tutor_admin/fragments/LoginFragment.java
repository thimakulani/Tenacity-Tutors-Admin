package com.thima.my_tutor_admin.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.activities.MainActivity;
import com.thima.my_tutor_admin.interfaces.FragmentClickInterface;


public class LoginFragment extends Fragment {

    private TextInputEditText InputEmail;
    private TextInputEditText InputPassword;
    private Context context;
    public String TAG = "LOGIN FRAGMENT";
    public LoginFragment(FragmentClickInterface clickInterface) {
        // Required empty public constructor
        this.clickInterface = clickInterface;
    }


    private final FragmentClickInterface clickInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        ConnectViews(view);
        context = view.getContext();
        return view;
    }


    private void ConnectViews(View view) {

        MaterialTextView btnSignup = (MaterialTextView) view.findViewById(R.id.login_btn_sign_up);
        MaterialButton BtnLogin = (MaterialButton) view.findViewById(R.id.login_btn_login);
        MaterialButton BtnForgotPassword = (MaterialButton) view.findViewById(R.id.btn_forgot_password);
        InputEmail = (TextInputEditText) view.findViewById(R.id.login_input_email);
        InputPassword = (TextInputEditText) view.findViewById(R.id.login_input_password);


        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (InputEmail.getText().toString().trim().isEmpty()) {
                    InputEmail.setError("Email cannot be empty");
                    return;
                }
                if (InputPassword.getText().toString().trim().isEmpty()) {
                    InputPassword.setError("Password cannot be empty");
                    return;
                }
                SweetAlertDialog pDialog = new SweetAlertDialog(view.getContext(), SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Loading");
                pDialog.setCancelable(false);
                pDialog.show();
                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(InputEmail.getText().toString().trim(), InputPassword.getText().toString().trim())
                        .addOnSuccessListener(authResult -> {
                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                        }).addOnFailureListener(e ->
                {
                    pDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);

                    pDialog.setTitleText("Oops...");
                    pDialog.setContentText(e.getMessage());
                    pDialog.setConfirmText("OK");
                    pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            pDialog.dismissWithAnimation();
                        }
                    });
                });


            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInterface.BtnSignUpClick();
            }
        });

        BtnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}