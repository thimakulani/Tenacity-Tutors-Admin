package com.thima.my_tutor_admin.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.adapters.FabMenuAdapter;
import com.thima.my_tutor_admin.dialogs.ProfileFragment;
import com.thima.my_tutor_admin.interfaces.CircleTransform;
import com.thima.my_tutor_admin.interfaces.ImagePickerClickInterface;
import com.thima.my_tutor_admin.interfaces.MenuAdapterClickInterface;
import com.thima.my_tutor_admin.models.MenuModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MenuAdapterClickInterface, ImagePickerClickInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SetupMenu();
    }
    List<MenuModel> Items = new ArrayList<>();
    ShapeableImageView menu_profile_img;
    private void SetupMenu() {
        RecyclerView recycler = findViewById(R.id.recycler_menus);
        menu_profile_img = findViewById(R.id.menu_profile_img);

        Items.add(new MenuModel("Profile".toUpperCase(), R.drawable.baseline_account_circle_white_img_24dp, R.color.deep_purple_500));
        Items.add(new MenuModel("Students".toUpperCase(), R.drawable.baseline_account_circle_white_24dp, R.color.red_500));
        Items.add(new MenuModel("Messages".toUpperCase(), R.drawable.baseline_account_circle_white_24dp, R.color.blue_500));
        Items.add(new MenuModel("Meeting".toUpperCase(), R.drawable.baseline_account_circle_black_24dp, R.color.green_500));
        Items.add(new MenuModel("Notify".toUpperCase(), R.drawable.baseline_account_circle_black_24dp, R.color.grey_500));
        Items.add(new MenuModel("Logout".toUpperCase(), R.drawable.baseline_account_circle_black_24dp, R.color.white));

        FabMenuAdapter adapter = new FabMenuAdapter(this, Items);
        recycler.setLayoutManager(new GridLayoutManager(this, 3));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
    }

    @Override
    public void OnMenuClick(int position, FloatingActionButton fab) {
        if (position == 1){
            ProfileFragment profileFragment = new ProfileFragment(this);
            profileFragment.show(getSupportFragmentManager()
                    .beginTransaction().setCustomAnimations(R.anim.slide_up, R.anim.fade_out_anim)
                    .addSharedElement(fab, fab.getTransitionName()), "PROFILE");
        }
        if (position == 2){

        }



    }
    SweetAlertDialog pDialog;
    @Override
    public void SelectImage() {
        ImagePicker.Companion.with(this)
                .crop()
                .compress(512)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(120, 120)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Loading");
            pDialog.setCancelable(false);
            pDialog.show();
            Toast.makeText(this, "selected", Toast.LENGTH_SHORT).show();
            Uri img = data.getData();
            Picasso.get().load(img)
                    .resize(150, 150)
                    .transform(new CircleTransform())
                    .into(menu_profile_img);
            pDialog.dismiss();
        }

    }
}