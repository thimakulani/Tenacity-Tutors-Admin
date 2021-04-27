package com.thima.my_tutor_admin.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.adapters.MessagingAdapter;
import com.thima.my_tutor_admin.models.MessageModel;
import com.thima.my_tutor_admin.models.StudentsModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class MessegingDialogFragment extends DialogFragment {

    private String uid;


    public MessegingDialogFragment(String uid) {
        // Required empty public constructor
        this.uid = uid;



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
        View view = inflater.inflate(R.layout.fragment_messeging_dialog, container, false);
        ConnectViews(view);
        return view;
    }
    List<MessageModel> Items = new ArrayList<>();
    private void ConnectViews(View view) {
        MaterialToolbar toolbar = view.findViewById(R.id.app_toolbar);
        RecyclerView recycler = view.findViewById(R.id.recycler_chats);
        TextInputEditText input_message = view.findViewById(R.id.InputMessage);
        FloatingActionButton fab_send = view.findViewById(R.id.FabSendMessage);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        FirebaseFirestore.getInstance()
                .collection("Students")
                .document(uid)
                .addSnapshotListener((value, error) -> {
                    if(value != null){
                        StudentsModel stud = value.toObject(StudentsModel.class);
                        toolbar.setTitle(String.format("%s %s", Objects.requireNonNull(stud).getName(), stud.getSurname()));
                    }
                });

        fab_send.setOnClickListener(v -> {
            if(!Objects.requireNonNull(input_message.getText()).toString().isEmpty())
            {
                HashMap<String, Object> msg = new HashMap<>();
                msg.put("text", input_message.getText().toString().trim());
                msg.put("uid", FirebaseAuth.getInstance().getUid());
                msg.put("time_stamp", FieldValue.serverTimestamp());


                FirebaseFirestore.getInstance()
                        .collection("Chats")
                        .document(uid)
                        .collection("Message")
                        .add(msg)
                        .addOnSuccessListener(documentReference -> {
                            input_message.getText().clear();
                            String currentDate = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
                            HashMap<String, Object> dates = new HashMap<>();
                            dates.put("Dates", currentDate);
                            FirebaseFirestore.getInstance()
                                    .collection("Chats")
                                    .document(uid)
                                    .set(dates);
                        })

                ;
            }
        });





        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        MessagingAdapter adapter = new MessagingAdapter(Items);
        recycler.setAdapter(adapter);

        FirebaseFirestore.getInstance()
                .collection("Chats")
                .document(uid)
                .collection("Message")
                .orderBy("time_stamp", Query.Direction.ASCENDING)
                .addSnapshotListener((value, error) -> {
                    if(value != null){
                        for (DocumentChange dc : value.getDocumentChanges()){
                            switch (dc.getType()) {
                                case ADDED:
                                    Items.add(dc.getDocument().toObject(MessageModel.class));
                                    adapter.notifyDataSetChanged();
                                    break;
                                case MODIFIED:
                                case REMOVED:
                                    break;
                            }
                        }
                    }
                });
    }
}