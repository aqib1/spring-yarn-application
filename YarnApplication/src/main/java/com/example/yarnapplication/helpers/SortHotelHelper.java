package com.example.yarnapplication.helpers;

import java.io.IOException;
import java.util.Objects;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.Job;

import com.example.yarnapplication.exceptions.InternalMalfunctionException;
import com.example.yarnapplication.mapreduce.SortMapTask;

public class SortHotelHelper {

	private static SortHotelHelper sortHotelHelper;

	private SortHotelHelper() {

	}

	@SuppressWarnings("deprecation")
	public SortHotelHelper sortHotels(String inputDir, String outPut) {
		try {
			Path input = new Path(inputDir);
			Path output = new Path(outPut);

			Configuration configuration = new Configuration(true);
			Job job = new Job(configuration, "Job for sorting data");
			job.setJarByClass(SortHotelHelper.class);

			job.setMapperClass(SortMapTask.class);

			job.setMapOutputKeyClass(IntWritable.class);
			job.setMapOutputValueClass(Text.class);
			job.setOutputKeyClass(IntWritable.class);
			job.setOutputValueClass(Text.class);

			FileInputFormat.addInputPath(job, input);
			job.setInputFormatClass(TextInputFormat.class);

			FileOutputFormat.setOutputPath(job, output);
			job.setOutputFormatClass(TextOutputFormat.class);

			job.waitForCompletion(true);
		} catch (IOException e) {
			e.printStackTrace();
			throw new InternalMalfunctionException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new InternalMalfunctionException(e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new InternalMalfunctionException(e);
		}

		return this;
	}

	public static SortHotelHelper getInstance() {
		if (Objects.isNull(sortHotelHelper)) {
			synchronized (PopularHotelFinder.class) {
				if (Objects.isNull(sortHotelHelper)) {
					sortHotelHelper = new SortHotelHelper();
				}
			}
		}
		return sortHotelHelper;
	}
}
