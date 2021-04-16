package com.thima.my_tutor_admin.dialogs;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.adapters.StudentAdapter;
import com.thima.my_tutor_admin.models.StudentsModel;

import java.util.ArrayList;
import java.util.List;

public class StudentsDialogFragment extends DialogFragment {


    public StudentsDialogFragment() {
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
        View view = inflater.inflate(R.layout.fragment_students_dialog, container, false);
        ConnectViews(view);
        return view;
    }
    private final List<StudentsModel> Items = new ArrayList<>();
    private void ConnectViews(View view) {
        MaterialToolbar toolbar = view.findViewById(R.id.app_toolbar);
        RecyclerView recycler = view.findViewById(R.id.recycler_students);
        MaterialTextView txt_count_students = view.findViewById(R.id.txt_count_students);
        txt_count_students.setText(String.format(getString(R.string.txt_num_stud), Items.size()));


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Items.add(new StudentsModel("NAME", "SURNAME", ""));
        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        StudentAdapter adapter = new StudentAdapter(Items);
        recycler.setAdapter(adapter);

        FirebaseFirestore.getInstance().collection("Students")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(value != null && !value.isEmpty()){
                            int i = 1;
                            for (DocumentChange dc: value.getDocumentChanges() ){
                                switch (dc.getType()) {
                                    case ADDED:
                                        StudentsModel stud = dc.getDocument().toObject(StudentsModel.class);
                                        stud.setId(dc.getDocument().getId());
                                        Items.add(stud);
                                        adapter.notifyDataSetChanged();
                                        break;
                                    case MODIFIED:
                                        StudentsModel s = dc.getDocument().toObject(StudentsModel.class);
                                        s.setId(dc.getDocument().getId());
                                        Items.set(i,s);
                                        adapter.notifyDataSetChanged();
                                        break;
                                    case REMOVED:
                                        break;
                                }
                                i++;
                            }

                        }
                        txt_count_students.setText(String.format(getString(R.string.txt_num_stud), Items.size()-1));
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