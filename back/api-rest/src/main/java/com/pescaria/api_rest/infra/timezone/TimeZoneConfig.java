package com.pescaria.api_rest.infra.timezone;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeZoneConfig {

	// Setting the date and time for Brazilian Timezone
	@Bean
	public ZonedDateTime zonedDateTime() {
		Instant NOW_UTC = Instant.now();
		ZoneId AMERICA_SP = ZoneId.of("America/Sao_Paulo");
		return ZonedDateTime.ofInstant(NOW_UTC, AMERICA_SP);
	}
	
	// Return current date
	@Autowired
	@Bean
	public Date date(ZonedDateTime zonedDateTime) {	
		return Date.valueOf(zonedDateTime.toLocalDate());
	}
	
	// Return current time
	@Autowired
	@Bean
	public Time time(ZonedDateTime zonedDateTime) {	
		return Time.valueOf(zonedDateTime.toLocalTime());
	}
	
	@Autowired
	@Bean
	public LocalDateTime localDateTime(ZonedDateTime zonedDateTime) {
		return LocalDateTime.now(zonedDateTime.getZone());
	}
}
