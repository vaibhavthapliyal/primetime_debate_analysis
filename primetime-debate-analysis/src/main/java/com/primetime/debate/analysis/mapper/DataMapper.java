package com.primetime.debate.analysis.mapper;

import com.google.api.services.youtube.model.PlaylistItem;
import com.primetime.debate.analysis.constants.Constants;
import com.primetime.debate.analysis.entity.DebateMetadata;

public class DataMapper {
	public static DebateMetadata getDebateMetadataFromYoutubePlaylistItem(PlaylistItem item) {
		DebateMetadata metadata = new DebateMetadata();

		metadata.setChannelId(item.getSnippet().getChannelId());

		metadata.setChannelTitle(item.getSnippet().getChannelTitle());

		metadata.setDescription(item.getSnippet().getDescription());

		metadata.setId(item.getId());

		metadata.setPlaylistId(item.getSnippet().getPlaylistId());

		metadata.setPublishedAt(item.getSnippet().getPublishedAt().toString());

		if(item.getSnippet().getThumbnails().getHigh() != null) {
			metadata.setThumbnailUrlHighRes(item.getSnippet().getThumbnails().getHigh().getUrl());
		}

		if(item.getSnippet().getThumbnails().getStandard() != null) {
			metadata.setThumbnailUrlStandard(item.getSnippet().getThumbnails().getStandard().getUrl());
		}

		metadata.setTitle(item.getSnippet().getTitle());

		metadata.setVideoId(item.getSnippet().getResourceId().getVideoId());

		metadata.setVideoUrl(Constants.YOUTUBE_BASE_URL + item.getSnippet().getResourceId().getVideoId());

		return metadata;
	}
}
