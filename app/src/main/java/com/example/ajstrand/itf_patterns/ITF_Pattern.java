package com.example.ajstrand.itf_patterns;


import android.content.res.AssetManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by ajstrand on 9/4/17.
 */

public class ITF_Pattern {

    private Context context;

    public ITF_Pattern(Context context) {
        this.context = context;
    }

    /**
     * An array of sample (dummy) items.
     */
    public static final List<ITF_Pattern.PatternItem> ITEMS = new ArrayList<ITF_Pattern.PatternItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, ITF_Pattern.PatternItem> ITEM_MAP = new HashMap<String, ITF_Pattern.PatternItem>();


    protected void addItem(PatternItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    protected static String createStepsDetails(JSONObject stepsObj)  {
        StringBuilder builder = new StringBuilder();
        builder.append("\nMore details information here.");

        Iterator<String> iter = stepsObj.keys();

        while(iter.hasNext()){
            String key = iter.next();
            try {
                Object value = stepsObj.get(key);
                builder.append("Details about Item: ").append(value);

            }
            catch(JSONException e){
                e.printStackTrace();
            }
        }


        return builder.toString();
    }

    protected static PatternItem createPatternItem(String id, String title, String steps) {
        return new PatternItem(String.valueOf(id), "Item " + id, steps);
    }

    public static class PatternItem {

        public final String id;
        public final String title;
        public final String details;

        public PatternItem(String id, String title, String details) {
            this.id = id;
            this.title = title;
            this.details = details;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}

