package FurSPS.dao;

import java.util.Date;
import java.util.List;

import FurSPS.models.submodels.MyItem;
import FurSPS.models.submodels.Top3Customer;

public interface IReportDAO {
    public List<MyItem> reportReceipt(Date date, int limit);
    public List<MyItem> reportKPISeller(Date date, int id);
    public List<MyItem> reportBestItemSeller(int id);
    public List<List<Object>> reportTotalMoneyInMonth();
    public List<Top3Customer> reportTop3Customer();
    public List<List<Object>> reportSellerOrderByYear(int sellerID);
}
