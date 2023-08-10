package com.navi.Assignment3;

import java.time.LocalDate;

public class CreateUserRequest {
    private String email;
    private String country;
    private String category;
    private String[] preferredSources=null;
    private LocalDate fromDate;
    private LocalDate toDate;
    public String[] getPreferredSources() {
        return preferredSources;
    }

    public void setPreferredSources(String[] preferredSources) {
        this.preferredSources = preferredSources;
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

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }
}
