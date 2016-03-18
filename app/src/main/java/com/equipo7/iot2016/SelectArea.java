package com.equipo7.iot2016;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.json.JSONArray;

public class SelectArea extends AppCompatActivity implements JSONRequest.JSONCallBack {
    private JSONArray js;
    private ListView list;
    private ButtonAdapter adapter;
    private IpAddress ip = new IpAddress();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_area);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        RelativeLayout lay = (RelativeLayout)findViewById(R.id.rl);
        lay.setBackgroundResource(R.drawable.pattern);
        new JSONRequest(this).execute(ip.BASE_URL + "/places");
        this.list = (ListView)findViewById(R.id.listView3);
    }

    public void buttonWasPressed(View v){
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("sectionId", buttonText);
        Log.e("", buttonText);
        startActivity(intent);
    }

    @Override
    public void requestComplete(JSONArray array) {
        this.js = array;
        this.adapter = new ButtonAdapter(array, this);
        this.list.setAdapter(adapter);
        this.list.setBackgroundColor(Color.TRANSPARENT);
    }
}
