package FurSPS.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import FurSPS.configs.DBConnection;
import FurSPS.dao.ICategoryDAO;
import FurSPS.models.CategoryModel;

public class CategoryDAOImpl implements ICategoryDAO {
	Connection conn = null;

	@Override
	public List<CategoryModel> findAll() {

		List<CategoryModel> Lcategory = new ArrayList<CategoryModel>();
		String sql = "Select * from CATEGORY ";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryID(rs.getInt("CategoryID"));
				category.setCategoryName(rs.getString("CategoryName"));
				category.setImage(rs.getString("Image"));
				Object oj = rs.getObject("ParentCategoryID");
				if (oj == null)
					category.setParentCategoryID(0);
				else
					category.setParentCategoryID(rs.getInt("ParentCategoryID"));
				Lcategory.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lcategory;
	}

	@Override
	public CategoryModel findOne(int id) {
		CategoryModel category = new CategoryModel();
		String sql = "Select * from CATEGORY where CategoryID=?";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				category = new CategoryModel();
				category.setCategoryID(rs.getInt("CategoryID"));
				category.setCategoryName(rs.getString("CategoryName"));
				category.setParentCategoryID(rs.getInt("ParentCategoryID"));
				category.setImage(rs.getString("Image"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public void insert(CategoryModel model) {
	}

	@Override
	public void update(CategoryModel model) {
	}

	@Override
	public void delete(int id) {

	}

	@Override
	public List<CategoryModel> getCategoriesByParentId(int parentId) {
		String sql = "SELECT * FROM CATEGORY WHERE ParentCategoryID=?";
		String sqlWithNoneParent = "SELECT * FROM CATEGORY WHERE ParentCategoryID is NULL";
		List<CategoryModel> listCategory = new ArrayList<CategoryModel>();

		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(parentId == 0 ? sqlWithNoneParent : sql);
			if (parentId != 0) {
				ps.setInt(1, parentId);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryID(rs.getInt("CategoryID"));
				category.setCategoryName(rs.getString("CategoryName"));
				category.setImage(rs.getString("Image"));

				if (rs.getObject("ParentCategoryID") == null) {
					category.setParentCategoryID(0);
				} else {
					category.setParentCategoryID(rs.getInt("ParentCategoryID"));
				}

				listCategory.add(category);
			}
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listCategory;
	}

	@Override
	public CategoryModel findRootCategoryByCategoryId(int id) {
		CategoryModel category = new CategoryModel();
		String sql = "WITH CategoryPath AS ("
		           + "    SELECT CategoryID, ParentCategoryID, CategoryName, Image "
		           + "    FROM CATEGORY "
		           + "    WHERE CategoryID = ? "
		           + "    UNION ALL "
		           + "    SELECT c.CategoryID, c.ParentCategoryID, c.CategoryName, c.Image "
		           + "    FROM CATEGORY c "
		           + "    JOIN CategoryPath cp ON c.ParentCategoryID = cp.CategoryID "
		           + ") "
		           + "SELECT * "
		           + "FROM CategoryPath "
		           + "WHERE ParentCategoryID IS NULL;";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				category = new CategoryModel();
				category.setCategoryID(rs.getInt("CategoryID"));
				category.setCategoryName(rs.getString("CategoryName"));
				category.setImage(rs.getString("Image"));

				if (rs.getObject("ParentCategoryID") == null) {
					category.setParentCategoryID(0);
				} else {
					category.setParentCategoryID(rs.getInt("ParentCategoryID"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}
}
