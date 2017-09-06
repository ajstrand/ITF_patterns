package com.example.ajstrand.itf_patterns;

import org.json.JSONArray;

import java.util.HashMap;

/**
 * Created by ajstrand on 9/4/17.
 */

class PatternItem {

    public final String id;
    public final String title;
    public final JSONArray details;

    public PatternItem(String id, String title, JSONArray details) {
        this.id = id;
        this.title = title;
        this.details = details;
    }

    @Override
    public String toString() {
        return title;
    }
}
