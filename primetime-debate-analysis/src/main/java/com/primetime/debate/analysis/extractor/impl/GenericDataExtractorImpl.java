package com.primetime.debate.analysis.extractor.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.primetime.debate.analysis.entity.DebateMetadata;
import com.primetime.debate.analysis.extractor.GenericDataExtractor;
import com.primetime.debate.analysis.mapper.DataMapper;

@Service
@Scope("prototype")
public class GenericDataExtractorImpl implements GenericDataExtractor{

	@Autowired
	@Qualifier("youtube")
	private YouTube youtubeService;
	
	private String playListId;
	
	private Long maxResults = 0L;
	
	private Logger log = LogManager.getLogger(GenericDataExtractorImpl.class);
	
	private String nextPageToken;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void run() {
		try {
			YouTube.PlaylistItems.List request;
			PlaylistItemListResponse response;
			do {
				request = youtubeService.playlistItems().list("snippet,contentDetails").setMaxResults(maxResults)
						.setPlaylistId(playListId).setPageToken(nextPageToken);
				response = request.execute();
				
				for(PlaylistItem item :response.getItems()) {
					DebateMetadata metadata = DataMapper.getDebateMetadataFromYoutubePlaylistItem(item);
					save(metadata);
				}
				nextPageToken = response.getNextPageToken();
			} while (response.getNextPageToken() != null);
		} catch (IOException e) {
			log.error("Error Occured in extracting Data for Playlist ID: " + playListId, e);
		}
		
	}

	@Override
	public void save(DebateMetadata metadata) {
		try {
			log.info("Data Saved: " + mapper.writeValueAsString(metadata));
		} catch (Exception e) {
			log.error("Error occured in saving data " + metadata.getId(), e);
		}
	}


	public String getPlayListId() {
		return playListId;
	}


	public void setPlayListId(String playListId) {
		this.playListId = playListId;
	}


	public Long getMaxResults() {
		return maxResults;
	}


	public void setMaxResults(Long maxResults) {
		this.maxResults = maxResults;
	}
	
}
