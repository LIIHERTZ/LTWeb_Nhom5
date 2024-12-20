package FurSPS.service.impl;

import java.util.List;

import FurSPS.dao.IItemDAO;
import FurSPS.dao.impl.ItemDAOImpl;
import FurSPS.models.ItemModel;
import FurSPS.service.IItemService;

public class ItemServiceImpl implements IItemService{
	IItemDAO itemDAO = new ItemDAOImpl();

	@Override
	public List<ItemModel> findAll() {
		return itemDAO.findAll();
	}

	@Override
	public List<ItemModel> findByProductID(int productID) {
		return itemDAO.findByProductID(productID);
	}

	@Override
	public ItemModel findOne(int id) {
		return itemDAO.findOne(id);
	}

	@Override
	public void insertItem(ItemModel model) {
		itemDAO.insertItem(model);
	}

	@Override
	public void deleteItem(int IteID) {
		itemDAO.deleteItem(IteID);
	}

	@Override
	public void updateItem(ItemModel model) {
		itemDAO.updateItem(model);
	}

	@Override
	public ItemModel findOneByProductID(int productID) {
		return itemDAO.findOneByProductID(productID);
	}

	@Override
	public int CreateItemID(int Id) {
	    int maxItemID = itemDAO.getMaxItemID();
	    return maxItemID + 1;
	}

	@Override
	public List<ItemModel> findAllByProductID(int productID) {
		return itemDAO.findAllByProductID(productID);
	}

}
