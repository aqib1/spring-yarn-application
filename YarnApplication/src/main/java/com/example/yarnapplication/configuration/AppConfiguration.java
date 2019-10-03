package com.example.yarnapplication.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.config.annotation.EnableHadoop;
import org.springframework.data.hadoop.config.annotation.SpringHadoopConfigurerAdapter;
import org.springframework.data.hadoop.config.annotation.builders.HadoopConfigConfigurer;

@Configuration
@EnableHadoop
public class AppConfiguration extends SpringHadoopConfigurerAdapter {

	@Override
	public void configure(HadoopConfigConfigurer config) throws Exception {
		config.fileSystemUri("hdfs://10.22.24.127:9000");
		config.resourceManagerAddress("10.22.24.127");
	}
}
