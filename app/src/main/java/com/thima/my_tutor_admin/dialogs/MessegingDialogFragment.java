package com.thima.my_tutor_admin.dialogs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thima.my_tutor_admin.R;


public class MessegingDialogFragment extends Fragment {

    private String uid;


    public MessegingDialogFragment(String uid) {
        // Required empty public constructor
        this.uid = uid;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messeging_dialog, container, false);
    }
}