package com.example.ajstrand.itf_patterns;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ajstrand.itf_patterns.ITF_Pattern;




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

    /**
     * The dummy content this fragment is presenting.
     */
    //private DummyContent.DummyItem mItem;

    private ITF_Pattern.PatternItem myItem;

    ITF_Pattern test = new ITF_Pattern();



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PatternDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        test.setup();

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            myItem = ITF_Pattern.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
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

        // Show the dummy content as text in a TextView.
        if (myItem != null) {
            ((TextView) rootView.findViewById(R.id.pattern_detail)).setText(myItem.details);
        }

        return rootView;
    }
}
