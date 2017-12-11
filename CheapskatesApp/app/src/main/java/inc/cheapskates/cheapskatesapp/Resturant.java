package inc.cheapskates.cheapskatesapp;

import java.io.Serializable;

//Creates restaurant class
public class Resturant implements Serializable {
    private String id;
    private String name;
    private String longitude;
    private String latitude;
    private String address;
    private String rating;
    private String locality;


    //getters and setters for the members of the class
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
