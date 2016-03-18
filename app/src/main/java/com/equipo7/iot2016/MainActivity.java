package com.equipo7.iot2016;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.json.JSONArray;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements JSONRequest.JSONCallBack {

    private ListView list;
    private JSONArray js;
    private JSONAdapter adapter;
    private Button button;
    private MainActivity activity;
    private IpAddress ip;
    private String sectionId;
    private RelativeLayout rel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.ip = new IpAddress();
        this.list = (ListView)findViewById(R.id.listView);
        this.rel = (RelativeLayout)findViewById(R.id.relLayout);
        this.activity = this;
        Intent intent = getIntent();
        this.sectionId = intent.getStringExtra("sectionId");


        new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    try {
                        makeRequest();
                        Thread.sleep(2000);
                        Log.e("thread","thread");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();

        // Request JSON

    }


    // Update from JSON
    public void makeRequest(){
        try {
            String string = ip.BASE_URL + "/graph/" + this.sectionId;
            URL url = new URL(string);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());

            new JSONRequest(this).execute(uri.toURL().toString());
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    // Retrieve Array of JSONs and set adapter to the ListView
    @Override
    public void requestComplete(JSONArray json) {
        this.js = json;
        this.adapter = new JSONAdapter(json, this, this.rel);
        this.list.setAdapter(adapter);
    }
}
