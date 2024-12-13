package FurSPS.models;

public class AddressModel {
	private int userID;
	private String name;
	private String phone;
	private String city;
	private String detail;
	
	
	public AddressModel() {
		super();
	}

	public AddressModel(int userID, String name, String phone, String city, String detail) {
		super();
		this.userID = userID;
		this.name = name;
		this.phone = phone;
		this.city = city;
		this.detail = detail;
	}
	@Override
	public String toString() {
		return "AddressModel [userID=" + userID + ", phone=" + phone + ", city=" + city + ", Detail=" + detail + "]";
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	

}
