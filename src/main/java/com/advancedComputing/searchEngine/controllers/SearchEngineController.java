package com.advancedComputing.searchEngine.controllers;

import com.advancedComputing.searchEngine.controllers.response.SearchEngineResponse;
import com.advancedComputing.searchEngine.service.SearchEngineMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchEngineController {
    @PostMapping(value = "/search/engine",produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> handleForgotPassword(@RequestParam("keyWord") String keyWord) {
        SearchEngineResponse searchEngineResponse= new SearchEngineResponse();
        searchEngineResponse.setUrls(SearchEngineMain.caller(keyWord));
        return ResponseEntity.ok(searchEngineResponse);
    }
}
