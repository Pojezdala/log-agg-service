package org.service.util;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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

	public static Long getRandomTs() {
    	Random r = new Random();
    	Long randomTs =  (long) r.nextInt(1626731272);
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
		File database = new File("./src/main/resources/GeoLite2-Country/GeoLite2-Country.mmdb");

		Builder b = new Builder(database);
		b.withCache(new CHMCache(256));
		DatabaseReader reader;
		try {
			reader = b.build();
			InetAddress ipAddress = InetAddress.getByName(ip);
			CountryResponse country = reader.country(ipAddress);
			log.info("<getCountryCode> Country : {}", country.getCountry().getIsoCode());
			return country.getCountry().getIsoCode();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GeoIp2Exception e) {
			e.printStackTrace();
		}
		return ip;
	}
	
}
