package com.example.ajstrand.itf_patterns;


import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

import com.example.ajstrand.itf_patterns.PatternItem;

/**
 * Created by ajstrand on 9/4/17.
 */

public class ITF_Pattern {

   static ArrayList<HashMap<String, PatternItem>> patterns = new ArrayList<HashMap<String, PatternItem>>();

    static {
        setup();
    }

    public static String loadJSON(){
        String json = null;

       Context context = null;

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

   public static void setup() {
       try {

           String testId = "1";


           JSONObject  obj = new JSONObject(loadJSON());
           JSONArray stepsArr = obj.getJSONArray("patternSteps");
           String patternTitle = obj.getString("title");

           createSteps(stepsArr);

           createPatternItem(testId, patternTitle, stepsArr);

       }
       catch(JSONException e){
        e.printStackTrace();

       }

   }
    private static PatternItem createPatternItem(String id, String title, JSONArray steps) {
        return new PatternItem(String.valueOf(id), "Item " + id, steps);
    }
    public static HashMap<Integer, String> createSteps(JSONArray stepsArr) {
        HashMap<Integer, String > testObj = new HashMap<Integer, String>();

        for (int i = 0; i < stepsArr.length(); i++) {

            String test = null;
            try {
                test = stepsArr.getString(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Log.d("hello", test);
            testObj.put(i, test);

        }
        return testObj;

    }


}
