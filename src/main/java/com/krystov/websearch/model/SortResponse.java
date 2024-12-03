package com.krystov.websearch.model;

import org.springframework.hateoas.RepresentationModel;

public class SortResponse extends RepresentationModel<SortResponse> {
    private int[] sortedData;
    private String algorithm;
    private long executionTimeMs;

    // Constructor
    public SortResponse(int[] sortedData, String algorithm, long executionTimeMs) {
        this.sortedData = sortedData;
        this.algorithm = algorithm;
        this.executionTimeMs = executionTimeMs;
    }

    // Getters
    public int[] getSortedData() { return sortedData; }
    public String getAlgorithm() { return algorithm; }
    public long getExecutionTimeMs() { return executionTimeMs; }
}
