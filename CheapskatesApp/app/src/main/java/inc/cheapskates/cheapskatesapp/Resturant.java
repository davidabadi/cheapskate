package inc.cheapskates.cheapskatesapp;

import java.io.Serializable;


public class Resturant implements Serializable {
    String restaurant;
    String apikey;
    String id;
    String name;
    String longitude;
    String latitude;
    String address;
    String rating;
    String locality;

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

    public String getLongitude(){return longitude;}

    public String getLatitude(){return latitude;}

    public String getAddress(){return address;}

    public String getRating(){return rating;}

    public String getLocality(){return locality;}

    public void setLongitude(String longitude){this.longitude = longitude;}

    public void setLatitude(String latitude){this.latitude = latitude;}

    public void setAddress(String address){this.address = address;}

    public void setRating(String rating){this.rating = rating;}

    public void setLocality(String locality){this.locality = locality;}
}
