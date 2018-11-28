package com.alvinquach.newsapp.util;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private final static String BASE_URL = "https://newsapi.org/v1/articles";

    private final static String SOURCE_PARAM_NAME = "source";

    private final static String SOURCE_PARAM_VALUE = "the-next-web";

    private final static String SORT_PARAM_NAME = "sortBy";

    private final static String SORT_PARAM_VALUE = "latest";

    private final static String API_KEY_PARAM_NAME = "apiKey";

    private final static String API_KEY = "d0ec7c04b5e54bcd96337ba5a64d126d";

    public static URL buildUrl() {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(SOURCE_PARAM_NAME, SOURCE_PARAM_VALUE)
                .appendQueryParameter(SORT_PARAM_NAME, SORT_PARAM_VALUE)
                .appendQueryParameter(API_KEY_PARAM_NAME, API_KEY)
                .build();

        try {
            return new URL(uri.toString());
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Copied from assignment description.
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            }
            else {
                return null;
            }
        }
        finally {
            urlConnection.disconnect();
        }
    }

}
