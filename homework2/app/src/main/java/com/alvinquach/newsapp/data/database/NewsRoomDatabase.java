package com.alvinquach.newsapp.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.alvinquach.newsapp.data.dao.NewsItemDao;
import com.alvinquach.newsapp.data.entity.NewsItem;

@Database(entities = {NewsItem.class}, version = 1)
public abstract class NewsRoomDatabase extends RoomDatabase {

    public abstract NewsItemDao newsItemDao();

    private static volatile NewsRoomDatabase INSTANCE;

    public static NewsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        NewsRoomDatabase.class,
                        "news_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }

}
