package com.thima.my_tutor_admin.dialogs;

import android.content.ClipData;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.adapters.CommuniqueAdapter;
import com.thima.my_tutor_admin.models.Communique;
import com.thima.my_tutor_admin.models.CommuniqueModel;

import java.util.ArrayList;
import java.util.List;

public class CommuniqueDialogFragment extends DialogFragment {


    public CommuniqueDialogFragment() {
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
        View view = inflater.inflate(R.layout.fragment_communique_dialog, container, false);

        ConnectViews(view);

        return view;
    }
    private final List<Communique> Items = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void ConnectViews(View view) {
        RecyclerView recycler = view.findViewById(R.id.recycler_communique);
        FloatingActionButton fab_add_communique = view.findViewById(R.id.fab_add_communique);
        MaterialToolbar toolbar = view.findViewById(R.id.app_toolbar);
        toolbar.setNavigationOnClickListener(v -> dismiss());


        fab_add_communique.setOnClickListener(v -> {
            AddCommuniqueDialogFragment dlg = new AddCommuniqueDialogFragment();
            dlg.show(getChildFragmentManager().beginTransaction(), "ADD COMMUNIQUE");
        });

        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        CommuniqueAdapter adapter = new CommuniqueAdapter(Items);
        recycler.setAdapter(adapter);

        FirebaseFirestore.getInstance()
                .collection("Announcements")
                .addSnapshotListener((value, error) -> {
                    if(!value.isEmpty())
                    {
                        for (DocumentChange dc : value.getDocumentChanges()){
                            switch (dc.getType()) {
                                case ADDED:
                                    Items.add(dc.getDocument().toObject(Communique.class));

                                    break;
                                case MODIFIED:
                                    break;
                                case REMOVED:

                                    if(Items.removeIf(communique -> communique.getId().contains(dc.getDocument().getId())))
                                    {
                                        adapter.notifyDataSetChanged();
                                    }

                                    break;
                                default:
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                });



    }
}