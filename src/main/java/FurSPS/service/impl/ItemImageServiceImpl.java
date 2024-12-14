package FurSPS.service.impl;

import java.util.List;

import FurSPS.dao.IItemImageDAO;
import FurSPS.dao.impl.ItemImageDAOImpl;
import FurSPS.models.ItemImageModel;
import FurSPS.service.IItemImageService;

public class ItemImageServiceImpl implements IItemImageService {

	IItemImageDAO itemImage = new ItemImageDAOImpl();

	@Override
	public List<ItemImageModel> findByProductID(int productID) {
		return itemImage.findByProductID(productID);
	}

	@Override
	public void insertItemImage(ItemImageModel ItemId) {
		itemImage.insertItemImage(ItemId);
	}

	@Override
	public void deleteItemImage(int IteamId) {
		itemImage.deleteItemImage(IteamId);
	}

	@Override
	public void updateItemImage(ItemImageModel ItemId) {
		itemImage.updateItemImage(ItemId);
	}

	@Override
	public int CreateItemimageID(int Id) {
		return itemImage.CreateItemimageID(Id);
	}

}
