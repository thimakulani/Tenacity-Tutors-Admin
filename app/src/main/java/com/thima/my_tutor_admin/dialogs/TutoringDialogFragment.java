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

import com.thima.my_tutor_admin.R;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.webrtc.EglBase;
import org.webrtc.RendererCommon;

import java.net.MalformedURLException;
import java.net.URL;

import timber.log.Timber;

import static org.webrtc.ContextUtils.getApplicationContext;


public class TutoringDialogFragment extends DialogFragment {



    public TutoringDialogFragment() {
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
        View view = inflater.inflate(R.layout.fragment_tutoring_dialog, container, false);
        ConnectViews(view);
        return view;
    }

    private Context context;
    private void ConnectViews(View view) {

        context = view.getContext();

        URL serverURL = null;
        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions conferenceOptions
                    = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverURL)
                    .setWelcomePageEnabled(false)
                    .setRoom("Test")
                    .build();
            JitsiMeet.setDefaultConferenceOptions(conferenceOptions);
            JitsiMeetActivity.launch(context, conferenceOptions);





        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }



    }



}