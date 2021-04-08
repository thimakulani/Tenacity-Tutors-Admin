package com.thima.my_tutor_admin.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
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


    private void ConnectViews(View view)
    {
        try {
            MaterialTextView btnSignup = (MaterialTextView) view.findViewById(R.id.login_btn_sign_up);
            MaterialButton BtnLogin = (MaterialButton) view.findViewById(R.id.login_btn_login);
            MaterialButton BtnForgotPassword = (MaterialButton) view.findViewById(R.id.btn_forgot_password);
            InputEmail = (TextInputEditText) view.findViewById(R.id.login_input_email);
            InputPassword = (TextInputEditText) view.findViewById(R.id.login_input_password);


            BtnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(InputEmail.getText().toString().trim().isEmpty()){
                        InputEmail.setError("Email cannot be empty");
                        return;
                    }
                    if(InputPassword.getText().toString().trim().isEmpty()){
                        InputPassword.setError("Password cannot be empty");
                        return;
                    }

                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(intent);
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
        catch (Exception ex){
            Toast.makeText(context, "Xxx", Toast.LENGTH_SHORT).show();
        }




    }

}