package com.alvinquach.newsapp.data.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.alvinquach.newsapp.data.dao.NewsItemDao;
import com.alvinquach.newsapp.data.database.NewsRoomDatabase;
import com.alvinquach.newsapp.data.entity.NewsItem;
import com.alvinquach.newsapp.util.JsonUtils;
import com.alvinquach.newsapp.util.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsItemRepository {

    private NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mNewsItems;

    public NewsItemRepository(Context context) {
        NewsRoomDatabase db = NewsRoomDatabase.getDatabase(context);
        mNewsItemDao = db.newsItemDao();
        mNewsItems = mNewsItemDao.loadAllNewsItems();
    }

    public LiveData<List<NewsItem>> loadAllNewsItems() {
        return mNewsItems;
    }

    public void syncNewsItems() {
        new SyncNewsItemsTask(mNewsItemDao).execute();
    }

    private static class SyncNewsItemsTask extends AsyncTask<Void, Void, Void> {

        private NewsItemDao mAsyncTaskDao;

        SyncNewsItemsTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void ...objects) {

            // Clear all current entries in the database.
            mAsyncTaskDao.clearAll();

            String responseBody;
            try {

                // Call the API and get a result string.
                responseBody =  NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildUrl());
                Log.d("HELLO", responseBody);
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            // Parse results into a list of news objects.
            ArrayList<NewsItem> newsItems = JsonUtils.parseNews(responseBody);

            // Persist news into database
            mAsyncTaskDao.insert(newsItems);

            return null;
        }

    }

}
