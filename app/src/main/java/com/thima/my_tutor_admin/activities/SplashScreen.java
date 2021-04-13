package com.thima.my_tutor_admin.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.thima.my_tutor_admin.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TextView appName = findViewById(R.id.txtAppName);
        TextView developed = findViewById(R.id.txtDevelopedBy);
        ImageView ImageAppLogo = findViewById(R.id.ImageAppLogo);
        developed.setVisibility(View.GONE);
        ImageAppLogo.setVisibility(View.GONE);


        developed.setText(R.string.txt_developed_by);


        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.float_up);
        Animation slideUp2 = AnimationUtils.loadAnimation(this, R.anim.float_up);
        Animation slideUp3 = AnimationUtils.loadAnimation(this, R.anim.float_up);
        appName.startAnimation(slideUp);
        slideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                developed.setVisibility(View.VISIBLE);
                ImageAppLogo.setVisibility(View.VISIBLE);
                developed.startAnimation(slideUp3);
                ImageAppLogo.startAnimation(slideUp2);
                slideUp3.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment("Authenticating...");
//                        FragmentTransaction ft = getSupportFragmentManager()
//                                .beginTransaction();
//                        loadingDialogFragment.setCancelable(false);
//                        loadingDialogFragment.show(ft, "Loading");
                        new Handler().postDelayed(() -> {
                            Intent intent;
                            if(FirebaseAuth.getInstance().getCurrentUser() == null){
                                intent = new Intent(getApplicationContext(), LoginSignupActivity.class);
                            }
                            else{
                                intent = new Intent(getApplicationContext(), MainActivity.class);
                            }
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_up, R.anim.slide_right);
                            finish();


                        }, 3000);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}