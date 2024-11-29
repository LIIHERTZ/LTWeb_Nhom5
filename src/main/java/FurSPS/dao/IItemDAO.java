package FurSPS.dao;

import java.util.List;

import FurSPS.models.ItemModel;

public interface IItemDAO {
	List<ItemModel> findAll();
	List<ItemModel> findByProductID(int productID);
	List<ItemModel> findAllByProductID(int productID);
	ItemModel findOneByProductID(int productID);
	ItemModel findOne(int id);
	void insertItem (ItemModel model);
	void deleteItem (int ItemId);
	void updateItem (ItemModel model);
	int findDisplayedPromotionPrice(int productID);
	int findDisplayedOriginalPrice(int productID);
}