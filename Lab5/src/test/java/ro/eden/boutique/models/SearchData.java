package ro.eden.boutique.models;

import lombok.Data;

@Data
public class SearchData {
    private String searchQuery;
    private boolean expectedResultCount;
    private boolean expectedEmptyResults;
}