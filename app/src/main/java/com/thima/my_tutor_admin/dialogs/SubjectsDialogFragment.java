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
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.adapters.SubjectAdapter;
import com.thima.my_tutor_admin.models.SubjectsModel;

import java.util.ArrayList;
import java.util.List;

public class SubjectsDialogFragment extends DialogFragment {



    public SubjectsDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.FullScreenDialogStyle);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subjects_dialog, container, false);
        ConnectViews(view);
        return view;
    }
    private final ArrayList<SubjectsModel> Items = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void ConnectViews(View view) {
        MaterialToolbar toolbar = view.findViewById(R.id.app_toolbar);
        ExtendedFloatingActionButton fab_open_add = view.findViewById(R.id.fab_open_add_subjects);
        fab_open_add.setOnClickListener(v -> {
            AddSubjectDialogFragment fragment = new AddSubjectDialogFragment();
            fragment.show(getChildFragmentManager().beginTransaction(), "ADD SUBJECT");

        });

        RecyclerView recycler = view.findViewById(R.id.recycler_subjects);
        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        SubjectAdapter adapter = new SubjectAdapter(Items);
        recycler.setAdapter(adapter);

        FirebaseFirestore.getInstance()
                .collection("Subjects")
                .addSnapshotListener((value, error) -> {
                    if(!value.isEmpty())
                    {
                        for (DocumentChange dc : value.getDocumentChanges()){

                            switch (dc.getType()) {
                                case ADDED:
                                    SubjectsModel subject = dc.getDocument().toObject(SubjectsModel.class);
                                    subject.setId(dc.getDocument().getId());
                                    Items.add(subject);
                                    adapter.notifyDataSetChanged();
                                    break;
                                case MODIFIED:
                                    break;
                                case REMOVED:
                                    if(Items.removeIf(x -> x.getId().contains(dc.getDocument().getId())))
                                    {
                                        adapter.notifyDataSetChanged();
                                    }
                                    break;
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