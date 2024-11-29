package FurSPS.dao;

import java.util.List;

import FurSPS.models.submodels.RatingModel;

public interface IRatingDAO {

	List<RatingModel> findRatinglByProductID(int productID);

}