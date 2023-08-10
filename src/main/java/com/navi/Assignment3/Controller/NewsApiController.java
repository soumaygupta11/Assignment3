package com.navi.Assignment3.Controller;

import com.navi.Assignment3.CreateUserRequest;
import com.navi.Assignment3.Service.NewsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class NewsApiController {
    private final NewsApiService newsApiService;

    @Autowired
    public NewsApiController(NewsApiService newsApiService) {
        this.newsApiService = newsApiService;
    }

    @PostMapping("/create-user")
    public String createUser(@RequestBody CreateUserRequest request, @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                             @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate){
        String[] preferredSources = request.getPreferredSources();
        if (preferredSources == null) {
            preferredSources = new String[0]; // Set an empty array
        }
        return newsApiService.createUser(request.getEmail(), request.getCountry(), request.getCategory(), preferredSources, fromDate, toDate);
    }

    @GetMapping("/sources")
    public Object getSources(@RequestParam("user-id") String userId){
        return newsApiService.getSources(userId);
    }

    @GetMapping("/user-articles")
    public Object getNewsFromPreferredSources(@RequestParam("user-id") String userId){
        return newsApiService.getNewsFromPreferredSources(userId);
    }

}
