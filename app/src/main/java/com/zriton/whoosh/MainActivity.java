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
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton recordButton,pauseButton ;
    ListView list;
    private MediaRecorder myAudioRecorder;
    private String outputFile = null;
    public static String [] sounds={"Sound 1","Sound 2","Sound 3","Sound 4","Sound 5","Sound 6","Sound 7","Sound 8","Sound 9","Sound 10"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //listview
        list=(ListView)findViewById(R.id.listView);
        list.setAdapter(new CustomAdapter(this,sounds));

        recordButton= (FloatingActionButton) findViewById(R.id.record_button);
        pauseButton=(FloatingActionButton)findViewById(R.id.pause_button);


        outputFile = android.os.Environment.getExternalStorageDirectory() + "/Recording/test_" + System.currentTimeMillis() + ".mp3";
        boolean exists = (new File(android.os.Environment.getExternalStorageDirectory()+"/Recording/")).exists();

        if(!exists)
        {
            new File(android.os.Environment.getExternalStorageDirectory()+"/Recording/").mkdirs();

        }


        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordButton.setVisibility(View.GONE);
                pauseButton.setVisibility(View.VISIBLE);

                myAudioRecorder=new MediaRecorder();
                myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.DEFAULT);
                myAudioRecorder.setOutputFile(outputFile);

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
                Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_SHORT).show();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseButton.setVisibility(View.GONE);
                recordButton.setVisibility(View.VISIBLE);

                myAudioRecorder.stop();
                myAudioRecorder.reset();
                myAudioRecorder.release();
                myAudioRecorder  = null;


                Toast.makeText(getApplicationContext(), "Audio recorded successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
