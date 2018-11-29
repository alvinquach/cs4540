package com.alvinquach.newsapp.job;

import android.os.AsyncTask;
import android.util.Log;

import com.alvinquach.newsapp.data.repository.NewsItemRepository;
import com.alvinquach.newsapp.notification.NotificationUtils;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class FirebaseJobService extends JobService {

    private NewsItemRepository mRepository = new NewsItemRepository(this);

    private AsyncTask<Void, Void, Void> mBackgroundTask;

    public FirebaseJobService() {
        Log.d("HELLO", "JOB CREATED");
    }


    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        Log.d("HELLO", "Running job");

        mBackgroundTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... objects) {
                mRepository.syncNewsItems();
                NotificationUtils.remindUserBecauseCharging(FirebaseJobService.this);
                return null;
            }

            @Override
            protected void onPostExecute(Void o) {
                jobFinished(jobParameters, false);
                Log.d("HELLO", "JOB FINISHED");
            }

        };

        mBackgroundTask.execute();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if (mBackgroundTask != null) {
            mBackgroundTask.cancel(true);
        }
        return true;
    }

}
