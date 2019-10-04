package com.example.yarnapplication.helpers;

import java.io.IOException;
import java.util.Objects;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.example.yarnapplication.exceptions.InternalMalfunctionException;

public class PrintHelper {

	private static PrintHelper PRINT_HELPER = null;

	private PrintHelper() {

	}

	@SuppressWarnings("deprecation")
	public void printResults(int limit, String path) {
		try {
			Path inputPath = new Path(path);
			Configuration configuration = new Configuration(true);
			try (FileSystem hdfs = inputPath.getFileSystem(configuration);
					FSDataInputStream fsDataInputStream = hdfs.open(inputPath);) {
				String line = null;
				for (int x = 0; x < limit && ((line = fsDataInputStream.readLine()) != null); x++) {
					System.out.println(line);
				}
			}
		} catch (Exception e) {
			throw new InternalMalfunctionException(e.getMessage(), e);
		}
	}

	// double check locking
	public static PrintHelper getInstance() {
		if (Objects.isNull(PRINT_HELPER)) {
			synchronized (PrintHelper.class) {
				if (Objects.isNull(PRINT_HELPER)) {
					PRINT_HELPER = new PrintHelper();
				}
			}
		}
		return PRINT_HELPER;
	}
}
