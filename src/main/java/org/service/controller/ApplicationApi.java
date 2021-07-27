package org.service.controller;

import java.util.List;

import org.service.entity.AggLogEntity;
import org.service.entity.AggTableEntity;
import org.service.entity.LoginEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ApplicationApi {
	
	@ApiOperation(value = "", nickname = "getAggData", notes = "Get aggregate data", response = AggTableEntity.class, tags = {})

	@ApiResponses(value = {

			@ApiResponse(code = 200, message = "The operation was successful", response = AggTableEntity.class),

			@ApiResponse(code = 400, message = "Wrong input data format", response = Error.class) })

	@RequestMapping(value = "/aggData", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<AggLogEntity>> getAggData(Integer rate, String date);


	@ApiOperation(value = "", nickname = "postLogin", notes = "Post login Log", response = LoginEntity.class, tags = {})

	@ApiResponses(value = {

			@ApiResponse(code = 200, message = "The operation was successful", response = LoginEntity.class),

			@ApiResponse(code = 400, message = "Wrong input data format", response = Error.class) })

	@RequestMapping(value = "/login", produces = { "application/json" }, method = RequestMethod.POST)
	ResponseEntity<LoginEntity> postLogin(LoginEntity data);
	
	@ApiOperation(value = "", nickname = "postLoginData", notes = "Post login Log", response = LoginEntity.class, tags = {})

	@ApiResponses(value = {

			@ApiResponse(code = 200, message = "The operation was successful", response = HttpStatus.class),

			@ApiResponse(code = 400, message = "Wrong input data format", response = Error.class) })

	@RequestMapping(value = "/execute", produces = { "application/json" }, method = RequestMethod.POST)
	ResponseEntity<HttpStatus> postLoginData(Integer rate);

}
