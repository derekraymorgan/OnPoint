package com.mystatscloud.onpoint.TestFacilityLocator;

import java.io.Serializable;

/**
 * Represents test facility address information
 */
public class TestFacility implements Serializable {
    private String address;
    private String city;
    private String state;
    private int zipCode;
    private String phone;
    private String fax;
    private double lat;
    private double lng;

    public TestFacility(String address, String city, String state, int zipCode, String phone, String fax, double lat, double lng) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phone = phone;
        this.fax = fax;
        this.lat = lat;
        this.lng = lng;
    }

    /**
     * @return Address of facility
     */
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return City of facility
     */
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return State of facility
     */
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return Zipcode of facility
     */
    public int getZipCode() {
        return zipCode;
    }


    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return Phone number of facility
     */
    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return Fax number of facility
     */
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return Latitude of facility
     */
    public double getLat() {
        return lat;
    }

    /**
     * @return Longitude of facility
     */
    public double getLng() {
        return lng;
    }
}
