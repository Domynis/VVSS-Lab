package ro.eden.boutique.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import ro.eden.boutique.models.SearchData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVDataReader {

    public static List<SearchData> readSearchData(String fileName) {
        List<SearchData> searchDataList = new ArrayList<>();

        try (InputStream inputStream = CSVDataReader.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                SearchData searchData = new SearchData();

                if (record.isMapped("searchQuery")) {
                    searchData.setSearchQuery(record.get("searchQuery"));
                }

                if (record.isMapped("expectedResultCount")) {
                    searchData.setExpectedResultCount(Boolean.parseBoolean(record.get("expectedResultCount")));
                }

                if (record.isMapped("expectedEmptyResults")) {
                    searchData.setExpectedEmptyResults(Boolean.parseBoolean(record.get("expectedEmptyResults")));
                }

                searchDataList.add(searchData);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file: " + fileName, e);
        }

        return searchDataList;
    }
}