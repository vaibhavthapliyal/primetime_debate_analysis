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
	
	@Autowired
	private GenericDataExtractorImpl ndtvDataExtractor;
	
	@Value("${republic.playlist.id}")
	private String republicPlaylistId;
	
	@Value("${republic.channel.title}")
	private String republicChannelTitle;
	
	@Value("${ndtv.playlist.id}")
	private String ndtvPlaylistId;
	
	@Value("${ndtv.channel.title}")
	private String ndtvChannelTitle;
	
	@Value("${request.max.results}")
	private Long maxResults;
	
	@Value("${thread.count}")
	private int threadCount;
	
	
	@Override
	public void run() {
		configure();
		ExecutorService service = Executors.newFixedThreadPool(threadCount);
		service.submit(republicDataExtractor);
		service.submit(ndtvDataExtractor);
	}

	@Override
	public void configure() {
		//Configure republic data extractor
		republicDataExtractor.setMaxResults(maxResults);
		republicDataExtractor.setPlayListId(republicPlaylistId);
		republicDataExtractor.setChannelTitle(republicChannelTitle);
		//Configure NDTV data extractor
		ndtvDataExtractor.setMaxResults(maxResults);
		ndtvDataExtractor.setPlayListId(ndtvPlaylistId);
		ndtvDataExtractor.setChannelTitle(ndtvChannelTitle);
	}
}
