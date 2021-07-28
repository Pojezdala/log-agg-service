package org.service.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.service.entity.AggLogEntity;
import org.service.entity.AggTableEntity;
import org.service.entity.LoginEntity;
import org.service.aggregate.LogsAggService;
import org.service.util.ApplicationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class ApplicationController implements ApplicationApi {
	
    @Autowired
    private LogsAggService logsAggService;

	private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public ApplicationController(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public ResponseEntity<List<AggLogEntity>> getAggData(@RequestHeader(name = "rate", required = false) Integer rate,
			@RequestHeader(name = "date", required = false) String date) {
		
		log.info("Call /aggData rate: {}, date: {}", rate, date);
		
		if (request.getHeader("rate") != null && request.getHeader("rate") != "") {
			rate = Integer.valueOf(request.getHeader("rate"));
		}

		if (request.getHeader("date") != null && request.getHeader("date") != "") {
			date = request.getHeader("date");
		}

		List<AggLogEntity> aggData = new ArrayList<AggLogEntity>();
		logsAggService.selectAggData(date, rate).forEach(aggData::add);
		aggData = setOrder(aggData);

		return new ResponseEntity<>(aggData, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<LoginEntity> postLogin(@RequestBody LoginEntity data) {
		log.info("<postLogin> Call /login " + "timestamp: {}, ip: {}", data.getTs(), data.getIp());

		AggTableEntity aggEntity = new AggTableEntity();
		aggEntity.setDate(ApplicationUtil.timestampToDate(data.getTs()));
		aggEntity.setCountry(ApplicationUtil.getCountryCode(data.getIp()));

		logsAggService.insertDataResponse(aggEntity);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<HttpStatus> postLoginData(@RequestHeader(name = "rate", required = false) Integer rate) {
		log.info("<postLoginData> Call /execute headers, rate: {}", rate);
		
		ResponseEntity<HttpStatus> result = null;
		List<HttpStatus> httpStatuses = new ArrayList<HttpStatus>();
		
		if (rate == null || rate == 0) {
			rate = 1;
		}

		for (int i = 0; i < rate; i++) {
			result = logsAggService.createPostRequest();
			httpStatuses.add(result.getStatusCode());
		}

		for (HttpStatus httpStatus : httpStatuses) {
			if (!httpStatus.equals(HttpStatus.OK)) {
				return new ResponseEntity<>(httpStatus);
			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private List<AggLogEntity> setOrder(List<AggLogEntity> aggData) {
		int i = 1;
		for (AggLogEntity aggEnt : aggData) {
			aggEnt.setOrder(i);
			i++;
		}
		return aggData;
	}
	
}