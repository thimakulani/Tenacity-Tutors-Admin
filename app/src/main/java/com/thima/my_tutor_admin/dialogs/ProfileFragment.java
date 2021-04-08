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

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.interfaces.ImagePickerClickInterface;

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

    private void ConnectViews(View view) {
        MaterialToolbar toolbar = view.findViewById(R.id.toolbar_profile);
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