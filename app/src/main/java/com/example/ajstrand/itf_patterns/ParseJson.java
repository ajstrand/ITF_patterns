package com.example.ajstrand.itf_patterns;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import com.example.ajstrand.itf_patterns.ITF_Pattern;

/**
 * Created by ajstrand on 9/6/17.
 */

public class ParseJson extends AsyncTask<Void, String, String> {
    private Context context;

    protected ITF_Pattern patternStuff = new ITF_Pattern(context);

    public ParseJson(Context context){
        this.context = context;
    }

    protected void onPostExecute(JSONObject json){
        try {

            String testId = "1";


            JSONObject obj = new JSONObject(json);
            JSONObject top = (JSONObject) obj.get("curPatternObj");
            JSONObject stepsObj = top.getJSONObject("patternSteps");
            String patternTitle = top.getString("title");

            patternStuff.addItem(patternStuff.createPatternItem(testId, patternTitle, createStepsDetails(stepsObj)));

        }
        catch(JSONException e){
            e.printStackTrace();

        }
    }
    @Override
    protected String doInBackground(Void... params) {

        String json = null;


        try{
            InputStream is = context.getAssets().open("chon-ji.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "utf-8");
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
