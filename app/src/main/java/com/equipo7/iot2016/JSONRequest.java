package com.equipo7.iot2016;

        import android.os.AsyncTask;
        import org.json.JSONArray;
        import java.io.BufferedReader;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.URL;
        import java.net.URLConnection;

public class JSONRequest extends AsyncTask<String, Void, JSONArray> {

    private JSONCallBack callback;

    public JSONRequest(JSONCallBack callback){
        this.callback = callback;
    }

    @Override
    protected JSONArray doInBackground(String... params) {
        URLConnection connection = null;
        BufferedReader br = null;
        JSONArray result = null;

        try{
            //Open connection
            URL url = new URL(params[0]);
            connection = (URLConnection) url.openConnection();

            //Build JSON array
            InputStream is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();
            String line = "";

            boolean first = true;
            boolean wasObject = false;
            while((line = br.readLine()) != null){
                if (first){
                    if (!line.startsWith("[")){
                        wasObject = true;
                        builder.append("[" + line);
                    } else {
                        builder.append(line);
                    }
                    first = false;
                }
                else{
                    builder.append(line);
                }
            }

            if (wasObject) {
                builder.append("]");
            }

            result = new JSONArray(builder.toString());

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                //close the connection
                if(br != null){
                    br.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);
        callback.requestComplete(jsonArray);
    }

    //Callback
    public interface JSONCallBack{
        void requestComplete(JSONArray array);
    }
}