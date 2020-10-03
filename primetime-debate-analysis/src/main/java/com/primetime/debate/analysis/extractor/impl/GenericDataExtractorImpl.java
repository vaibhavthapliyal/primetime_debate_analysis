package com.primetime.debate.analysis.extractor.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
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
	
	@Value("${elasticsearch.index.name}")
	private String indexName;
	
	@Autowired
	@Qualifier("elasticClient")
	private RestHighLevelClient client;
	
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
		
		close();
	}

	@Override
	public void save(DebateMetadata metadata) {
		try {
			log.info("Saving Data to Elasticsearch");
			IndexRequest request = new IndexRequest(indexName).id(metadata.getVideoId())
					.source(mapper.convertValue(metadata, new TypeReference<Map<String, Object>>() {
					}));

			client.index(request, RequestOptions.DEFAULT);
			log.info("Data Saved: " + mapper.writeValueAsString(metadata));
		} catch (Exception e) {
			log.error("Error occured in saving data " + metadata.getId(), e);
		}
	}
	
	


	@Override
	public void close() {
		try {
			client.close();
		} catch (Exception e) {
			log.error("Error in closing elasticsearch Client", e);
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
