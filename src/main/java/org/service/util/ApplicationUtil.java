package org.service.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maxmind.db.CHMCache;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.DatabaseReader.Builder;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;

public class ApplicationUtil {
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationUtil.class);
	
	public static String getRandomIp() {
    	Random r = new Random();
    	String randomIP =  r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
		return randomIP;
	}

	public static Long getRandomTimestamp() {
    	Random r = new Random();
    	long actualTimestamp = System.currentTimeMillis() / 1000;
    	Long randomTs =  (long) r.nextInt((int) actualTimestamp);
		return randomTs;
	}
	
	public static Integer getAppArguments(String arg) {
		int key = arg.indexOf("rate=");
		Integer value = Integer.valueOf(arg.substring(key+5));
		return value;
	}
	
	public static String timestampToDate(Long data) {
		Long ts = data * 1000L;
		Timestamp stamp = new Timestamp(ts);
		Date date = new Date(stamp.getTime());
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		log.info("<timestampToDate> Converted Timestamp to DATE : {}", f.format(date));
		return f.format(date);
	}
	
	public static String getCountryCode(String ip) {
		try {
			File database = getFile();
			Builder b = new Builder(database);
			b.withCache(new CHMCache(256));
			DatabaseReader reader;
			
			reader = b.build();
			InetAddress ipAddress = InetAddress.getByName(ip);
			CountryResponse country = reader.country(ipAddress);
			log.info("<getCountryCode> Country : {}", country.getCountry().getIsoCode());
			return country.getCountry().getIsoCode();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GeoIp2Exception e) {
			log.error("GeoIp2Exception {}", e.getMessage());
		}
		
		return ip;
	}
	
	public static File getFile() throws IOException {
		ApplicationUtil app = new ApplicationUtil();
		String fileName = "geoip2/GeoLite2-Country.mmdb";
		InputStream is = app.getFileFromResourceAsStream(fileName);
		File file = new File("GeoLite2-Country.mmdb");
		FileUtils.copyInputStreamToFile(is, file);
		return file;
	}

	private InputStream getFileFromResourceAsStream(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);

		if (inputStream == null) {
			throw new IllegalArgumentException("file not found! " + fileName);
		} else {
			return inputStream;
		}
	}
	
}
