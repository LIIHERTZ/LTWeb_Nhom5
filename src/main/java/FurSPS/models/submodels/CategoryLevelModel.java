package FurSPS.models.submodels;

import java.util.List;

import FurSPS.models.CategoryModel;

public class CategoryLevelModel extends CategoryModel {
	List<CategoryLevelModel> childrens;

	public CategoryLevelModel(List<CategoryLevelModel> childrens) {
		super();
		this.childrens = childrens;
	}

	public CategoryLevelModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryLevelModel(int categoryID, String categoryName, int parentCategoryID, String image) {
		super(categoryID, categoryName, parentCategoryID, image);
		// TODO Auto-generated constructor stub
	}

	public List<CategoryLevelModel> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<CategoryLevelModel> childrens) {
		this.childrens = childrens;
	}

	@Override
    public String toString() {
        return "CategoryLevelModel{" +
                "categoryID=" + getCategoryID() +
                ", categoryName='" + getCategoryName() + '\'' +
                ", parentCategoryID=" + getParentCategoryID() +
                ", image='" + getImage() + '\'' +
                ", childrens=" + (childrens != null ? childrens.size() : 0) + 
                '}';
    }

}
