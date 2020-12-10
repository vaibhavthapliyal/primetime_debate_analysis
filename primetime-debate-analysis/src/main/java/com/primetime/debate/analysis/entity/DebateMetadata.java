package com.primetime.debate.analysis.entity;

import lombok.Data;

import java.io.Serializable;
@Data
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

}
