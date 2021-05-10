package DTO;

public class PersonalDataDTO {

	private String country, province, location, postal_code, address, card, cvs, expiration;
	
	public PersonalDataDTO() {
		super();
		this.country = "";
		this.province = "";
		this.location = "";
		this.postal_code = "";
		this.address = "";
		this.card = "";
		this.cvs = "";
		this.expiration = "";
	}
	
	public PersonalDataDTO(String country, String province, String location, String postal_code, String address,
			String card, String cvs, String expiration) {
		super();
		this.country = country;
		this.province = province;
		this.location = location;
		this.postal_code = postal_code;
		this.address = address;
		this.card = card;
		this.cvs = cvs;
		this.expiration = expiration;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getCvs() {
		return cvs;
	}

	public void setCvs(String cvs) {
		this.cvs = cvs;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	
	
}
