package com.alvinquach.newsapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.alvinquach.newsapp.adapter.NewsAdapter;
import com.alvinquach.newsapp.model.NewsItem;
import com.alvinquach.newsapp.util.JsonUtils;
import com.alvinquach.newsapp.util.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    protected NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.news_recyclerview);
        mAdapter = new NewsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            NewsQueryTask task = new NewsQueryTask();
            task.execute();
            return true;
        }
        return false;
    }

    private class NewsQueryTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void[] objects) {
            String result = null;
            try {
                result = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildUrl());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ArrayList<NewsItem> newsItems = JsonUtils.parseNews(result);
            mAdapter.updateNewsItems(newsItems);
            mAdapter.notifyDataSetChanged();
        }

    }

}
