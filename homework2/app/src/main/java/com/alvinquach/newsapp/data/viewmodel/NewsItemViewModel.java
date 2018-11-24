package com.alvinquach.newsapp.data.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.alvinquach.newsapp.data.entity.NewsItem;
import com.alvinquach.newsapp.data.repository.NewsItemRepository;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {

    private NewsItemRepository mRepository;

    private LiveData<List<NewsItem>> mNewsItems;

    public NewsItemViewModel(Application application) {
        super(application);
        mRepository = new NewsItemRepository(application);
        mNewsItems = mRepository.loadAllNewsItems();
    }

    public LiveData<List<NewsItem>> getAllNewsItems() {
        return mNewsItems;
    }

    public void syncNewsItems() {
        mRepository.syncNewsItems();
    }

}
