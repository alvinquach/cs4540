package com.alvinquach.newsapp.util;

import com.alvinquach.newsapp.model.NewsItem;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class JsonUtils {

    public static final ObjectMapper mapper = new ObjectMapper();

    public static ArrayList<NewsItem> parseNews(String jsonString) {
        ArrayList<NewsItem> result = new ArrayList<>();
        try {
            JsonNode jsonNode = mapper.readTree(jsonString);
            JsonNode articles = jsonNode.get("articles");
            if (articles != null && articles.isArray()) {
                for (JsonNode article : articles) {
                    NewsItem newsItem = mapper.treeToValue(article, NewsItem.class);
                    result.add(newsItem);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}


