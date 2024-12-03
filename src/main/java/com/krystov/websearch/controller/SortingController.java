package com.krystov.websearch.controller;

import com.krystov.websearch.model.SortRequest;
import com.krystov.websearch.model.SortResponse;
import com.krystov.websearch.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class SortingController {

    @Autowired
    private SortingService sortingService;

    @PostMapping("/sort")
    public ResponseEntity<SortResponse> sort(@RequestBody SortRequest request) {
        long startTime = System.currentTimeMillis();
        int[] sortedData = sortingService.sort(request.getData(), request.getAlgorithm());
        long executionTime = System.currentTimeMillis() - startTime;

        SortResponse response = new SortResponse(sortedData, request.getAlgorithm(), executionTime);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/algorithms")
    public ResponseEntity<List<String>> getAlgorithms() {
        List<String> algorithms = List.of("heapsort", "quicksort", "mergesort", "radixsort", "bucketsort");
        return ResponseEntity.ok(algorithms);
    }
}