package com.example.ajstrand.itf_patterns;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by ajstrand on 9/6/17.
 */

public class ParseJson extends AsyncTask<Void, String, ArrayList<String>> {
    private Context localContext;
    private PatternListActivity patternListActivityRef;
    private ArrayList<String> jsonFiles = new ArrayList<String>();

    private ITF_Pattern patternStuff;
    ParseJson(Context context, PatternListActivity patternListActivity){
        patternStuff = new ITF_Pattern(context);
        localContext = context;
        patternListActivityRef = patternListActivity;
    }


    protected void onPostExecute(ArrayList<String> jsonFiles){
        for(int i = 0;i<jsonFiles.size() - 1;i++){
           createItem(i);
        }
        patternListActivityRef.start();
    }

    private void createItem(int i) {
        String jsonObjString = jsonFiles.get(i);
        JSONObject obj = null;
        try {
            obj = new JSONObject(jsonObjString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject top = null;
        try {
            assert obj != null;
            top = (JSONObject) obj.get("curPatternObj");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject stepsObj = null;
        try {
            assert top != null;
            stepsObj = top.getJSONObject("patternSteps");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String patternTitle = null;
        try {
            patternTitle = top.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String createStepsResult = null;
        if (stepsObj != null) {
            createStepsResult = ITF_Pattern.createStepsDetails(stepsObj);
        }
        ITF_Pattern.PatternItem createdItem = ITF_Pattern.createPatternItem(i, patternTitle, createStepsResult);

        patternStuff.addItem(createdItem);
    }
    @Override
    protected ArrayList<String> doInBackground(Void... params) {
        String dirName = "jsonFiles";
        AssetManager am = localContext.getAssets();
        try {
            String filesList[] = am.list(dirName);
            if (filesList != null) {
                for (String file : filesList) {
                    createFile(file, am, dirName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonFiles;
    }

    private void createFile(String file, AssetManager am, String dirName) {
        String temp = null;
        InputStream is = null;
        try {
            is = am.open(dirName+"/"+file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int size = 0;
        try {
            if (is != null) {
                size = is.available();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[size];
        try {
            if (is != null) {
                is.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            temp = new String(buffer, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        jsonFiles.add(temp);
        System.out.println("added file");
    }
}
