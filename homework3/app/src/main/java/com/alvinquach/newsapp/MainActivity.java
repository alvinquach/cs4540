package com.alvinquach.newsapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.alvinquach.newsapp.adapter.NewsAdapter;
import com.alvinquach.newsapp.data.viewmodel.NewsItemViewModel;


public class MainActivity extends AppCompatActivity {

    private NewsItemViewModel mNewsItemViewModel;

    private RecyclerView mRecyclerView;
    protected NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.news_recyclerview);
        mNewsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);
        mAdapter = new NewsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNewsItemViewModel.getAllNewsItems().observe(this, (items) -> {
            mAdapter.updateNewsItems(items);
            mAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            mNewsItemViewModel.syncNewsItems();
            return true;
        }
        return false;
    }

}
