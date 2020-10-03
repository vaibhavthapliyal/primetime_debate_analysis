package com.primetime.debate.analysis.constants;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class Constants {
	public static final String YOUTUBE_DATA_DIR = ".store/youtube";
	public static final String CLIENT_SECRETS = "/client_secret_credential.json";
	public static final Collection<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/youtube.readonly");

	public static final String APPLICATION_NAME = "Primetime Debate Data Extractor";
	public static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	public static final File DATA_STORE_DIR =
	            new File(System.getProperty("user.home"), YOUTUBE_DATA_DIR);
	
	public static final String YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v=";
}
