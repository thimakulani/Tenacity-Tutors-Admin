package com.thima.my_tutor_admin.dialogs;

import android.os.Bundle;
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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.adapters.ChatListAdapter;
import com.thima.my_tutor_admin.models.StudentsModel;

import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends DialogFragment {



    public ChatFragment() {
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
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        ConnectViews(view);
        return view;

    }
    List<String> Items = new ArrayList<>();
    private void ConnectViews(View view) {
        MaterialToolbar toolbar = view.findViewById(R.id.app_toolbar);
        RecyclerView recycler = view.findViewById(R.id.recycler_chat_list);

        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ChatListAdapter adapter = new ChatListAdapter(Items, getChildFragmentManager());
        recycler.setAdapter(adapter);
        
        toolbar.setNavigationOnClickListener(v -> dismiss());


        FirebaseFirestore.getInstance().collection("Chats")
                .orderBy("Dates", Query.Direction.ASCENDING)
                .addSnapshotListener((value, error) -> {
                    if(value != null){
                        for (DocumentChange dc : value.getDocumentChanges()){
                            switch (dc.getType()) {
                                case ADDED:
                                    Items.add(dc.getDocument().getId());
                                    adapter.notifyDataSetChanged();
                                    break;
                                case MODIFIED:
                                    break;
                                case REMOVED:
                                    break;
                            }
                        }
                    }
                });
    }
}