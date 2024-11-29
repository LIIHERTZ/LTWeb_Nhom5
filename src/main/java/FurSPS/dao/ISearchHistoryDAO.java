package FurSPS.dao;

import java.util.List;

import FurSPS.models.SearchHistoryModel;

public interface ISearchHistoryDAO {
	List<SearchHistoryModel> getHistorySearchByCID(int customerID);
	void insertSearchHistory(SearchHistoryModel model);
}
