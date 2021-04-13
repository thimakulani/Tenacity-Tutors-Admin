package com.thima.my_tutor_admin.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.adapters.FabMenuAdapter;
import com.thima.my_tutor_admin.dialogs.AboutDialogFragment;
import com.thima.my_tutor_admin.dialogs.AppointmentDialogFragment;
import com.thima.my_tutor_admin.dialogs.ChatFragment;
import com.thima.my_tutor_admin.dialogs.CommuniqueDialogFragment;
import com.thima.my_tutor_admin.dialogs.MentorsDialogFragment;
import com.thima.my_tutor_admin.dialogs.ProfileFragment;
import com.thima.my_tutor_admin.dialogs.StudentsDialogFragment;
import com.thima.my_tutor_admin.dialogs.SubjectsDialogFragment;
import com.thima.my_tutor_admin.interfaces.CircleTransform;
import com.thima.my_tutor_admin.interfaces.ImagePickerClickInterface;
import com.thima.my_tutor_admin.interfaces.MenuAdapterClickInterface;
import com.thima.my_tutor_admin.models.MentorsModel;
import com.thima.my_tutor_admin.models.MenuModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MenuAdapterClickInterface, ImagePickerClickInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu_profile_img = findViewById(R.id.menu_profile_img);
        txt_home_display_name = findViewById(R.id.txt_home_display_name);

        FirebaseFirestore.getInstance()
                .collection("Mentors")
                .document(FirebaseAuth.getInstance().getUid())
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(value.exists()){
                            MentorsModel user = value.toObject(MentorsModel.class);
                            txt_home_display_name.setText(user.getName());
                        }
                    }
                });

        SetupMenu();
    }
    List<MenuModel> Items = new ArrayList<>();
    ShapeableImageView menu_profile_img;
    MaterialTextView txt_home_display_name;
    private void SetupMenu() {
        RecyclerView recycler = findViewById(R.id.recycler_menus);


        Items.add(new MenuModel("Profile".toUpperCase(), R.drawable.baseline_account_circle_white_img_24dp, R.color.deep_purple_500));
        Items.add(new MenuModel("Students".toUpperCase(), R.drawable.baseline_people_alt_white_24dp, R.color.indigo_900));
        Items.add(new MenuModel("Mentors".toUpperCase(), R.drawable.baseline_how_to_reg_white_24dp, R.color.purple_A700));
        Items.add(new MenuModel("Subjects".toUpperCase(), R.drawable.baseline_grading_white_24dp, R.color.red_500));
        Items.add(new MenuModel("Messages".toUpperCase(), R.drawable.baseline_forward_to_inbox_white_24dp, R.color.blue_500));
        Items.add(new MenuModel("Meeting".toUpperCase(), R.drawable.baseline_cast_for_education_white_24dp, R.color.green_500));
        Items.add(new MenuModel("Notify".toUpperCase(), R.drawable.baseline_announcement_white_24dp, R.color.grey_500));
        Items.add(new MenuModel("About".toUpperCase(), R.drawable.baseline_help_outline_white_24dp, R.color.blue_grey_900));
        Items.add(new MenuModel("Logout".toUpperCase(), R.drawable.baseline_login_white_24dp, R.color.light_blue_900));

        FabMenuAdapter adapter = new FabMenuAdapter(this, Items);
        recycler.setLayoutManager(new GridLayoutManager(this, 3));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
    }

    @Override
    public void OnMenuClick(int position, FloatingActionButton fab) {
        if (position == 0){
            ProfileFragment profileFragment = new ProfileFragment(this);
            profileFragment.show(getSupportFragmentManager()
                    .beginTransaction().setCustomAnimations(R.anim.slide_up, R.anim.fade_out_anim)
                    .addSharedElement(fab, fab.getTransitionName()), "PROFILE");

        }
        if (position == 1){
            StudentsDialogFragment frag = new StudentsDialogFragment();
            frag.show(getSupportFragmentManager()
                    .beginTransaction().setCustomAnimations(R.anim.slide_up, R.anim.fade_out_anim)
                    .addSharedElement(fab, fab.getTransitionName()), "STUDENTS");

        }
        if (position == 2)
        {
            MentorsDialogFragment mentors = new MentorsDialogFragment();
            mentors.show(getSupportFragmentManager().beginTransaction(), "MENTORS");
        }
        if (position == 3)
        {
            SubjectsDialogFragment subjects = new SubjectsDialogFragment();
            subjects.show(getSupportFragmentManager().beginTransaction(), "SUBJECTS");
        }
        if (position == 4)
        {
            ChatFragment chats = new ChatFragment();
            chats.show(getSupportFragmentManager().beginTransaction(), "CHATS");

        }
        if (position == 5)
        {
            AppointmentDialogFragment appointment = new AppointmentDialogFragment();
            appointment.show(getSupportFragmentManager().beginTransaction(), "APPOINTMENT");
        }
        if (position == 6)
        {
            CommuniqueDialogFragment communique = new CommuniqueDialogFragment();
            communique.show(getSupportFragmentManager().beginTransaction(), "COMMUNIQUE");
        }
        if(position == 7){
            AboutDialogFragment about = new AboutDialogFragment();
            about.show(getSupportFragmentManager().beginTransaction(), "About");
        }
        if (position == 8)
        {
            finish();
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

            if (data != null) {
                Uri img = data.getData();
                Picasso.get().load(img)
                        .resize(150, 150)
                        .transform(new CircleTransform())
                        .into(menu_profile_img);
            }

            pDialog.dismiss();
        }

    }
}