package FurSPS.service;

import java.util.List;

import FurSPS.models.CategoryModel;
import FurSPS.models.submodels.CategoryLevelModel;

public interface ICategoryService {
	CategoryModel findOne(int id);
	CategoryModel findRootCategoryByCategoryId(int id);
	List<CategoryLevelModel> getCategoryLevels();
	List<CategoryModel> getRootCategories();
	List<CategoryModel> geChidlCategories(int parentId);
    List<CategoryModel> getCategoriesByParentId(int parentId);
}

	