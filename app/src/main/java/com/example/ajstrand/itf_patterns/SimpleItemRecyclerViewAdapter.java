package com.example.ajstrand.itf_patterns;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SimpleItemRecyclerViewAdapter
        extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private boolean twoPane;

    FragmentManager con;

    private final List<ITF_Pattern.PatternItem> mValues;

    private ArrayList<String> foo = new ArrayList<String>(0);


    protected SimpleItemRecyclerViewAdapter(List<ITF_Pattern.PatternItem> items, boolean mTwoPane, FragmentManager context) {
        foo.add("one");
        foo.add("two");
        foo.add("three");
        mValues = items;
        twoPane = mTwoPane;
        con = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pattern_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).title);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (twoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putInt(PatternDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                    PatternDetailFragment fragment = new PatternDetailFragment();
                    fragment.setArguments(arguments);
                    con.beginTransaction()
                            .replace(R.id.pattern_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, PatternDetailActivity.class);
                    intent.putExtra(PatternDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public ITF_Pattern.PatternItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
