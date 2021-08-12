package pojo;

public class SearchData {

    private String searchCriteria;
    private int expectedResults;

    public SearchData(String _searchCriteria, int _expectedResults) {
        this.searchCriteria = _searchCriteria;
        this.expectedResults = _expectedResults;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public void setExpectedResults(int expectedResults) {
        this.expectedResults = expectedResults;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public int getExpectedResults() {
        return expectedResults;
    }


}
