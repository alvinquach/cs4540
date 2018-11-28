package com.alvinquach.newsapp.job;

import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.alvinquach.newsapp.data.repository.NewsItemRepository;
import com.alvinquach.newsapp.data.viewmodel.NewsItemViewModel;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class FirebaseJobService extends JobService {

    private NewsItemRepository mRepository;

    private AsyncTask mBackgroundTask;

    public FirebaseJobService() {
        Log.d("HELLO", "JOB CREATED");
    }


    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        Log.d("HELLO", "Running job");

        mBackgroundTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                Toast.makeText(FirebaseJobService.this, "News refreshed", Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void ...objects) {
                //mRepository.syncNewsItems();
                return null;
            }

            @Override
            protected void onPostExecute(Void o) {
                jobFinished(jobParameters, false);
                super.onPostExecute(o);
            }

        };

        mBackgroundTask.execute();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        if (mBackgroundTask != null) {
            mBackgroundTask.cancel(false);
        }

        return true;
    }
}
