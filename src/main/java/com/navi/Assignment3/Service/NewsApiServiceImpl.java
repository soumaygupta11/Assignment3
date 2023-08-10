package com.navi.Assignment3.Service;

import com.navi.Assignment3.Response.Source;
import com.navi.Assignment3.Response.SourceResponse;
import com.navi.Assignment3.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NewsApiServiceImpl implements NewsApiService{

    public List<User> usersList = new ArrayList<>();
    public final RestTemplate restTemplate = new RestTemplate();
    @Value("${newsapi.apiKey}") // Add your API key in your application.properties
    private String apiKey;


    public NewsApiServiceImpl(){

    }




    @Override
    public String createUser(String email, String country, String category, String[] preferredSources, LocalDate fromDate, LocalDate toDate) {

        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            List<String> availableSources = getAvailableSources(country, category);
            boolean allPreferredSourcesAvailable = Arrays.stream(preferredSources)
                    .allMatch(availableSources::contains);

            if (allPreferredSourcesAvailable) {
                User user = addUser(email, country, category, preferredSources);
                return "User is successfully added in the list with user id = " + user.getUserId();
            } else {
                return "Some of the preferred sources are not available for the selected country and category.";
            }
        }

        return "Invalid email id";

    }

    @Override
    public List<String> getAvailableSources(String country, String category) {
        String apiUrl = "https://newsapi.org/v2/sources";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("country", country)
                .queryParam("category", category)
                .queryParam("apiKey", apiKey);


        ResponseEntity<SourceResponse> response = restTemplate.getForEntity(
                uriBuilder.toUriString(),
                SourceResponse.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            SourceResponse sourceResponse = response.getBody();
            if (sourceResponse != null && sourceResponse.getSources() != null) {
                List<Source> sources = sourceResponse.getSources();
                List<String> availableSources = new ArrayList<>();
                for (Source source : sources) {
                    availableSources.add(source.getId());
                }
                return availableSources;
            }
        }

        return new ArrayList<>();
    }

    @Override
    public Object getNewsFromPreferredSources(String userId) {
        User user = getUserById(userId);

        if (user == null) {
            return Collections.emptyList();
        }

        String[] preferredSources = user.getPreferredSources();

        if (preferredSources == null || preferredSources.length == 0) {
            // No preferred sources specified, fetch all news articles
            System.out.println("No preferred sources specified, fetch all news articles");
            return fetchAllNewsArticles(userId);
        } else {
            System.out.println("Preferred sources specified, fetch news from preferred sources");
            return fetchNewsFromPreferredSources(userId);
        }
    }



    @Override
    public Object fetchNewsFromPreferredSources(String userId){
        User user = getUserById(userId);



        String[] preferredSources = user.getPreferredSources();

        if (preferredSources == null || preferredSources.length == 0) {
            // No preferred sources provided by the user, return an appropriate response
            return "No preferred sources specified by the user.";
        }

        String API_KEY = "968045807c804799bcb183abb1e60d66";
        String sourcesQueryParam = String.join(",", preferredSources);

        String resourceUrl = "https://newsapi.org/v2/top-headlines?sources=" + sourcesQueryParam + "&apiKey=" + API_KEY;

        return restTemplate.getForObject(resourceUrl, Object.class);

    }

    @Override
    public Object fetchAllNewsArticles(String userId){


        String targetCountry = "";
        String targetCategory = "";

        String API_KEY = "f3a0b655a767492bbbaecf69e092a50a";
        boolean validUser = false;
        for(User user : usersList){
            if(userId.equals(user.getUserId())){
                targetCountry = user.getCountry();
                targetCategory = user.getCategory();
                validUser = true;
                break;
            }
        }

        String resourceUrl = "https://newsapi.org/v2/top-headlines?country=" + targetCountry + "&category=" + targetCategory + "&apiKey=" + API_KEY;

        return restTemplate.getForObject(resourceUrl, Object.class);


    }

    private User getUserById(String userId) {
        for (User user : usersList) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Object getSources(String userId) {
        String targetCountry = "";
        String targetCategory = "";

        String API_KEY = "968045807c804799bcb183abb1e60d66";
        boolean validUser = false;
        for(User user : usersList){
            if(userId.equals(user.getUserId())){
                targetCountry = user.getCountry();
                targetCategory = user.getCategory();
                validUser = true;
                break;
            }
        }
        String resourceUrl = "https://newsapi.org/v2/top-headlines/sources?country=" + targetCountry + "&category=" + targetCategory + "&apiKey=" + API_KEY;

        return restTemplate.getForObject(resourceUrl, Object.class);
    }

    private User addUser(String email, String country, String category,String[] preferredSources, LocalDate fromDate, LocalDate toDate) {
        User user = new User(email, country, category,preferredSources);
        user.setFromDate(fromDate);
        user.setToDate(toDate);
        usersList.add(user);
        System.out.println("User added");
        System.out.println(user.getUserId());
        return user;
    }
}
