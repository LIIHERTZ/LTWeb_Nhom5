package FurSPS.service;

import java.util.List;

import FurSPS.models.SearchHistoryModel;

public interface ISearchHistoryService {
	List<SearchHistoryModel> getHistorySearchByCID(int customerID);
	void insertSearchHistory(SearchHistoryModel model);
}
