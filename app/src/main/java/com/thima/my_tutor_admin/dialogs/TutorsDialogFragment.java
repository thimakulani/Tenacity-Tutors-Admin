package com.thima.my_tutor_admin.dialogs;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import com.thima.my_tutor_admin.adapters.TutorsAdapter;
import com.thima.my_tutor_admin.models.TutorModel;

import java.util.ArrayList;
import java.util.List;


public class TutorsDialogFragment extends DialogFragment {


    public TutorsDialogFragment() {
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
        View view = inflater.inflate(R.layout.fragment_tutor_dialog, container, false);
        ConnectViews(view);
        return view;
    }
    private final List<TutorModel> Items = new ArrayList<>();
    private void ConnectViews(View view) {
        MaterialToolbar toolbar = view.findViewById(R.id.app_toolbar);
        RecyclerView recycler = view.findViewById(R.id.recycler_mentors);

        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        TutorsAdapter adapter = new TutorsAdapter(Items, getChildFragmentManager());
        recycler.setAdapter(adapter);
        FirebaseFirestore.getInstance()
                .collection("Tutors")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(!value.isEmpty()){
                            for (DocumentChange dc : value.getDocumentChanges()){
                                switch (dc.getType()) {
                                    case ADDED:
                                        TutorModel mentor = dc.getDocument().toObject(TutorModel.class);
                                        mentor.setId(dc.getDocument().getId());
                                        Items.add(mentor);
                                        adapter.notifyDataSetChanged();
                                        break;
                                    case MODIFIED:
                                        break;
                                    case REMOVED:
                                        if(Items.removeIf(x -> x.getId().contains(dc.getDocument().getId()))){
                                            adapter.notifyDataSetChanged();
                                        }
                                        break;
                                }
                            }
                        }
                    }
                });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });




    }
}