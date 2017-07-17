package com.walmart.ticket.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.walmart.seat.model.SeatHold;
import com.walmart.seat.model.seat.SeatAssignment;
import com.walmart.ticket.exception.service.SeatHoldCreationException;
import com.walmart.ticket.exception.service.SeatHoldReservationReleaseException;
import com.walmart.ticket.exception.service.SeatReservationCreationException;

/**
 * 
 * @author andrew.slade
 *
 */
@Service
public class TicketServiceImpl implements TicketService {

	private int totalSeats = 100;
	private int heldSeatCount = 0;
	private int reservedSeatCount = 0;

	private static final int totalRows = 10;
	private static final int totalColumns = 10;

	// 2 dimensional array storing seats for a 10 x 10 space
	// The first entry indicates the total number of sequential seats
	private int[][] seatsArray = new int[totalRows][totalColumns + 1];

	private List<SeatHold> heldSeatList = new ArrayList<SeatHold>();

	private Map<String, SeatHold> reservedSeatMap = new HashMap<String, SeatHold>();

	public TicketServiceImpl() {
		super();

		// Initialize values for the total number of sequential open seats
		for (int i = 0; i < seatsArray.length; i++)
			seatsArray[i][0] = seatsArray[0].length - 1;

	}

	@Override
	public int numSeatsAvailable() {
		return totalSeats - heldSeatCount - reservedSeatCount;
	}

