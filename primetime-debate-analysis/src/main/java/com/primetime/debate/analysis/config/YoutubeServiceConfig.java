package com.primetime.debate.analysis.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.primetime.debate.analysis.constants.Constants;


@Configuration
public class YoutubeServiceConfig {

	private Logger log = LogManager.getLogger(YoutubeServiceConfig.class);
	
	private static FileDataStoreFactory dataStoreFactory;

	@Bean("youtube")
    public YouTube getYoutubeService(){
		
		YouTube youtubeService = null;
		
		try {
			final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			dataStoreFactory = new FileDataStoreFactory(Constants.DATA_STORE_DIR);
			Credential credential = authorize(httpTransport);
			youtubeService =  new YouTube.Builder(httpTransport, Constants.JSON_FACTORY, credential).setApplicationName(Constants.APPLICATION_NAME)
					.build();
		} catch (GeneralSecurityException | IOException e) {
			log.error("Error initialising Youtube Service", e);
		}
		
		log.info("Youtube Service Initialised successfully");
		return youtubeService;
	}

	private Credential authorize(NetHttpTransport httpTransport) throws IOException {
		// Load client secrets.
				InputStream in = YoutubeServiceConfig.class.getResourceAsStream(Constants.CLIENT_SECRETS);
				GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(Constants.JSON_FACTORY, new InputStreamReader(in));
				// Build flow and trigger user authorization request.
				GoogleAuthorizationCodeFlow flow =
		                new GoogleAuthorizationCodeFlow.Builder(httpTransport, Constants.JSON_FACTORY, clientSecrets, Constants.SCOPES)
		                        .setDataStoreFactory(dataStoreFactory)
		                        .build();
				Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
				return credential;
	}
}
