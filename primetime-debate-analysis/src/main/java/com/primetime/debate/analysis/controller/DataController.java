package com.primetime.debate.analysis.controller;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.primetime.debate.analysis.response.BaseResponse;
import com.primetime.debate.analysis.service.IDataExtractorService;

@RestController
@RequestMapping("/api/data")
public class DataController {

	Logger log = LogManager.getLogger(DataController.class);
	
	@Autowired
	private IDataExtractorService extractorService;
	
	@GetMapping("/extract")
	public BaseResponse startBatchImport() throws IOException {
		log.info("Received Request to extract Data");
		extractorService.run();
		return new BaseResponse("Extractor Service Started", true, 200);
	}
}