	@Override
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) throws SeatHoldCreationException {
		SeatHold seatHold = null;

		for (int i = 0; i < seatsArray.length; i++)
			// Check if there is enough sequential available seats in a row
			if (numSeats <= seatsArray[i][0]) {
				{
					for (int j = 1; j < seatsArray[i].length; j++)

						if (checkBlock(numSeats, seatsArray[i], j)) {
							seatHold = createBlock(numSeats, i, j, customerEmail);
							heldSeatList.add(seatHold);
							return seatHold;

						}
				}
			}
		return null;
	}

	@Override
	public String reserveSeats(int seatHoldId, String customerEmail) throws SeatReservationCreationException {

		SeatHold seatHold = null;

		for (SeatHold retrievedSeatHold : heldSeatList)
			if (seatHoldId == retrievedSeatHold.getId())
				seatHold = retrievedSeatHold;
		if (seatHold == null)
			throw new SeatReservationCreationException(
					"Unable to reserve seats. seatHoldId=" + seatHoldId + " does not map to a held seat");

		int seatCount = seatHold.getHeldSeatList().size();
		heldSeatCount -= seatCount;
		reservedSeatCount += seatCount;

		String reservationCode = UUID.randomUUID().toString();

		// Ensure reservation code is distinct. If not, generate new one
		while (reservedSeatMap.containsKey(reservationCode))
			reservationCode = UUID.randomUUID().toString();

		reservedSeatMap.put(reservationCode, seatHold);

		return reservationCode;
	}

	/**
	 * Helper method to determine if a segment of seats are sequentially
	 * available
	 * 
	 * @param numSeats
	 * @param rowOfSeats
	 * @param position
	 * @return
	 */
	private boolean checkBlock(int numSeats, int[] rowOfSeats, int position) {
		for (int i = position; i < rowOfSeats.length && i - position < numSeats; i++)
			if (rowOfSeats[i] != SeatHold.OPEN_SEAT)
				return false;

		return true;

	}

	/**
	 * Helper method to create a seat assignment hold
	 * 
	 * Generates a SeatHold object and adds it to the held list, then returns
	 * that object
	 * 
	 * @param numSeats
	 * @param rowOfSeats
	 * @param position
	 * @return
	 * @throws SeatHoldCreationException
	 */
	private SeatHold createBlock(int numSeats, int rowOfSeats, int position, String emailContact)
			throws SeatHoldCreationException {

		List<SeatAssignment> seatList = new ArrayList<SeatAssignment>();
		int count = 0;

		for (int i = position; i < seatsArray[rowOfSeats].length && i - position < numSeats && count < numSeats; i++)
			if (seatsArray[rowOfSeats][i] == SeatHold.OPEN_SEAT) {
				seatsArray[rowOfSeats][i] = SeatHold.HELD_SEAT;
				seatList.add(new SeatAssignment(rowOfSeats, i));
				count++;

			} else
				// This will only get called if a seat hold was not able to be
				// created.
				// Based on the requirements for this method a seat hold should
				// always
				// be created if this method is called
				throw new SeatHoldCreationException("Error in TicketServiceImpl. Cannot fit numSeats=" + numSeats
						+ " in row=" + rowOfSeats + " starting at seat position=" + (position - 1));

		SeatHold seatHold = null;
		// int availableSeats = 0;
		// int tempCount = 0;
		if (count == numSeats) {

			setMaxAvailableSequentialRowLength(rowOfSeats);

			// Decrease amount of available seats by setting the hold
			heldSeatCount += count;

			seatHold = new SeatHold(seatList, emailContact);

			return seatHold;
		}

		// This will only get called if a seat hold was not able to be created.
		// Based on the requirements for this method a seat hold should always
		// be created if this method is called
		throw new SeatHoldCreationException("Error in TicketServiceImpl. Cannot fit numSeats=" + numSeats + " in row="
				+ rowOfSeats + " starting at seat position=" + (position - 1));

	}

	private void setMaxAvailableSequentialRowLength(int rowOfSeats) {
		int availableSeats = 0;
		int tempCount = 0;

		// Need to determine next maximum amount of sequential seats
		// available
		for (int i = 1; i < seatsArray[rowOfSeats].length; i++) {
			if (seatsArray[rowOfSeats][i] == SeatHold.OPEN_SEAT)
				tempCount++;
			else {
				if (tempCount > availableSeats)
					availableSeats = tempCount;
				tempCount = 0;
			}

		}
		if (tempCount > availableSeats)
			availableSeats = tempCount;

		// Set the amount of available sequential seats for the row
		seatsArray[rowOfSeats][0] = availableSeats;
	}

	@Override
	public void releaseHold(int seatHoldId) throws SeatHoldReservationReleaseException {

		SeatHold seatHold = null;

		for (SeatHold retrievedSeatHold : heldSeatList)
			if (seatHoldId == retrievedSeatHold.getId())
				seatHold = retrievedSeatHold;
		if (seatHold == null)
			throw new SeatHoldReservationReleaseException(
					"Unable to release hold. seatHoldId=" + seatHoldId + " does not map to a held seat");

		heldSeatList.remove(seatHold);

		int rowOfSeats = seatHold.getHeldSeatList().get(0).getRowId();

		for (SeatAssignment seatAssignment : seatHold.getHeldSeatList())
			seatsArray[seatAssignment.getRowId()][seatAssignment.getColumnId()] = SeatHold.OPEN_SEAT;

		setMaxAvailableSequentialRowLength(rowOfSeats);

	}

	@Override
	public void releaseLatestHold(Date date) {
		// Increment the date parameter by the expirationTime constant
		date.setTime(date.getTime() + EXPIRATION_TIME_IN_MILLISECONDS);

		System.out.println("date=" + date);

		List<SeatHold> holdsToRemoveList = new ArrayList<SeatHold>();

		for (SeatHold heldSeat : heldSeatList) {
			System.out.println(heldSeat);
			if (heldSeat.getCreationDate().before(date)) {
				holdsToRemoveList.add(heldSeat);
			}
			// Holds are added in sequential order, once a hold is no longer
			// expired no future holds will be expired
			else {
				break;
			}
		}
		// Remove the held seats from the held seat list
		for (SeatHold heldSeat : holdsToRemoveList) {
			for (SeatAssignment seatAssignment : heldSeat.getHeldSeatList()) {
				seatsArray[seatAssignment.getRowId()][seatAssignment.getColumnId()] = SeatHold.OPEN_SEAT;
			}
			setMaxAvailableSequentialRowLength(heldSeat.getHeldSeatList().get(0).getRowId());
			heldSeatList.remove(heldSeat);
		}

	}

}