package inc.cheapskates.cheapskatesapp;

import java.io.Serializable;

/**
 * Created by PRIYA on 10-Dec-17.
 */

public class Resturant implements Serializable {
    String restaurant;
    String apikey;
    String id;
    String name;

    String url;

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
