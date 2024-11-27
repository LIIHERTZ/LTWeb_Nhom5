package FurSPS.service;

import java.util.List;

import FurSPS.models.ItemModel;

public interface IItemService {
	List<ItemModel> findAll();
	List<ItemModel> findByProductID(int productID);
	List<ItemModel> findAllByProductID(int productID);
	ItemModel findOne(int id);
	void insertItem (ItemModel model);
	void deleteItem (int IteID);
	void updateItem (ItemModel model);
	ItemModel findOneByProductID(int productID);
	public int CreateItemID(int Id);
}
