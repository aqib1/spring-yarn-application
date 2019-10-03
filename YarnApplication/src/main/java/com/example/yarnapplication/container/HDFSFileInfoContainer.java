package com.example.yarnapplication.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.yarn.annotation.OnContainerStart;
import org.springframework.yarn.annotation.YarnComponent;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.springframework.data.hadoop.fs.FsShell;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@YarnComponent
public class ContainerApplication {
	private static final Log log = LogFactory.getLog(ContainerApplication.class);
	@Autowired
	private Configuration conf;
	
	@OnContainerStart
	public void listRootHdfsFiles() {
		FsShell fsShell = new FsShell(conf);
		for(FileStatus fileStatus : fsShell.ls(true, "")) {
			log.info("File -> "+ fileStatus);
			log.info("File Path -> "+ fileStatus.getPath());
			System.out.println("File -> "+fileStatus);
			System.out.println("File Path -> "+fileStatus.getPath());
		}
	}
}
