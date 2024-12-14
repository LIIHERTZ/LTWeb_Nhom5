package FurSPS.models.submodels;

public class RatingModel {
	
	private int numOfStar;
	private int numOfRating;
	private String link;
	




	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public RatingModel(int numOfStar, int numOfRating, String link) {
		super();
		this.numOfStar = numOfStar;
		this.numOfRating = numOfRating;
		this.link = link;
	}

	public int getNumOfStar() {
		return numOfStar;
	}

	public void setNumOfStar(int numOfStar) {
		this.numOfStar = numOfStar;
	}

	public int getNumOfRating() {
		return numOfRating;
	}

	public void setNumOfRating(int numOfRating) {
		this.numOfRating = numOfRating;
	}


	public RatingModel() {
		super();
	}
	@Override
    public String toString() {
        return "RatingModel{" +
                "numOfStar=" + numOfStar +
                ", numOfRating=" + numOfRating +
                '}';
    }
}
