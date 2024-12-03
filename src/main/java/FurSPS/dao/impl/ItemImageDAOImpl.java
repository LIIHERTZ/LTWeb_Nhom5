package FurSPS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import FurSPS.configs.DBConnection;
import FurSPS.dao.IItemImageDAO;
import FurSPS.models.ItemImageModel;

public class ItemImageDAOImpl implements IItemImageDAO {
	Connection conn = null;
	
	@Override
	public List<ItemImageModel> findByProductID(int id) {
		
		String sql = "Select * from ITEMIMAGE join ITEM on ITEMIMAGE.ItemID = ITEM.ItemID where ITEM.ItemID=?";
		List<ItemImageModel> list = new ArrayList<ItemImageModel>();
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ItemImageModel model = new ItemImageModel();
				model.setItemimageID(rs.getInt("ItemImageID"));
				model.setItemID(rs.getInt("ItemID"));
				model.setImage(rs.getString("Image"));
				list.add(model);
			}
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

    
    @Override
	public void insertItemImage(ItemImageModel model) {
		String sql = "Insert into ITEMIMAGE values (?,?,?)";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,model.getItemimageID());
			ps.setInt(2, model.getItemID());
			ps.setString(3, model.getImage());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteItemImage(int IteamId) {
		String sql = "Delete from ITEMIMAGE where ItemID = ?";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, IteamId);
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateItemImage(ItemImageModel model) {
		String sql = "Update ITEMIMAGE Set Image = ? where ItemImageID = ?";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, model.getImage());
			ps.setString(3, model.getImage());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public int CreateItemimageID(int Id) {
		String sql = "SELECT MAX(ItemImageID) FROM ITEMIMAGE";
	    try {
	        new DBConnection();
	        conn = DBConnection.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            int maxId = rs.getInt(1);
	            return maxId + 1;  // Trả về ID tiếp theo
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return Id * 100 + 1; // Trường hợp không có dữ liệu, trả về giá trị mặc định
	}

}
