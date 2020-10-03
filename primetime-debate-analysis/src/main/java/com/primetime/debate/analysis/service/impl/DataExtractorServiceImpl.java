package com.primetime.debate.analysis.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.primetime.debate.analysis.extractor.impl.GenericDataExtractorImpl;
import com.primetime.debate.analysis.service.IDataExtractorService;

@Service
public class DataExtractorServiceImpl implements IDataExtractorService{

	@Autowired
	private GenericDataExtractorImpl republicDataExtractor;
	
	@Value("${republic.playlist.id}")
	private String playlistId;
	
	@Value("${request.max.results}")
	private Long maxResults;
	
	
	@Value("${thread.count}")
	private int threadCount;
	
	
	@Override
	public void run() {
		configure();
		ExecutorService service = Executors.newFixedThreadPool(threadCount);
		service.submit(republicDataExtractor);
	}

	@Override
	public void configure() {
		republicDataExtractor.setMaxResults(maxResults);
		republicDataExtractor.setPlayListId(playlistId);
	}
}
