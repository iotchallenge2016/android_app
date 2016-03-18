package com.equipo7.iot2016;

        import android.app.Activity;
        import android.graphics.Color;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

/**
 * Created by TerceSpartan on 06/03/16.
 */
public class JSONAdapter extends BaseAdapter{
    private JSONObject json;
    private Activity activity;
    private RelativeLayout rel;

    public JSONAdapter(JSONArray json, Activity activity, RelativeLayout rel) {
        try {
            this.json = json.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.activity = activity;
        this.rel = rel;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return this.json;
    }

    @Override
    public long getItemId(int position) {
        long id = -1;
        try {
            id = this.json.getLong("id");
        }catch (JSONException jse){
            jse.printStackTrace();
        }
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.json_row, null);
        }

        TextView section = (TextView)convertView.findViewById(R.id.textView);
        TextView lugares = (TextView)convertView.findViewById(R.id.textView2);
        TextView per = (TextView)convertView.findViewById(R.id.textView3);
        LinearLayout layout = (LinearLayout)convertView.findViewById(R.id.layout);

        try{
            String sec = this.json.getString("section") + "";
            sec = sec.replace("P_", "");
            section.setText("Estacionamiento " + sec);

            lugares.setText(new Double(this.json.getString("capacity")).intValue() + "");

            Integer percentage = new Double((Double.parseDouble(this.json.getString("capacity")) * 100) / Double.parseDouble(this.json.getString("max"))).intValue();

            if(percentage >= 90){
                layout.setBackgroundColor(Color.rgb(122, 203, 0));
                per.setText(Integer.toString(100 - percentage) + "%");
                this.rel.setBackgroundColor(Color.rgb(122,203,0));
            }else if(percentage >= 80 && percentage < 90){
                layout.setBackgroundColor(Color.rgb(155, 195, 1));
                per.setText(Integer.toString(100 - percentage) + "%");
                this.rel.setBackgroundColor(Color.rgb(155, 195, 1));
            }else if(percentage >= 70 && percentage < 80){
                layout.setBackgroundColor(Color.rgb(170,190,1));
                per.setText(Integer.toString(100 - percentage) + "%");
                this.rel.setBackgroundColor(Color.rgb(170,190,1));
            }else if(percentage >= 60 && percentage < 70){
                layout.setBackgroundColor(Color.rgb(185,185,1));
                per.setText(Integer.toString(100 - percentage) + "%");
                this.rel.setBackgroundColor(Color.rgb(185,185,1));
            }else if(percentage >= 50 && percentage < 60){
                layout.setBackgroundColor(Color.rgb(203,183,0));
                per.setText(Integer.toString(100 - percentage) + "%");
                this.rel.setBackgroundColor(Color.rgb(203,183,0));
            }else if(percentage >= 40 && percentage < 50){
                layout.setBackgroundColor(Color.rgb(211,157,0));
                per.setText(Integer.toString(100 - percentage) + "%");
                this.rel.setBackgroundColor(Color.rgb(211,157,0));
            }else if(percentage >= 30 && percentage < 40){
                layout.setBackgroundColor(Color.rgb(222,122,0));
                per.setText(Integer.toString(100 - percentage) + "%");
                this.rel.setBackgroundColor(Color.rgb(222,122,0));
            }else if(percentage >= 20 && percentage < 30){
                layout.setBackgroundColor(Color.rgb(225, 106, 0));
                per.setText(Integer.toString(100 - percentage) + "%");
                this.rel.setBackgroundColor(Color.rgb(225,106,0));
            }else if(percentage >= 10 && percentage < 20){
                layout.setBackgroundColor(Color.rgb(240,55,0));
                per.setText(Integer.toString(100 - percentage) + "%");
                this.rel.setBackgroundColor(Color.rgb(240,55,0));
            }else if(percentage >= 0 && percentage < 10){
                layout.setBackgroundColor(Color.rgb(255,0,0));
                per.setText(Integer.toString(100 - percentage) + "%");
                this.rel.setBackgroundColor(Color.rgb(255,0,0));
            }
        } catch (JSONException jse){
            jse.printStackTrace();
        }
        return convertView;
    }
}