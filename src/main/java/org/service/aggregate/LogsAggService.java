package org.service.aggregate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.service.entity.AggLogEntity;
import org.service.entity.AggTableEntity;
import org.service.util.ApplicationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class LogsAggService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger log = LoggerFactory.getLogger(LogsAggService.class);
	
	public LogsAggService() {
	}
	
	public class AggEntityRowMapper implements RowMapper<AggLogEntity> {

		@Override
		public AggLogEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
			AggLogEntity res = new AggLogEntity(rs.getString("date"), rs.getString("country"), rs.getString("COUNT(*)"));
			return res;
		}
	}
	
	public List<AggLogEntity> selectAggData(String date, Integer rate) {
		String sql = "SELECT id, country, date, COUNT(*) FROM agg_log_data ";
		
		if (date != null && !date.isEmpty()) {
			sql = sql + "WHERE date=" + "'" + date + "'";
		}

		sql = sql + " GROUP BY country ORDER BY 4 DESC ";

		if (rate != null && rate != 0) {
			sql = sql + "LIMIT " + rate;
		}

		log.info("<selectAggData> Call SQL script : {}", sql);
		List<AggLogEntity> aggData = (List<AggLogEntity>) jdbcTemplate.query(sql, new AggEntityRowMapper());
		return aggData;
	}
	
	public void insertDataResponse(AggTableEntity data) {
		String sql = "INSERT INTO agg_log_data (country, date) VALUES(?,?)";
		jdbcTemplate.update(sql, data.getCountry(), data.getDate());
	}
	
	public HttpStatus postLoginDataBoot(Integer rate) {
		log.info("<postLoginDataBoot>");
		
		ResponseEntity<HttpStatus> result = null;

		if (rate == null) {
			rate = 1;
		}

		for (int i = 0; i < rate; i++) {
			result = createPostRequest();
		}
		return result.getStatusCode();
	}
	
	public ResponseEntity<HttpStatus> createPostRequest() {
		log.info("<createPostRequest>");
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();

		rootNode.put("ts", ApplicationUtil.getRandomTimestamp());
		rootNode.put("ip", ApplicationUtil.getRandomIp());
		
		final String uri = "http://127.0.0.1:8080/login";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(rootNode.toString(), headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
		return new ResponseEntity<HttpStatus>(result.getStatusCode());
	}

}
