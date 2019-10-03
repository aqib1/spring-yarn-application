package com.example.yarnapplication.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author Aqib_Javed
 *
 */
public class ReduceTask extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text arg0, Iterable<IntWritable> arg1,
			Reducer<Text, IntWritable, Text, IntWritable>.Context arg2) throws IOException, InterruptedException {
		int counter = 0;
		for(IntWritable i : arg1) {
			counter += i.get();
		}
		arg2.write(arg0, new IntWritable(counter));
	}
}
