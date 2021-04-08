package com.thima.my_tutor_admin.dialogs;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.thima.my_tutor_admin.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutFragment extends Fragment {
    public AboutFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Element adsElement = new Element();
        adsElement.setTitle("Advertise with us");

        View aboutPage = new AboutPage(getContext())
                .isRTL(false)
                .enableDarkMode(false)
              //  .setImage(R.drawable.dummy_image)
                .addItem(new Element().setTitle("Version 0.0.1"))
                .addItem(adsElement)
                .addGroup("Connect with us")
                .addEmail("")
                .addWebsite("")
                .addFacebook("")
                .addTwitter("")
                .addYoutube("")
                .addPlayStore("")
                .addInstagram("")
                .addGitHub("")
                .addItem(getCopyRightsElement())
                .create();

        return aboutPage;
    }


    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights = "";
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIconDrawable(R.drawable.baseline_account_circle_black_24dp);
        copyRightsElement.setAutoApplyIconTint(true);
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
        copyRightsElement.setIconNightTint(android.R.color.white);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), copyrights, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRightsElement;
    }
}