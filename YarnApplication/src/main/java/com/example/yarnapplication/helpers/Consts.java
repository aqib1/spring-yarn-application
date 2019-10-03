package com.example.yarnapplication.helpers;

import java.util.Objects;

public class Consts {
	
	public static final String HEADER_OF_FILE = "id,date_time,site_name,posa_continent,user_location_country,user_location_region,user_location_city,orig_destination_distance,user_id,is_mobile,is_package,channel,srch_ci,srch_co,srch_adults_cnt,srch_children_cnt,srch_rm_cnt,srch_destination_id,srch_destination_type_id,hotel_continent,hotel_country,hotel_market";
	public static final int INDEX_SRCH_ADULTS_CNT = 14;
	public static final int INDEX_HOTEL_CONTINENT = 19;
	public static final int INDEX_HOTEL_COUNTRY = 20;
	public static final int INDEX_HOTEL_MARKET = 21;

	public static <T> boolean isNullOrEmptyArray(T [] arr) {
		return Objects.isNull(arr) || arr.length == 0;
	}
	public static final boolean isNullOrEmptyString(String val) {
		return Objects.isNull(val) || val.isEmpty();
	}
	private Consts() {
		
	}
}
