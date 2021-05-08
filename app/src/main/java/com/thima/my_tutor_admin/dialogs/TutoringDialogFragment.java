package com.thima.my_tutor_admin.dialogs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.models.AppointmentModel;
import com.thima.my_tutor_admin.models.StudentsModel;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.webrtc.EglBase;
import org.webrtc.RendererCommon;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import timber.log.Timber;

import static org.webrtc.ContextUtils.getApplicationContext;


public class TutoringDialogFragment extends DialogFragment {

    AppointmentModel appointment = new AppointmentModel();
    public TutoringDialogFragment(AppointmentModel appointment) {
        this.appointment = appointment;
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
        View view = inflater.inflate(R.layout.fragment_tutoring_dialog, container, false);
        context = view.getContext();
        ConnectViews(view);
        return view;
    }

    private Context context;
    private void ConnectViews(View view) {

        MaterialTextView txt_name = view.findViewById(R.id.session_stud_name);
        MaterialButton btn_complete_session = view.findViewById(R.id.btn_complete_session);
        MaterialTextView txt_grade = view.findViewById(R.id.session_grade);
        MaterialTextView txt_subject = view.findViewById(R.id.session_subject);
        MaterialTextView txt_time = view.findViewById(R.id.session_time);
        btn_complete_session.setVisibility(View.GONE);
        txt_grade.setText(appointment.getGrade());

        txt_subject.setText(appointment.getSubject());
        txt_time.setText(appointment.getTime());
        FirebaseFirestore.getInstance()
                .collection("Students")
                .document(appointment.getStud_id())
                .addSnapshotListener((value, error) -> {
                    if(value !=null){
                        StudentsModel s = value.toObject(StudentsModel.class);
                        txt_name.setText(Objects.requireNonNull(s).getName());
                    }
                });
        FirebaseFirestore
                .getInstance()
                .collection("Appointment")
                .document(appointment.getId())
                .addSnapshotListener((value, error) -> {
                    if (value != null && value.exists()) {
                        AppointmentModel app = value.toObject(AppointmentModel.class);
                        if (Objects.requireNonNull(app).getMeeting_room() != null) {
                            btn_complete_session.setVisibility(View.VISIBLE);
                        }
                    }
                });
                
                
                


        URL serverURL = null;
        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions options
                    = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverURL)
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(options);

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }



        MaterialButton btn_start_session = view.findViewById(R.id.btn_start_session);

        btn_start_session.setOnClickListener(v -> {

            try {
                JitsiMeetConferenceOptions option
                        = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(appointment.getId() +":" + appointment.getId())
                        .build();
                JitsiMeetActivity.launch(context, option);
                FirebaseFirestore
                        .getInstance()
                        .collection("Appointment")
                        .document(appointment.getId())
                        .update("meeting_room", appointment.getId() +":" + appointment.getId());
            }
            catch (Exception ex){
                Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}