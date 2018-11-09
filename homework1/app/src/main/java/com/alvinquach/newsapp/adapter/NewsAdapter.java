package com.alvinquach.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alvinquach.newsapp.R;
import com.alvinquach.newsapp.WebActivity;
import com.alvinquach.newsapp.model.NewsItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy h:mm a");

    private Context mContext;
    private List<NewsItem> mNewsItems = new ArrayList<>();

    public NewsAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }

    @Override
    public NewsAdapter.NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        NewsItemViewHolder viewHolder = new NewsItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.NewsItemViewHolder holder, int position) {
        holder.bind(position);
    }

    public void updateNewsItems(Collection<NewsItem> newsItems) {
        mNewsItems.clear();
        mNewsItems.addAll(newsItems);
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private TextView description;
        private TextView date;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
        }

        private void bind(int index) {
            NewsItem newsItem = mNewsItems.get(index);
            title.setText("Title: " + newsItem.getTitle());
            description.setText("Description: " + newsItem.getDescription());
            date.setText("Date: " + DATE_FORMAT.format(newsItem.getPublishedAt()));
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            NewsItem newsItem = mNewsItems.get(getAdapterPosition());
            String url = newsItem.getUrl();
            Intent intent = new Intent(mContext, WebActivity.class);
            intent.putExtra("urlString", url);
            mContext.startActivity(intent);
        }
    }
}
