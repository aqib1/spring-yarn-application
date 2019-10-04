package com.example.yarnapplication.helpers;

import java.io.IOException;
import java.util.Objects;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.example.yarnapplication.exceptions.InternalMalfunctionException;
import com.example.yarnapplication.exceptions.JobExecutionFailedException;
import com.example.yarnapplication.mapreduce.MapTask;
import com.example.yarnapplication.mapreduce.ReduceTask;

public class PopularHotelFinder {

	private static PopularHotelFinder popularHotelFinder;

	private PopularHotelFinder() {
	}

	@SuppressWarnings("deprecation")
	public PopularHotelFinder findHotels(String input, String output) {
		Path inputPath = new Path(input);
		Path outpuPath = new Path(output);

		Configuration conf = new Configuration(true);
		try {

			Job job = new Job(conf, "Finding Popular Hotels");
			job.setJarByClass(PopularHotelFinder.class);

			job.setMapperClass(MapTask.class);
			job.setReducerClass(ReduceTask.class);

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(IntWritable.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);

			FileInputFormat.setInputPaths(job, inputPath);
			job.setInputFormatClass(TextInputFormat.class);
			FileOutputFormat.setOutputPath(job, outpuPath);
			job.setOutputFormatClass(TextOutputFormat.class);

			if (!job.waitForCompletion(true)) {
				throw new JobExecutionFailedException(
						"Job execution failed in class [" + PopularHotelFinder.class.getName() + "]");
			}

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

	public static PopularHotelFinder getInstance() {
		if (Objects.isNull(popularHotelFinder)) {
			synchronized (PopularHotelFinder.class) {
				if (Objects.isNull(popularHotelFinder)) {
					popularHotelFinder = new PopularHotelFinder();
				}
			}
		}
		return popularHotelFinder;
	}
}
