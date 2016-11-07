package tech.salroid.filmy.parser;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tech.salroid.filmy.data_classes.FavouriteData;

/**
 * Created by salroid on 11/7/2016.
 */

public class FavouriteMovieParseWork {

    private Context context;
    private String result;

    public FavouriteMovieParseWork(Context context, String result) {
        this.context = context;
        this.result = result;
    }

    public List<FavouriteData> parse_favourite() {
        final List<FavouriteData> favouriteArray = new ArrayList<FavouriteData>();
        FavouriteData favouriteData = null;


        try {


            JSONObject jsonobject = new JSONObject(result);

            JSONArray jsonArray = jsonobject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                favouriteData = new FavouriteData();

                String title, id, movie_poster;

                id = jsonArray.getJSONObject(i).getString("id");
                movie_poster = "http://image.tmdb.org/t/p/w185" + jsonArray.getJSONObject(i).getString("poster_path");
                title = jsonArray.getJSONObject(i).getString("original_title");


                if (!(movie_poster.equals("null"))) {
                    favouriteData.setFav_id(id);
                    favouriteData.setFav_title(title);
                    favouriteData.setFav_poster(movie_poster);
                    favouriteArray.add(favouriteData);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return favouriteArray;
    }
}

