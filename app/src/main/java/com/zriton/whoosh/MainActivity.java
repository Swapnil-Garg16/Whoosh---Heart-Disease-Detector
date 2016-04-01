package com.zriton.whoosh;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton recordButton,pauseButton ;
    private MediaRecorder myAudioRecorder;
    private String outputFile = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recordButton= (FloatingActionButton) findViewById(R.id.record_button);
        pauseButton=(FloatingActionButton)findViewById(R.id.pause_button);


        outputFile = android.os.Environment.getExternalStorageDirectory() + "/Recording/test_" + System.currentTimeMillis() + ".mp3";
        boolean exists = (new File(android.os.Environment.getExternalStorageDirectory()+"/Recording/")).exists();

        if(!exists)
        {
            new File(android.os.Environment.getExternalStorageDirectory()+"/Recording/").mkdirs();

        }
        myAudioRecorder=new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.DEFAULT);
        myAudioRecorder.setOutputFile(outputFile);

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordButton.setVisibility(View.GONE);
                pauseButton.setVisibility(View.VISIBLE);
                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseButton.setVisibility(View.GONE);
                recordButton.setVisibility(View.VISIBLE);

                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder  = null;


                Toast.makeText(getApplicationContext(), "Audio recorded successfully",Toast.LENGTH_LONG).show();
            }
        });
    }


}
