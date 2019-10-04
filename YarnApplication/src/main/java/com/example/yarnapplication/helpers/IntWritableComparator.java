package com.example.yarnapplication.helpers;

import java.nio.ByteBuffer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparator;

public class IntWritableComparator extends WritableComparator {
	public IntWritableComparator() {
		super(IntWritable.class);
	}

	@Override
	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
		Integer first = ByteBuffer.wrap(b1, s1, l1).getInt();
		Integer second = ByteBuffer.wrap(b2, s2, l2).getInt();
		return second.compareTo(first);
	}
	
	
}
