package com.motu.motumap.search;

/**
 * Created by 20170418003 on 2017/5/11.
 */

public class SearchPoiEntity {

    private String longitude;
    private String latitude;
    private String name;
    private String district;
    //private String address;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
