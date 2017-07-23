package com.akimi808.quotescollector.fragments;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akimi808.quotescollector.QuoteManager;
import com.akimi808.quotescollector.R;

import com.akimi808.quotescollector.fragments.AuthorFragment.OnListFragmentInteractionListener;
import com.akimi808.quotescollector.fragments.dummy.DummyContent.DummyItem;
import com.akimi808.quotescollector.model.Author;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AuthorRecyclerViewAdapter extends RecyclerView.Adapter<AuthorRecyclerViewAdapter.ViewHolder> implements QuoteManager.DataChangedListener {

    private final OnListFragmentInteractionListener listener;
    private Activity activity;
    private QuoteManager quoteManager;


    public AuthorRecyclerViewAdapter(QuoteManager quoteManager, OnListFragmentInteractionListener listener, Activity activity) {
        this.quoteManager = quoteManager;
        this.listener = listener;
        this.activity = activity;
        quoteManager.registerForDataChanged(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_author, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.author = quoteManager.getAuthorByIndex(position);
        holder.idView.setText(holder.author.getId().toString());
        holder.authorNameView.setText(holder.author.getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listener.onListFragmentInteraction(holder.author);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return quoteManager.getAuthorCount();
    }

    @Override
    public void onDataChanged() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView idView;
        public final TextView authorNameView;
        public Author author;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            idView = (TextView) view.findViewById(R.id.id);
            authorNameView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + authorNameView.getText() + "'";
        }
    }
}
