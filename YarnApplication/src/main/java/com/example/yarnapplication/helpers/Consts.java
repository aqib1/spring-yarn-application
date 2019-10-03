package com.example.yarnapplication.helpers;

import java.util.Objects;

public class Consts {

	public static <T> boolean isNullOrEmptyArray(T [] arr) {
		return Objects.isNull(arr) || arr.length == 0;
	}
	
	private Consts() {
		
	}
}
