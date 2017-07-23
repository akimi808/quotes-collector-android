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


import com.akimi808.quotescollector.fragments.SourceFragment.OnListFragmentInteractionListener;
import com.akimi808.quotescollector.fragments.dummy.DummyContent.DummyItem;
import com.akimi808.quotescollector.model.Source;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class SourceRecyclerViewAdapter extends RecyclerView.Adapter<SourceRecyclerViewAdapter.ViewHolder> implements QuoteManager.DataChangedListener {

    private final OnListFragmentInteractionListener listener;
    private QuoteManager quoteManager;
    private Activity activity;

    public SourceRecyclerViewAdapter(QuoteManager quoteManager, OnListFragmentInteractionListener listener, Activity activity) {
        this.quoteManager = quoteManager;
        this.listener = listener;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_source, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.source = quoteManager.getSourceByIndex(position);
        holder.id.setText(holder.source.getId().toString());
        holder.title.setText(holder.source.getTitle());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listener.onListFragmentInteraction(holder.source);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return quoteManager.getSourceCount();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        quoteManager.registerForDataChanged(this);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        quoteManager.unregisterForDataChanged(this);
        super.onDetachedFromRecyclerView(recyclerView);
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
        public final TextView id;
        public final TextView title;
        public Source source;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            id = (TextView) view.findViewById(R.id.id);
            title = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
