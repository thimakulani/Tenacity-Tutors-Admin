package com.thima.my_tutor_admin.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.interfaces.CircleTransform;
import com.thima.my_tutor_admin.interfaces.ImagePickerClickInterface;
import com.thima.my_tutor_admin.models.MentorsModel;

import java.util.HashMap;
import java.util.Objects;

public class ProfileFragment extends DialogFragment {

    ImagePickerClickInterface imgPicker;

    public ProfileFragment(ImagePickerClickInterface imgPicker) {
        this.imgPicker = imgPicker;
    }

    @Override




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.FullScreenDialogStyle);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.dialog_fragment_profile, container, false);

        ConnectViews(view);
        setCancelable(false);
        return view;
    }

    private void ConnectViews(View view)
    {

        TextInputEditText profile_input_name = view.findViewById(R.id.profile_input_name);
        ShapeableImageView img_dp = view.findViewById(R.id.img_dp);
        TextInputEditText profile_input_surname = view.findViewById(R.id.profile_input_surname);
        TextInputEditText profile_input_phone = view.findViewById(R.id.profile_input_phone);
        TextInputEditText profile_input_email = view.findViewById(R.id.profile_input_email);
        TextInputEditText profile_input_role = view.findViewById(R.id.profile_input_role);
        MaterialButton profile_btn_update = view.findViewById(R.id.profile_btn_update);



        profile_btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(profile_input_name.getText().toString().isEmpty()){
                    profile_input_name.setError("Cannot be empty");
                    return;
                }
                if(profile_input_surname.getText().toString().isEmpty()){
                    profile_input_surname.setError("Cannot be empty");
                    return;
                }
                if(profile_input_phone.getText().toString().isEmpty()){
                    profile_input_phone.setError("Cannot be empty");
                    profile_input_phone.findFocus();
                    return;
                }



                HashMap<String, Object> data = new HashMap<>();
                data.put("name", profile_input_name.getText().toString().trim());
                data.put("surname", profile_input_surname.getText().toString().trim());
                data.put("phone", profile_input_phone.getText().toString().trim());
                /*data.put("email", profile_input_email.getText().toString().trim());
                data.put("role", profile_input_role.getText().toString().trim());*/
                FirebaseFirestore.getInstance().collection("Tutors")
                        .document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                        .update(data);
                new SweetAlertDialog(view.getContext(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Success!!")
                        .setContentText("Successfully updated")
                        .setConfirmText("Ok")
                        .show();
            }
        });



        FirebaseFirestore.getInstance()
                .collection("Tutors")
                .document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (value != null && value.exists()) {

                            MentorsModel user = value.toObject(MentorsModel.class);
                            assert user != null;
                            profile_input_name.setText(user.getName());
                            profile_input_surname.setText(user.getSurname());
                            profile_input_phone.setText(user.getPhone());
                            profile_input_email.setText(user.getEmail());
                            profile_input_role.setText(user.getRole());

                            if (user.getImgUrl() != null) {
                                Picasso.get().load(user.getImgUrl())
                                        .resize(100, 100)
                                        .transform(new CircleTransform())
                                        .into(img_dp);
                            }


                        }

                    }
                });

        MaterialToolbar toolbar = view.findViewById(R.id.app_toolbar);
        FloatingActionButton fab = view.findViewById(R.id.fab_upload_pic);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgPicker.SelectImage();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}