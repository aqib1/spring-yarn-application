package com.example.yarnapplication.mapreduce;

import java.io.IOException;
import java.util.Objects;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.example.yarnapplication.exceptions.MapperInternalException;
import com.example.yarnapplication.helpers.Consts;

public class MapTask extends Mapper<LongWritable, Text, Text, IntWritable> {

	private Text hotelKey = new Text();

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if (Objects.isNull(value))
			throw new MapperInternalException("Text value from mapper [" + MapTask.class.getName() + "] is null");
		if (value.toString().contains(Consts.HEADER_OF_FILE))
			return;
		try {
			String hotelInfoKey = getHotelKey(value);
			if (!Consts.isNullOrEmptyString(hotelInfoKey)) {
				IntWritable intWritable = new IntWritable(1);
				hotelKey.set(hotelInfoKey);
				context.write(hotelKey, intWritable);
			} else {
				return;
			}
		} catch (MapperInternalException e) {
			throw e;
		}

	}

	private String getHotelKey(Text value) {
		String keyForRow = null;
		try {
			String line = value.toString();
			if (Consts.isNullOrEmptyString(line))
				throw new MapperInternalException(
						"Text string value is empty or null from mapper [" + MapTask.class.getName() + "]");
			String[] entities = line.split(",");
			if (isValidEntitiesLength(entities)) {
				int numberOfAdults = Integer.parseInt(entities[Consts.INDEX_SRCH_ADULTS_CNT]);
				if (numberOfAdults >= 2) { // pair
					String hotelContinent = entities[Consts.INDEX_HOTEL_CONTINENT];
					String hotelCountry = entities[Consts.INDEX_HOTEL_COUNTRY];
					String hotelMarket = entities[Consts.INDEX_HOTEL_MARKET];
					keyForRow = hotelContinent + "," + hotelCountry + "," + hotelMarket;

				}

			} else {
				throw new MapperInternalException(
						"Invalid Excel File Submit, [please use relevant test.csv provided by big data 101 training in EPAM (Standard columns required >=22)]");
			}
		} catch (Exception e) {
			throw new MapperInternalException("Internal Mapper Exception :[" + e.getMessage() + "]", e);
		}
		return keyForRow;
	}

	private boolean isValidEntitiesLength(String[] entities) {
		if (Objects.isNull(entities) || entities.length < 22) {
			return false;
		}
		return true;
	}
}
