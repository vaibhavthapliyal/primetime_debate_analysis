package com.primetime.debate.analysis.entity;

import java.io.Serializable;

public class DebateMetadata implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4271043945616033922L;
	
	private String id;
	private String publishedAt;
	private String channelId;
	private String title;
	private String description;
	private String thumbnailUrlHighRes;
	private String thumbnailUrlStandard;
	private String channelTitle;
	private String playlistId;
	private String videoUrl;
	private String videoId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnailUrlHighRes() {
		return thumbnailUrlHighRes;
	}

	public void setThumbnailUrlHighRes(String thumbnailUrlHighRes) {
		this.thumbnailUrlHighRes = thumbnailUrlHighRes;
	}

	public String getThumbnailUrlStandard() {
		return thumbnailUrlStandard;
	}

	public void setThumbnailUrlStandard(String thumbnailUrlStandard) {
		this.thumbnailUrlStandard = thumbnailUrlStandard;
	}

	public String getChannelTitle() {
		return channelTitle;
	}

	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}

	public String getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(String playlistId) {
		this.playlistId = playlistId;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

}
