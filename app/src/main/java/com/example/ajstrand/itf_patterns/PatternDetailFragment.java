package com.example.ajstrand.itf_patterns;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ajstrand.itf_patterns.ITF_Pattern;

import static android.R.attr.button;


/**
 * A fragment representing a single Pattern detail screen.
 * This fragment is either contained in a {@link PatternListActivity}
 * in two-pane mode (on tablets) or a {@link PatternDetailActivity}
 * on handsets.
 */
public class PatternDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";




    private ITF_Pattern.PatternItem myItem;



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PatternDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = this.getActivity();

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            Bundle test = activity.getIntent().getExtras();

            Integer myID = test.getInt("item_id");
            myItem = ITF_Pattern.ITEM_MAP.get(myID);

            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(myItem.title);

            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pattern_detail, container, false);

        if (myItem != null) {
            ((TextView) rootView.findViewById(R.id.pattern_detail)).setText(myItem.details);
        }

        return rootView;
    }
}
