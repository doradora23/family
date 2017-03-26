package com.example.sh.family;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.teamviewer.sdk.screensharing.api.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TVSessionConfiguration config =
                new TVSessionConfiguration.Builder(
                        new TVConfigurationID("e73h8wb"))
                        .setServiceCaseName("NAME_FOR_SERVICE_CASE")
                        .setServiceCaseDescription("DESCRIPTION_FOR_SERVICE_CASE")
                        .build();

        TVSessionFactory.createTVSession(this, "2dafa167-667f-b843-23b6-050fa9c522ea",
                new TVSessionCreationCallback() {
                    @Override
                    public void onTVSessionCreationSuccess(TVSession session) {
                        session.start(config);
                    }

                    @Override
                    public void onTVSessionCreationFailed(TVCreationError error) {
                    }
                });
    }
}

