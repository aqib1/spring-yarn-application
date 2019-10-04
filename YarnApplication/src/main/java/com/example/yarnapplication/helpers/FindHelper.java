package com.example.yarnapplication.helpers;

import java.util.Arrays;
import java.util.Objects;

import com.example.yarnapplication.exceptions.InvalidRequestParamException;

/**
 * @author Aqib_Javed
 *
 */
public class FindHelper {

	private static FindHelper findHelper = null;

	private FindHelper() {

	}

	public FindHelper input(String[] args) {
		if (isValidInput(args)) {
			findHotels(args);
			return this;
		}
		return null;
	}

	private boolean isValidInput(String[] args) {
		if (Consts.isNullOrEmptyArray(args)) {
			throw new InvalidRequestParamException("Args [" + Arrays.toString(args) + "] empty or null is not allowed");
		}
		return true;
	}

	private void findHotels(String[] args) {
		PopularHotelFinder.getInstance().findHotels(args[0], args[1]);
		SortHotelHelper.getInstance().sortHotels(args[1], args[1] + Consts.SORTED_DIR);
		PrintHelper.getInstance().printResults(3, args[1] + Consts.SORTED_DIR + "/" + Consts.RESULT_MR_RESULT_NAME);
	}

	// double check locking
	public static FindHelper getInstance() {
		if (Objects.isNull(findHelper)) {
			synchronized (FindHelper.class) {
				if (Objects.isNull(findHelper)) {
					findHelper = new FindHelper();
				}
			}
		}
		return findHelper;
	}
}
