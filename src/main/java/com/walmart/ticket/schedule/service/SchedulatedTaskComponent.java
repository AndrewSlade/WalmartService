package com.walmart.ticket.schedule.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.walmart.ticket.service.TicketService;
import com.walmart.ticketing.constants.TicketingConstants;

@Component
public class SchedulatedTaskComponent {

	@Autowired
	private TicketService ticketService;

	private static final String IDLE_SEAT_HOLD_TIMEOUT = "" + TicketingConstants.EXPIRATION_TIME_IN_MILLISECONDS;

	@Scheduled(fixedRateString = IDLE_SEAT_HOLD_TIMEOUT)
	public void clearExpiredHolds() throws Exception {

		Date currentDate = new Date();

		try {

			ticketService.releaseLatestHold(currentDate);

		} catch (Exception e) {
			throw new Exception("Unable to clear old seat holds. Exception=" + e);
		}
	}

}