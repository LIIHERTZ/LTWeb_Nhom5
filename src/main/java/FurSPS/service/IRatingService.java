package FurSPS.service;

import java.util.List;

import FurSPS.models.submodels.RatingModel;

public interface IRatingService {
	List<RatingModel> findRatinglByProductID(int productID);

}
