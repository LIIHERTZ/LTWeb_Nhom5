package FurSPS.service.impl;

import java.util.List;

import FurSPS.dao.IRatingDAO;
import FurSPS.dao.impl.RatingDAOImpl;
import FurSPS.models.submodels.RatingModel;
import FurSPS.service.IRatingService;

public class RatingServiceImpl implements IRatingService{
	IRatingDAO ratingDAO = new RatingDAOImpl();

	@Override
	public List<RatingModel> findRatinglByProductID(int productID) {
		return ratingDAO.findRatinglByProductID(productID);
	}

}
