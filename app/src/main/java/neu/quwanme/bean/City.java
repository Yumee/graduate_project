package neu.quwanme.bean;

public class City {
    /**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column city.city_id
	 * @abatorgenerated  Fri Apr 08 12:04:43 CST 2016
	 */
	private Integer cityId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column city.city_name
	 * @abatorgenerated  Fri Apr 08 12:04:43 CST 2016
	 */
	private String cityName;

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column city.city_id
	 * @return  the value of city.city_id
	 * @abatorgenerated  Fri Apr 08 12:04:43 CST 2016
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column city.city_id
	 * @param cityId  the value for city.city_id
	 * @abatorgenerated  Fri Apr 08 12:04:43 CST 2016
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column city.city_name
	 * @return  the value of city.city_name
	 * @abatorgenerated  Fri Apr 08 12:04:43 CST 2016
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column city.city_name
	 * @param cityName  the value for city.city_name
	 * @abatorgenerated  Fri Apr 08 12:04:43 CST 2016
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + "]";
	}
   
}