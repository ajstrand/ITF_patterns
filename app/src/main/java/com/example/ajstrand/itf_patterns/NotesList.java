package com.example.ajstrand.itf_patterns;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NotesList extends RecyclerView.Adapter<NotesList.NoteViewHolder> {

    final static String EXTRA_MESSAGE = "MESSAGE";


    private final LayoutInflater mInflater;
    private List<PatternNote> mNotes; // Cached copy of words
    NotesList(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public NotesList.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.content_notes_list, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NotesList.NoteViewHolder holder, final int position) {
        if (mNotes != null) {
            PatternNote current = mNotes.get(position);
            final String patternNoteText = current.text;
            holder.wordItemView.setText(current.name);
            holder.wordItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle data = new Bundle();
                    data.putString("id", Integer.toString(position));
                    data.putString("content", patternNoteText);
                    Context localContext = holder.wordItemView.getContext();
                    Intent intent = new Intent(localContext, ShowNote.class);
                    //intent.putExtra(EXTRA_MESSAGE, patternNoteText);
                    intent.putExtras(data);
                    localContext.startActivity(intent);
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No notes");
        }
    }

    public void setPatternNotes(List<PatternNote> notes){
        mNotes = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mNotes != null)
            return mNotes.size();
        else return 0;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private NoteViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}
