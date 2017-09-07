package com.example.ajstrand.itf_patterns;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.example.ajstrand.itf_patterns.ITF_Pattern;

/**
 * Created by ajstrand on 9/6/17.
 */

public class ParseJson extends AsyncTask<Void, String, ArrayList<String>> {
    private Context context;

    protected ITF_Pattern patternStuff = new ITF_Pattern(context);

    public ParseJson(Context context){
        this.context = context;
    }


    protected void onPostExecute(ArrayList<String> jsonFiles){
        try {

            for(int i = 0;i<jsonFiles.size() - 1;i++){
                int id = i;
                String jsonObjString = jsonFiles.get(i);
                JSONObject obj = new JSONObject(jsonObjString);
                JSONObject top = (JSONObject) obj.get("curPatternObj");
                JSONObject stepsObj = top.getJSONObject("patternSteps");
                String patternTitle = top.getString("title");

                patternStuff.addItem(patternStuff.createPatternItem(id, patternTitle, patternStuff.createStepsDetails(stepsObj)));
            }
        }
        catch(JSONException e){
            e.printStackTrace();

        }
    }
    @Override
    protected ArrayList<String> doInBackground(Void... params) {

        ArrayList<String> jsonFiles = new ArrayList<String>();
        String dirName = "jsonFiles";
        AssetManager am = context.getAssets();
        try {
            String filesList[] = am.list(dirName);
            for(int i = 0; i< filesList.length;i++){
                String file = filesList[i];
                String temp = null;
                InputStream is = am.open(filesList[4]);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                temp = new String(buffer, "utf-8");
                jsonFiles.add(i, temp);
                System.out.println("added file");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonFiles;
    }
}
