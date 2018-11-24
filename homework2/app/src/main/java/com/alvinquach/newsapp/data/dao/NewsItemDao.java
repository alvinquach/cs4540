package com.alvinquach.newsapp.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.alvinquach.newsapp.data.entity.NewsItem;

import java.util.List;

@Dao
public interface NewsItemDao {

    @Query("SELECT * FROM news_item ORDER BY publishedAt DESC")
    LiveData<List<NewsItem>> loadAllNewsItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<NewsItem> newsItems);

    @Query("DELETE FROM news_item")
    void clearAll();

}
