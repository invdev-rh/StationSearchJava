package com.example.controllers;

import com.example.LookupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
public class StationController {

    private LookupService s = new LookupService();


    @GetMapping("/api/values")
    public StationSearchResult getStations(@RequestParam(name = "filter") String filter) throws InterruptedException, ExecutionException {

        if (filter == "" || filter == null)
        {
            filter = " ";
        }

        filter = filter.replace("\"", "");

        var changedFilter = filter;

        var stations = s.getAllStartingWithAsync(filter).get();
        
        var nextChars = stations.stream().filter(station -> station.length() > changedFilter.length()).map(station -> station.charAt(changedFilter.length())).sorted().distinct();
        
        return new StationSearchResult(nextChars.toList(), stations.stream().sorted().toList());
    }
    
    
    
    public static class StationSearchResult {
        public final Iterable<String> stations;
        private final Iterable<Character> nextPossibleChars;
        
        public StationSearchResult(Iterable<Character> nextPossibleChars, Iterable<String> stations) {
            this.stations = stations;
            this.nextPossibleChars = nextPossibleChars;
        }
        
        public Iterable<String> getStations() {
            return stations;
        }
        
        public Iterable<Character> getNextPossibleChars() {
            return nextPossibleChars;
        }
    }
}
