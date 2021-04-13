package com.thima.my_tutor_admin.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.fragments.LoginFragment;
import com.thima.my_tutor_admin.fragments.SignupFragment;
import com.thima.my_tutor_admin.interfaces.FragmentClickInterface;

public class LoginSignupActivity extends AppCompatActivity implements FragmentClickInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        LoginFragment frag = new LoginFragment(this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.FragHost, frag)
                .commit();


    }

    @Override
    public void BtnSignUpClick() {
        SignupFragment frag = new SignupFragment(this);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.right_in, R.anim.right_out)
                .replace(R.id.FragHost, frag, frag.TAG)
                .commit();
    }

    @Override
    public void LoginClick() {
        LoginFragment frag = new LoginFragment(this);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.left_in, R.anim.left_out)
                .replace(R.id.FragHost, frag, frag.TAG)
                .commit();
    }



}