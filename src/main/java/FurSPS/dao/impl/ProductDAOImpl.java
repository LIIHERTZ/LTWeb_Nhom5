package FurSPS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import FurSPS.configs.DBConnection;
import FurSPS.dao.IItemDAO;
import FurSPS.dao.IProductDAO;
import FurSPS.models.ProductModel;
import FurSPS.dao.ISupplierDAO;
import FurSPS.models.SupplierModel;

public class ProductDAOImpl implements IProductDAO {
	IItemDAO itemDAO = new ItemDAOImpl();
	ISupplierDAO supDao =new SupplierDAOImpl();
	Connection conn = null;

	@Override
	public List<ProductModel> findAll() {
	    String sql = "SELECT "
	               + "    p.*, "
	               + "    (SELECT TOP 1 ii.Image "
	               + "     FROM ITEMIMAGE ii "
	               + "     JOIN ITEM i ON ii.ItemID = i.ItemID "
	               + "     WHERE i.ProductID = p.ProductID "
	               + "     ORDER BY ii.ItemImageID) AS FirstImage, "
	               + "    (SELECT MIN(i.PromotionPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinPromotionPrice, "
	               + "    (SELECT MIN(i.OriginalPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinOriginalPrice, "
	               + "    COALESCE(AVG(d.Rating), 0) AS Rating " 
	               + "FROM CATEGORY c "
	               + "JOIN PRODUCT p ON c.CategoryID = p.CategoryID "
	               + "JOIN ITEM i ON p.ProductID = i.ProductID "
	               + "LEFT JOIN DETAIL d ON d.ItemID = i.ItemID "
	               + "GROUP BY p.ProductID, p.ProductName, p.CategoryID, p.Description, p.SupplierID, p.Material, p.Origin;";

	    List<ProductModel> list = new ArrayList<>();

	    try {
	        // Kết nối cơ sở dữ liệu
	        conn = DBConnection.getConnection();

	        // Tạo PreparedStatement
	        PreparedStatement ps = conn.prepareStatement(sql);

	        // Thực thi truy vấn
	        ResultSet rs = ps.executeQuery();

	        // Duyệt qua kết quả và thêm vào danh sách
	        while (rs.next()) {
	            ProductModel model = new ProductModel();
	            SupplierModel supplier = supDao.findOne(rs.getInt("SupplierID"));
	            model.setProductID(rs.getInt("ProductID"));
	            model.setProductName(rs.getString("ProductName"));
	            model.setDescription(rs.getString("Description"));
	            model.setOrigin(rs.getString("Origin"));
	            model.setSupplierID(rs.getInt("SupplierID"));
	            model.setSupplierName(supplier.getSupplierName());
	            model.setCategoryID(rs.getInt("CategoryID"));
	            model.setMaterial(rs.getString("Material"));
	            model.setAvgRating(rs.getFloat("Rating")); // Đọc giá trị trung bình đánh giá
	            model.setDisplayedImage(rs.getString("FirstImage")); // Hình ảnh đầu tiên
	            model.setDisplayedPromotionPrice(rs.getInt("MinPromotionPrice")); // Giá khuyến mãi thấp nhất
	            model.setDisplayedOriginalPrice(rs.getInt("MinOriginalPrice")); // Giá gốc thấp nhất

	            list.add(model); // Thêm vào danh sách
	        }

	        // Đóng kết nối
	        rs.close();
	        ps.close();
	        conn.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list; // Trả về danh sách
	}


	@Override
	public List<ProductModel> findByCategoryID(int cateId) {
		String sql ="SELECT p.ProductID, p.ProductName, p.CategoryID, p.Description, p.SupplierID, p.Material, p.Origin, "
		           + "(SELECT TOP 1 ii.Image FROM ITEMIMAGE ii JOIN ITEM i ON ii.ItemID = i.ItemID WHERE i.ProductID = p.ProductID ORDER BY ii.ItemImageID) AS FirstImage, "
		           + "(SELECT MIN(i.PromotionPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinPromotionPrice, "
		           + "(SELECT MIN(i.OriginalPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinOriginalPrice, "
		           + "COALESCE(AVG(d.Rating), 0) AS Rating "
		           + "FROM CATEGORY c "
		           + "JOIN PRODUCT p ON c.CategoryID = p.CategoryID "
		           + "JOIN ITEM i ON p.ProductID = i.ProductID "
		           + "LEFT JOIN DETAIL d ON d.ItemID = i.ItemID "
		           + "WHERE (c.ParentCategoryID IS NOT NULL AND c.CategoryID = ?) "
		           + "OR (c.ParentCategoryID = ? AND c.CategoryID IN (SELECT CategoryID FROM CATEGORY WHERE ParentCategoryID = ?)) "
		           + "GROUP BY p.ProductID, p.ProductName, p.CategoryID, p.Description, p.SupplierID, p.Material, p.Origin;";

		List<ProductModel> list = new ArrayList<ProductModel>();

		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cateId);
			ps.setInt(2, cateId);
			ps.setInt(3, cateId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductModel model = new ProductModel();
				int productID = rs.getInt("ProductID");
				SupplierModel supplier = supDao.findOne(rs.getInt("SupplierID"));
				model.setSupplierName(supplier.getSupplierName());

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
		String sql = "Delete from PRODUCT where ProductID=?";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ProId);
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
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
				SupplierModel supplier = supDao.findOne(rs.getInt("SupplierID"));
				model.setSupplierName(supplier.getSupplierName());
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
				SupplierModel supplier = supDao.findOne(rs.getInt("SupplierID"));
				model.setSupplierName(supplier.getSupplierName());
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
				SupplierModel supplier = supDao.findOne(rs.getInt("SupplierID"));
				model.setSupplierName(supplier.getSupplierName());
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
				SupplierModel supplier = supDao.findOne(rs.getInt("SupplierID"));
				model.setSupplierName(supplier.getSupplierName());
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
		String sql =  "SELECT p.ProductID, p.ProductName, p.Description, MAX(p.Origin) AS Origin, p.SupplierID, p.Material, p.CategoryID, c.CategoryID, c.CategoryName, s.SupplierName, " +
	               "(SELECT MIN(i.PromotionPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinPromotionPrice, " +
	               "(SELECT MIN(i.OriginalPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinOriginalPrice, " +
	               "(SELECT AVG(CAST(d.rating AS FLOAT)) " +
	               "FROM ITEM i2 " +
	               "RIGHT JOIN DETAIL d ON d.ItemID = i2.ItemID " +
	               "WHERE i2.ProductID = p.ProductID) AS Rating " +
	               "FROM PRODUCT p " +
	               "JOIN ITEM i ON p.ProductID = i.ProductID " +
	               "JOIN CATEGORY c ON c.CategoryID = p.CategoryID " +
	               "JOIN SUPPLIER s ON s.SupplierID = p.SupplierID " +
	               "WHERE p.ProductID = ? " +
	               "GROUP BY p.ProductID, p.ProductName, p.Description, p.SupplierID, p.Material, p.CategoryID, c.CategoryID, c.CategoryName, s.SupplierName;";
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
			
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@Override
	public List<ProductModel> findAllProduct() {
		String sql = "Select * from PRODUCT";
		List<ProductModel> list = new ArrayList<ProductModel>();
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
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
		String sql = "SELECT p.*, " +
	               "(SELECT TOP 1 ii.Image FROM ITEMIMAGE ii JOIN ITEM i ON ii.ItemID = i.ItemID WHERE i.ProductID = p.ProductID ORDER BY ii.ItemImageID) AS FirstImage, " +
	               "(SELECT MIN(i.PromotionPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinPromotionPrice, " +
	               "(SELECT MIN(i.OriginalPrice) FROM ITEM i WHERE i.ProductID = p.ProductID) AS MinOriginalPrice, " +
	               "COALESCE(AVG(d.Rating), 0) AS Rating " +
	               "FROM CATEGORY c " +
	               "JOIN PRODUCT p ON c.CategoryID = p.CategoryID " +
	               "JOIN ITEM i ON p.ProductID = i.ProductID " +
	               "LEFT JOIN DETAIL d ON d.ItemID = i.ItemID " +
	               "WHERE p.SupplierID = ? " +
	               "GROUP BY p.ProductID, p.ProductName, p.CategoryID, p.Description, p.SupplierID, p.Material, p.Origin " +
	               "ORDER BY p.ProductID " +
	               "OFFSET 0 ROWS FETCH NEXT 4 ROWS ONLY";
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
				SupplierModel supplier = supDao.findOne(rs.getInt("SupplierID"));
				model.setSupplierName(supplier.getSupplierName());

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
		List <List<Object>> list = new ArrayList<List<Object>>();
		String sql = "select pr.ProductID, ProductName, round(avg(rating), 1)rate from (select ProductID, dt.ItemID, round(avg(Rating),1) as rating from DETAIL dt join ITEM it on dt.ItemID = it.ItemID group by dt.ItemID) q join PRODUCT pr on q.ProductID = pr.ProductID group by pr.ProductID having rate is not null order by rate desc limit 5";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next())
			{
				List<Object> model = new ArrayList<Object>();
				model.add(rs.getInt("pr.ProductID"));
				model.add(rs.getString("ProductName"));
				model.add(rs.getBigDecimal("rate"));
				list.add(model);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
