package com.thima.my_tutor_admin.dialogs;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.adapters.AppointmentAdapter;
import com.thima.my_tutor_admin.models.AppointmentModel;

import java.util.ArrayList;
import java.util.List;


public class AppointmentDialogFragment extends DialogFragment {


    public AppointmentDialogFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_appointment_dialog, container, false);
        ConnectViews(view);
        return view;
    }
    List<AppointmentModel> Items = new ArrayList<>();
    private void ConnectViews(View view)
    {
        MaterialToolbar toolbar = view.findViewById(R.id.app_toolbar);
        RecyclerView recycler = view.findViewById(R.id.recycler_appointments);
        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        AppointmentAdapter adapter = new AppointmentAdapter(Items, getChildFragmentManager());
        recycler.setAdapter(adapter);

        FirebaseFirestore.getInstance().collection("Appointments")
                .whereEqualTo("status", "Request")
                .addSnapshotListener((value, error) -> {

                    if(error != null){
                        Toast.makeText(view.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    if (value != null && !value.isEmpty())
                    {

                        for (DocumentChange dc: value.getDocumentChanges()){
                            switch (dc.getType()) {
                                case ADDED:
                                    Items.add(dc.getDocument().toObject(AppointmentModel.class));
                                    adapter.notifyDataSetChanged();
                                    break;
                                case MODIFIED:

                                    int counter = 0;
                                    for (AppointmentModel a : Items){
                                        if(a.getId().equals(dc.getDocument().getId())){
                                            Items.set(counter, dc.getDocument().toObject(AppointmentModel.class));
                                            adapter.notifyDataSetChanged();
                                            break;
                                        }
                                        counter++;
                                    }
                                    break;
                                case REMOVED:
                                    break;
                            }

                        }

                    }
                });



    }
}