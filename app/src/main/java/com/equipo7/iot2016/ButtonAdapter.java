package com.equipo7.iot2016;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by TerceSpartan on 16/03/16.
 */
public class ButtonAdapter extends BaseAdapter {
    private JSONArray array;
    private Activity activity;

    public ButtonAdapter(JSONArray array, Activity activity) {
        this.activity = activity;
        this.array = new JSONArray();

        for (int i = 0; i < array.length(); i++){
            try {
                String string = array.getJSONObject(i).getString("type");

                if(string.compareTo("parking") < 0){
                    this.array.put(array.getJSONObject(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getCount() {
        return array.length();
    }

    @Override
    public Object getItem(int position) {
        JSONObject result = null;

        try {
            result = array.getJSONObject(position);
        }catch (JSONException jse){
            jse.printStackTrace();
        }
        return result;
    }

    @Override
    public long getItemId(int position) {
        long id = -1;
        try {
            JSONObject result = array.getJSONObject(position);
            id = result.getLong("id");
        }catch (JSONException jse){
            jse.printStackTrace();
        }
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null){
            convertView = activity.getLayoutInflater().inflate(R.layout.button_row, null);
        }
                try {
                    Button myButton = (Button) convertView.findViewById(R.id.buttonAdapter);
                    myButton.setText(Integer.toString(position));
                    myButton.setText(this.array.getJSONObject(position).getString("place"));

                } catch (JSONException e) {
                    Log.e("no place", e.toString());
                }
        return convertView;
    }
}
