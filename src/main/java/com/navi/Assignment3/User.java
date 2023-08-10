package com.navi.Assignment3;

import java.time.LocalDate;
import java.util.UUID;

public class User {
    private String userId;
    private String email;
    private String country;
    private String category;
    private String[] preferredSources;

    private LocalDate fromDate;

    private LocalDate toDate;


public User(String email, String country, String category,String[] preferredSources, LocalDate fromDate, LocalDate toDate) {
        this.userId = UUID.randomUUID().toString();
        this.email = email;
        this.country = country;
        this.category = category;
        this.preferredSources= preferredSources;
        this.fromDate = LocalDate.now().minusDays(1);
        this.toDate = LocalDate.now();
    }

    public String[] getPreferredSources() {
        return preferredSources;
    }

    public void setPreferredSources(String[] preferredSources) {
        this.preferredSources = preferredSources;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
