package com.navi.Assignment3.Service;

import java.time.LocalDate;
import java.util.List;

public interface NewsApiService {
    public String createUser(String email, String country, String category,String[] preferredSources, LocalDate fromDate, LocalDate toDate);

    String ðcreateUser(String email, String country, String category, String[] preferredSources);

    List<String> getAvailableSources(String country, String category);


    Object getNewsFromPreferredSources(String userId);



    Object fetchNewsFromPreferredSources(String userId);

    Object fetchAllNewsArticles(String userId);

    public Object getSources(String userId);



}
