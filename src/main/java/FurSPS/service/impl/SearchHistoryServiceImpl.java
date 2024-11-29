package FurSPS.service.impl;

import java.util.List;

import FurSPS.dao.ISearchHistoryDAO;
import FurSPS.dao.impl.SearchHistoryDAOImpl;
import FurSPS.models.SearchHistoryModel;
import FurSPS.service.ISearchHistoryService;

public class SearchHistoryServiceImpl implements ISearchHistoryService{
	ISearchHistoryDAO searchHistoryDao=new SearchHistoryDAOImpl();
	@Override
	public List<SearchHistoryModel> getHistorySearchByCID(int customerID) {
		return searchHistoryDao.getHistorySearchByCID(customerID);
	}
	@Override
	public void insertSearchHistory(SearchHistoryModel model) {
		searchHistoryDao.insertSearchHistory(model);
	}

}
