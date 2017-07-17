package com.walmart.ticket.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import com.walmart.seat.model.SeatHold;
import com.walmart.seat.model.seat.SeatAssignment;

/**
 * 
 * @author andrew.slade
 *
 */
public class TicketServiceITTest {

	private TicketService ticketService = new TicketServiceImpl();;

	@Test
	public void testNumSeatsAvailable() throws Exception {
		int numSeats = ticketService.numSeatsAvailable();

		assertEquals(100, numSeats);

		int numOfSeatsDesired = 5;
		String customerEmail = "customer@email.com";

		ticketService.findAndHoldSeats(numOfSeatsDesired, customerEmail);

		numSeats = ticketService.numSeatsAvailable();

		assertEquals(95, numSeats);

	}

	@Test
	public void testFindAndHoldSeats() throws Exception {
		int numOfSeatsDesired = 5;
		String customerEmail = "customer@email.com";

		SeatHold seatHold = ticketService.findAndHoldSeats(numOfSeatsDesired, customerEmail);

		assertNotNull(seatHold);

		// First 5 seats of first row should be reserved
		assertEquals(new SeatAssignment(0, 1), seatHold.getHeldSeatList().get(0));
		assertEquals(new SeatAssignment(0, 2), seatHold.getHeldSeatList().get(1));
		assertEquals(new SeatAssignment(0, 3), seatHold.getHeldSeatList().get(2));
		assertEquals(new SeatAssignment(0, 4), seatHold.getHeldSeatList().get(3));
		assertEquals(new SeatAssignment(0, 5), seatHold.getHeldSeatList().get(4));

		seatHold = ticketService.findAndHoldSeats(6, customerEmail);

		assertNotNull(seatHold);

		// First 6 seats of second row should be reserved
		assertEquals(new SeatAssignment(1, 1), seatHold.getHeldSeatList().get(0));
		assertEquals(new SeatAssignment(1, 2), seatHold.getHeldSeatList().get(1));
		assertEquals(new SeatAssignment(1, 3), seatHold.getHeldSeatList().get(2));
		assertEquals(new SeatAssignment(1, 4), seatHold.getHeldSeatList().get(3));
		assertEquals(new SeatAssignment(1, 5), seatHold.getHeldSeatList().get(4));
		assertEquals(new SeatAssignment(1, 6), seatHold.getHeldSeatList().get(5));

		// More seats than are available in a row
		seatHold = ticketService.findAndHoldSeats(11, customerEmail);

		assertNull(seatHold);

	}

	@Test
	public void testFindAndHoldSeats2() throws Exception {
		int numOfSeatsDesired = 5;
		String customerEmail = "customer@email.com";

		SeatHold seatHold1 = ticketService.findAndHoldSeats(numOfSeatsDesired, customerEmail);

		assertNotNull(seatHold1);

		assertEquals(new SeatAssignment(0, 1), seatHold1.getHeldSeatList().get(0));
		assertEquals(new SeatAssignment(0, 2), seatHold1.getHeldSeatList().get(1));
		assertEquals(new SeatAssignment(0, 3), seatHold1.getHeldSeatList().get(2));
		assertEquals(new SeatAssignment(0, 4), seatHold1.getHeldSeatList().get(3));
		assertEquals(new SeatAssignment(0, 5), seatHold1.getHeldSeatList().get(4));

		numOfSeatsDesired = 2;

		SeatHold seatHold2 = ticketService.findAndHoldSeats(numOfSeatsDesired, customerEmail);

		assertNotNull(seatHold2);

		assertEquals(new SeatAssignment(0, 6), seatHold2.getHeldSeatList().get(0));
		assertEquals(new SeatAssignment(0, 7), seatHold2.getHeldSeatList().get(1));

		numOfSeatsDesired = 2;

		SeatHold seatHold3 = ticketService.findAndHoldSeats(numOfSeatsDesired, customerEmail);

		assertNotNull(seatHold3);

		assertEquals(new SeatAssignment(0, 8), seatHold3.getHeldSeatList().get(0));
		assertEquals(new SeatAssignment(0, 9), seatHold3.getHeldSeatList().get(1));

		SeatHold seatHold4 = ticketService.findAndHoldSeats(numOfSeatsDesired, customerEmail);

		assertNotNull(seatHold4);

		assertEquals(new SeatAssignment(1, 1), seatHold4.getHeldSeatList().get(0));
		assertEquals(new SeatAssignment(1, 2), seatHold4.getHeldSeatList().get(1));

	}

	@Test
	public void testReserveSeats() throws Exception {

		int numOfSeatsDesired = 5;
		String customerEmail = "customer@email.com";

		SeatHold seatHold = ticketService.findAndHoldSeats(numOfSeatsDesired, customerEmail);

		assertNotNull(seatHold);

		String reservationCode = ticketService.reserveSeats(seatHold.getId(), customerEmail);

		assertNotNull(reservationCode);

		System.out.println(reservationCode);

	}

	@Test
	public void releaseHold() throws Exception {
		int seatId = 0;

		// Intentionally trying to release a hold which does not exist, an
		// exception should be thrown
		try {
			ticketService.releaseHold(seatId);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}

		int numOfSeatsDesired = 5;
		String customerEmail = "customer@email.com";

		SeatHold seatHold1 = ticketService.findAndHoldSeats(numOfSeatsDesired, customerEmail);

		assertNotNull(seatHold1);

		numOfSeatsDesired = 2;

		SeatHold seatHold2 = ticketService.findAndHoldSeats(numOfSeatsDesired, customerEmail);

		assertNotNull(seatHold2);

		numOfSeatsDesired = 2;

		SeatHold seatHold3 = ticketService.findAndHoldSeats(numOfSeatsDesired, customerEmail);

		assertNotNull(seatHold3);

		ticketService.releaseHold(seatHold2.getId());

		numOfSeatsDesired = 3;

		SeatHold seatHold4 = ticketService.findAndHoldSeats(numOfSeatsDesired, customerEmail);

		assertNotNull(seatHold4);

		assertEquals(1, seatHold4.getHeldSeatList().get(0).getRowId());

		numOfSeatsDesired = 2;

		SeatHold seatHold5 = ticketService.findAndHoldSeats(numOfSeatsDesired, customerEmail);

		assertNotNull(seatHold5);

		assertEquals(0, seatHold5.getHeldSeatList().get(0).getRowId());

	}

	@Test
	public void testReleaseLatestHold() throws Exception {

		int numOfSeatsDesired = 5;
		String customerEmail = "customer@email.com";

		SeatHold seatHold1 = ticketService.findAndHoldSeats(numOfSeatsDesired, customerEmail);

		assertNotNull(seatHold1);

		// Set the creation date to be unix beginning, approximately Jan 1 1970
		seatHold1.setCreationDate(new Date(0));

		ticketService.releaseLatestHold(new Date());

		SeatHold seatHold2 = ticketService.findAndHoldSeats(6, customerEmail);

		assertNotNull(seatHold2);

		// The first hold expired so this new hold should overwrite the seat
		// assignments and take place in the first row
		assertEquals(0, seatHold2.getHeldSeatList().get(0).getRowId());

	}
}
