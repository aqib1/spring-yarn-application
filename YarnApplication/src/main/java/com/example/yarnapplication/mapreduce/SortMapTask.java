package com.example.yarnapplication.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.example.yarnapplication.helpers.Consts;

public class SortMapTask extends Mapper<LongWritable, Text, IntWritable, Text>{
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		
		if(!Consts.isNullOrEmptyString(line) && !line.isBlank()) {
			String [] splits = line.split("\t");
			String keyVal = splits[0];
			String valueVal = splits[1];
			Text text = new Text();
			text.set(keyVal);
			int valueParse = Integer.parseInt(valueVal);
			context.write(new IntWritable(valueParse), text);
		}
	}

}
