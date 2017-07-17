package com.walmart.ticket.service;

import java.util.Date;

import com.walmart.seat.model.SeatHold;
import com.walmart.ticket.exception.service.SeatHoldCreationException;
import com.walmart.ticket.exception.service.SeatHoldReservationReleaseException;
import com.walmart.ticket.exception.service.SeatReservationCreationException;

/**
 * 
 * @author andrew.slade
 *
 */
public interface TicketService {

	/**
	 * The number of seats in the venue that are neither held nor reserved
	 *
	 * @return the number of tickets available in the venue
	 */
	int numSeatsAvailable();

	/**
	 * Find and hold the best available seats for a customer
	 *
	 * @param numSeats
	 *            the number of seats to find and hold
	 * @param customerEmail
	 *            unique identifier for the customer
	 * @return a SeatHold object identifying the specific seats and related
	 *         information
	 * @throws SeatHoldCreationException
	 */
	SeatHold findAndHoldSeats(int numSeats, String customerEmail) throws SeatHoldCreationException;

	/**
	 * Commit seats held for a specific customer
	 *
	 * @param seatHoldId
	 *            the seat hold identifier
	 * @param customerEmail
	 *            the email address of the customer to which the seat hold is
	 *            assigned
	 * @return a reservation confirmation code
	 * @throws SeatHoldCreationException
	 * @throws SeatReservationCreationException
	 */
	public String reserveSeats(int seatHoldId, String customerEmail) throws SeatReservationCreationException;

	/**
	 * This is the method which will be called and query through the held seat
	 * list and expire the old holds. The parameter is the date which the hold
	 * seat's creation date minus hte offset must be before or the hold will
	 * expire
	 * 
	 * @param date
	 * @throws SeatHoldReservationReleaseException
	 */
	public void releaseLatestHold(Date date) throws SeatHoldReservationReleaseException;

	/**
	 * Clears the hold and resets the entries in the array
	 * 
	 * @param seatHoldId
	 * @throws SeatHoldReservationReleaseException
	 */
	public void releaseHold(int seatHoldId) throws SeatHoldReservationReleaseException;
}