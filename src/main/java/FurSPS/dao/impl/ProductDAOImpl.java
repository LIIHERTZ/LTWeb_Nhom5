package FurSPS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import FurSPS.configs.DBConnection;
import FurSPS.dao.IItemDAO;
import FurSPS.dao.IProductDAO;
import FurSPS.models.ProductModel;

public class ProductDAOImpl implements IProductDAO {
	IItemDAO itemDAO = new ItemDAOImpl();
	Connection conn = null;

	@Override
	public List<ProductModel> findAll() {
//		String sql = "SELECT p.*, \r\n"
//				+ "       SUBSTRING_INDEX(GROUP_CONCAT(ii.Image ORDER BY ii.ItemImageID), ',', 1) AS FirstImage,\r\n"
//				+ "       (SELECT MIN(i.PromotionPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinPromotionPrice,\r\n"
//				+ "       (SELECT MIN(i.OriginalPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinOriginalPrice,\r\n"
//				+ "AVG(rating) as Rating\r\n" + "FROM CATEGORY c\r\n"
//				+ "JOIN PRODUCT p ON c.CategoryID = p.CategoryID\r\n" + "JOIN ITEM i ON p.ProductID = i.ProductID\r\n"
//				+ "JOIN ITEMIMAGE ii ON ii.ItemID = i.ItemID\r\n" + "LEFT JOIN DETAIL d ON d.ItemID=i.ItemID "
//				+ "GROUP BY p.ProductID;";

		///////
		String sql = "SELECT p.ProductID, " + "p.ProductName, " + "p.Description, " + "p.Origin, " + "p.SupplierID, "
				+ "p.CategoryID, " + "p.Material, "
				+ "(SELECT TOP 1 ii.Image FROM ITEMIMAGE ii WHERE ii.ItemID = i.ItemID ORDER BY ii.ItemImageID) AS FirstImage, "
				+ "(SELECT MIN(i.PromotionPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinPromotionPrice, "
				+ "(SELECT MIN(i.OriginalPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinOriginalPrice, "
				+ "AVG(d.rating) AS Rating " + "FROM PRODUCT p " + "JOIN ITEM i ON p.ProductID = i.ProductID "
				+ "JOIN ITEMIMAGE ii ON ii.ItemID = i.ItemID " + "LEFT JOIN DETAIL d ON d.ItemID = i.ItemID "
				+ "GROUP BY p.ProductID, p.ProductName, p.Description, p.Origin, p.SupplierID, p.CategoryID, p.Material, i.ItemID";
		List<ProductModel> list = new ArrayList<>();
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel model = new ProductModel();
				model.setProductID(rs.getInt("ProductID"));
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setOrigin(rs.getString("Origin"));
				model.setSupplierID(rs.getInt("SupplierID"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setMaterial(rs.getString("Material"));
				model.setAvgRating(rs.getFloat("Rating"));
				model.setDisplayedImage(rs.getString("FirstImage"));
				model.setDisplayedPromotionPrice(rs.getInt("MinPromotionPrice"));
				model.setDisplayedOriginalPrice(rs.getInt("MinOriginalPrice"));
				list.add(model);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductModel> findByCategoryID(int cateId) {
		String sql = "SELECT p.*, \r\n"
				+ "       SUBSTRING_INDEX(GROUP_CONCAT(ii.Image ORDER BY ii.ItemImageID), ',', 1) AS FirstImage,\r\n"
				+ "       (SELECT MIN(i.PromotionPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinPromotionPrice,\r\n"
				+ "       (SELECT MIN(i.OriginalPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinOriginalPrice,\r\n"
				+ "AVG(rating) as Rating\r\n" + "FROM CATEGORY c\r\n"
				+ "JOIN PRODUCT p ON c.CategoryID = p.CategoryID\r\n" + "JOIN ITEM i ON p.ProductID = i.ProductID\r\n"
				+ "JOIN ITEMIMAGE ii ON ii.ItemID = i.ItemID\r\n" + "LEFT JOIN DETAIL d ON d.ItemID=i.ItemID\r\n"
				+ "WHERE c.CategoryID = ?\r\n" + "GROUP BY p.ProductID;";
		List<ProductModel> list = new ArrayList<ProductModel>();

		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cateId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel model = new ProductModel();
				int productID = rs.getInt("ProductID");

				model.setProductID(productID);
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setOrigin(rs.getString("Origin"));
				model.setSupplierID(rs.getInt("SupplierID"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setMaterial(rs.getString("Material"));
				model.setAvgRating(rs.getFloat("Rating"));
				model.setDisplayedImage(rs.getString("FirstImage"));
				model.setDisplayedPromotionPrice(rs.getInt("MinPromotionPrice"));
				model.setDisplayedOriginalPrice(rs.getInt("MinOriginalPrice"));

				list.add(model);
			}
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductModel> findWithCount(int count) {
		String sql = "Select * from PRODUCT limit ?";
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, count);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel model = new ProductModel();

				model.setProductID(rs.getInt("ProductID"));
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setOrigin(rs.getString("Origin"));
				model.setSupplierID(rs.getInt("SupplierID"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setMaterial(rs.getString("Material"));

				list.add(model);
			}
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void insertProduct(ProductModel model) {
		String sql = "Insert into PRODUCT values (?,?,?,?,?,?,?)";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, model.getProductID());
			ps.setString(2, model.getProductName());
			ps.setString(3, model.getDescription());
			ps.setString(4, model.getOrigin());
			ps.setInt(5, model.getSupplierID());
			ps.setInt(6, model.getCategoryID());
			ps.setString(7, model.getMaterial());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProduct(int ProId) {
//		String sql = "Delete from PRODUCT where ProductID=?";
//		try {
//			new DBConnection();
//			conn = DBConnection.getConnection();
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, ProId);
//			ps.executeUpdate();
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		String deleteItemSql = "DELETE FROM ITEM WHERE ProductID = ?";
	    String deleteProductSql = "DELETE FROM PRODUCT WHERE ProductID = ?";
	    
	    try {
	        new DBConnection();
	        conn = DBConnection.getConnection();
	        conn.setAutoCommit(false); // Bắt đầu giao dịch

	        // Xóa các bản ghi liên quan trong bảng ITEM trước
	        PreparedStatement psItem = conn.prepareStatement(deleteItemSql);
	        psItem.setInt(1, ProId);
	        psItem.executeUpdate();
	        
	        // Xóa sản phẩm trong bảng PRODUCT
	        PreparedStatement psProduct = conn.prepareStatement(deleteProductSql);
	        psProduct.setInt(1, ProId);
	        psProduct.executeUpdate();

	        // Commit giao dịch
	        conn.commit();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        try {
	            if (conn != null) {
	                conn.rollback(); // Rollback nếu có lỗi xảy ra
	            }
	        } catch (Exception se) {
	            se.printStackTrace();
	        }
	    }
	}

	@Override
	public void updateProduct(ProductModel model) {
		String sql = "Update PRODUCT Set ProductName= ?, Description = ?, Origin = ?, SupplierID = ?, CategoryID = ?, Material = ? where ProductID = ?";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, model.getProductName());
			ps.setString(2, model.getDescription());
			ps.setString(3, model.getOrigin());
			ps.setInt(4, model.getSupplierID());
			ps.setInt(5, model.getCategoryID());
			ps.setString(6, model.getMaterial());
			ps.setInt(7, model.getProductID());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ProductModel> findAllOfCategory(int Id) {
		String sql = "Select * from PRODUCT where CategoryID =?";
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel model = new ProductModel();

				model.setProductID(rs.getInt("ProductID"));
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setOrigin(rs.getString("Origin"));
				model.setSupplierID(rs.getInt("SupplierID"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setMaterial(rs.getString("Material"));

				list.add(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ProductModel> searchProductByName(String key) {
		List<ProductModel> listPro = new ArrayList<ProductModel>();
		String sql = "SELECT p.*,\r\n"
				+ "       SUBSTRING_INDEX(GROUP_CONCAT(ii.Image ORDER BY ii.ItemImageID), ',', 1) AS FirstImage,\r\n"
				+ "       (SELECT MIN(i.PromotionPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinPromotionPrice,\r\n"
				+ "       (SELECT MIN(i.OriginalPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinOriginalPrice\r\n"
				+ "FROM CATEGORY c\r\n" + "JOIN PRODUCT p ON c.CategoryID = p.CategoryID\r\n"
				+ "JOIN ITEM i ON p.ProductID = i.ProductID\r\n" + "JOIN ITEMIMAGE ii ON ii.ItemID = i.ItemID\r\n"
				+ "WHERE p.ProductName LIKE ? OR p.Description LIKE ? \r\n" + "GROUP BY p.ProductID";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + key + "%");
			ps.setString(2, key + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel model = new ProductModel();
				int productID = rs.getInt("ProductID");
				model.setProductID(productID);
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setOrigin(rs.getString("Origin"));
				model.setSupplierID(rs.getInt("SupplierID"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setMaterial(rs.getString("Material"));
				model.setDisplayedImage(rs.getString("FirstImage"));
				model.setDisplayedPromotionPrice(rs.getInt("MinPromotionPrice"));
				model.setDisplayedOriginalPrice(rs.getInt("MinOriginalPrice"));
				listPro.add(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPro;
	}

	@Override
	public List<ProductModel> filterByPrice(int minPrice, int maxPrice) {
		List<ProductModel> listPro = new ArrayList<ProductModel>();
		String sql = "SELECT P.ProductID, P.ProductName,  P.Description, P.Origin, P.SupplierID,P.CategoryID,P.Material\r\n"
				+ "FROM PRODUCT as P \r\n" + "INNER JOIN ITEM I ON P.ProductID = I.ProductID\r\n"
				+ "group by P.ProductID\r\n" + "having Max(I.OriginalPrice) > ? and Min(I.OriginalPrice) < ?";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, minPrice);
			ps.setInt(2, maxPrice);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel model = new ProductModel();
				model.setProductID(rs.getInt("ProductID"));
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setOrigin(rs.getString("Origin"));
				model.setSupplierID(rs.getInt("SupplierID"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setMaterial(rs.getString("Material"));
				listPro.add(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPro;

	}

	@Override
	public List<ProductModel> filterByRating(int rate) {
		List<ProductModel> listPro = new ArrayList<ProductModel>();
		String sql = "SELECT P.ProductID, P.ProductName,  P.Description, P.Origin, P.SupplierID,P.CategoryID,P.Material \r\n"
				+ " FROM PRODUCT as P  \r\n" + " INNER JOIN ITEM I ON P.ProductID = I.ProductID \r\n"
				+ " INNER JOIN DETAIL D on I.ItemID= D.ItemID \r\n" + " WHERE D.Rating >=? \r\n"
				+ " GROUP BY P.ProductID";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rate);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel model = new ProductModel();
				model.setProductID(rs.getInt("ProductID"));
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setOrigin(rs.getString("Origin"));
				model.setSupplierID(rs.getInt("SupplierID"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setMaterial(rs.getString("Material"));
				listPro.add(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPro;
	}

	@Override
	public List<ProductModel> sortByPrice() {
		List<ProductModel> listPro = new ArrayList<ProductModel>();
		String sql = "SELECT P.ProductID, P.ProductName,  P.Description, P.Origin, P.SupplierID,P.CategoryID,P.Material \r\n"
				+ "FROM PRODUCT as P  \r\n" + "INNER JOIN ITEM I ON P.ProductID = I.ProductID \r\n"
				+ "GROUP BY P.ProductID \r\n" + "order by min(I.OriginalPrice)";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel model = new ProductModel();
				model.setProductID(rs.getInt("ProductID"));
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setOrigin(rs.getString("Origin"));
				model.setSupplierID(rs.getInt("SupplierID"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setMaterial(rs.getString("Material"));
				listPro.add(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPro;
	}

	@Override
	public ProductModel findOne(int id) {
		String sql = "SELECT\r\n" + "    p.*,\r\n" + "    c.CategoryID,\r\n" + "    c.CategoryName,\r\n"
				+ "    s.SupplierName,\r\n"
				+ "    (SELECT MIN(i.PromotionPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinPromotionPrice,\r\n"
				+ "    (SELECT MIN(i.OriginalPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinOriginalPrice,\r\n"
				+ "     AVG(rating) as Rating\r\n" + "FROM\r\n" + "    PRODUCT p\r\n" + "JOIN\r\n"
				+ "    ITEM i ON p.ProductID = i.ProductID\r\n" + "JOIN\r\n"
				+ "    CATEGORY c ON c.CategoryID = p.CategoryID\r\n" + "JOIN\r\n"
				+ "    SUPPLIER s ON s.SupplierID = p.SupplierID\r\n" + "LEFT JOIN\r\n"
				+ "    DETAIL d ON d.ItemID = i.ItemID\r\n" + "WHERE\r\n" + "    p.ProductID = ?\r\n" + "GROUP BY\r\n"
				+ "    p.ProductID;";
		ProductModel model = new ProductModel();

		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int productID = rs.getInt("ProductID");

				model.setProductID(productID);
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setOrigin(rs.getString("Origin"));
				model.setSupplierID(rs.getInt("SupplierID"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setMaterial(rs.getString("Material"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setCategoryName(rs.getString("CategoryName"));
				model.setSupplierName(rs.getString("SupplierName"));

				model.setAvgRating(rs.getFloat("rating"));

				model.setDisplayedPromotionPrice(rs.getInt("MinPromotionPrice"));
				model.setDisplayedOriginalPrice(rs.getInt("MinOriginalPrice"));
				model.setListItem(itemDAO.findByProductID(productID));

				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@Override
	public List<ProductModel> findAllProduct() {
		String sql = "Select * from  [PRODUCT]";
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel model = new ProductModel();

				model.setProductID(rs.getInt("ProductID"));
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setOrigin(rs.getString("Origin"));
				model.setSupplierID(rs.getInt("SupplierID"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setMaterial(rs.getString("Material"));

				list.add(model);
			}
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ProductModel findOneProduct(int id) {
		ProductModel model = new ProductModel();
		String sql = "Select * from PRODUCT where ProductID=?";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				model.setProductID(rs.getInt("ProductID"));
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setOrigin(rs.getString("Origin"));
				model.setSupplierID(rs.getInt("SupplierID"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setMaterial(rs.getString("Material"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	public List<ProductModel> findBySupplierID(int supplierId) {
		String sql = "SELECT p.*, \r\n"
				+ "       SUBSTRING_INDEX(GROUP_CONCAT(ii.Image ORDER BY ii.ItemImageID), ',', 1) AS FirstImage,\r\n"
				+ "       (SELECT MIN(i.PromotionPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinPromotionPrice,\r\n"
				+ "       (SELECT MIN(i.OriginalPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinOriginalPrice,\r\n"
				+ "AVG(rating) as Rating\r\n" + "FROM CATEGORY c\r\n"
				+ "JOIN PRODUCT p ON c.CategoryID = p.CategoryID\r\n" + "JOIN ITEM i ON p.ProductID = i.ProductID\r\n"
				+ "JOIN ITEMIMAGE ii ON ii.ItemID = i.ItemID\r\n" + "LEFT JOIN DETAIL d ON d.ItemID=i.ItemID\r\n"
				+ "WHERE p.SupplierID = ?\r\n" + "GROUP BY p.ProductID LIMIT 5;";
		List<ProductModel> list = new ArrayList<ProductModel>();

		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, supplierId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel model = new ProductModel();
				int productID = rs.getInt("ProductID");

				model.setProductID(productID);
				model.setProductName(rs.getString("ProductName"));
				model.setDescription(rs.getString("Description"));
				model.setOrigin(rs.getString("Origin"));
				model.setSupplierID(rs.getInt("SupplierID"));
				model.setCategoryID(rs.getInt("CategoryID"));
				model.setMaterial(rs.getString("Material"));
				model.setAvgRating(rs.getFloat("Rating"));
				model.setDisplayedImage(rs.getString("FirstImage"));
				model.setDisplayedPromotionPrice(rs.getInt("MinPromotionPrice"));
				model.setDisplayedOriginalPrice(rs.getInt("MinOriginalPrice"));

				list.add(model);
			}
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<List<Object>> ProductRating() {
//		List<List<Object>> list = new ArrayList<List<Object>>();
//		String sql = "select pr.ProductID, ProductName, round(avg(rating), 1)rate from (select ProductID, dt.ItemID, round(avg(Rating),1) as rating from DETAIL dt join ITEM it on dt.ItemID = it.ItemID group by dt.ItemID) q join PRODUCT pr on q.ProductID = pr.ProductID group by pr.ProductID having rate is not null order by rate desc limit 5";
//		try {
//			new DBConnection();
//			conn = DBConnection.getConnection();
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery(sql);
//			while (rs.next()) {
//				List<Object> model = new ArrayList<Object>();
//				model.add(rs.getInt("pr.ProductID"));
//				model.add(rs.getString("ProductName"));
//				model.add(rs.getBigDecimal("rate"));
//				list.add(model);
//			}
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
		List<List<Object>> list = new ArrayList<List<Object>>();
	    String sql = "select top 5 pr.ProductID, " + 
	                 "pr.ProductName, " + 
	                 "round(avg(q.rating), 1) as rate " + 
	                 "from ( " + 
	                 "   select it.ProductID, " + 
	                 "          dt.ItemID, " + 
	                 "          round(avg(dt.Rating), 1) as rating " + 
	                 "   from DETAIL dt " + 
	                 "   join ITEM it on dt.ItemID = it.ItemID " + 
	                 "   group by it.ProductID, dt.ItemID " + 
	                 ") q " + 
	                 "join PRODUCT pr on q.ProductID = pr.ProductID " + 
	                 "group by pr.ProductID, pr.ProductName " + 
	                 "having avg(q.rating) is not null " + 
	                 "order by rate desc";

	    try {
	        // Thiết lập kết nối với database
	        conn = DBConnection.getConnection();
	        
	        // Chuẩn bị câu lệnh SQL
	        PreparedStatement ps = conn.prepareStatement(sql);
	        
	        // Thực thi câu lệnh và lấy kết quả
	        ResultSet rs = ps.executeQuery();

	        // Duyệt qua kết quả trả về
	        while (rs.next()) {
	            List<Object> model = new ArrayList<Object>();
	            model.add(rs.getInt("ProductID"));
	            model.add(rs.getString("ProductName"));
	            model.add(rs.getBigDecimal("rate"));
	            list.add(model);
	        }

	        // Đóng kết nối
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	@Override
	public int getMaxProductID() {
		String sql = "SELECT MAX(ProductID) FROM PRODUCT";
	    int maxProductID = 0;
	    try {
	        new DBConnection();
	        conn = DBConnection.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            maxProductID = rs.getInt(1); // Lấy giá trị MAX của ProductID
	        }
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return maxProductID;
	}
}
