package com.alvinquach.newsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.alvinquach.newsapp.util.NetworkUtils;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    private static class NewsQueryTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void[] objects) {
            String result = null;
            try {
                result = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildUrl());
                Log.d("WTF", result);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

    }

}
