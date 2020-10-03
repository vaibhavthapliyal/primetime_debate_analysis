package com.primetime.debate.analysis.extractor;

import com.primetime.debate.analysis.entity.DebateMetadata;

public interface GenericDataExtractor extends Runnable {

	default public void run() {
		throw new RuntimeException("Not Implemented yet");
	}

	default public void save(DebateMetadata metadata) {
		throw new RuntimeException("Not Implemented yet");
	}

}
