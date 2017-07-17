package com.walmart.ticket.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.walmart.ticket.service.TicketService;

@Component
public class SchedulatedTaskComponentITTest {

	@Autowired
	private TicketService ticketService;

	private static final String idleReviewTimeoutMilliseconds = "" + TicketService.EXPIRATION_TIME_IN_MILLISECONDS;

	@Scheduled(fixedRateString = idleReviewTimeoutMilliseconds)
	public void clearLocksForOldSessions() throws Exception {

		Date currentDate = new Date();

		try {

			ticketService.releaseLatestHold(currentDate);

		} catch (Exception e) {
			throw new Exception("Unable to clear old seat holds. Exception=" + e);
		}
	}

}