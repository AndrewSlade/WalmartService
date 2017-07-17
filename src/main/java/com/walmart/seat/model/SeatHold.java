package com.walmart.seat.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import com.walmart.seat.model.seat.SeatAssignment;

/**
 * 
 * @author andrew.slade
 *
 */
@Entity
public class SeatHold {

	// This would normally be handled by a database auto increment or other
	// persistence layer
	private static int SEAT_ID = 1;

	public static final int OPEN_SEAT = 0;
	public static final int HELD_SEAT = 1;
	public static final int RESERVED_SEAT = 2;

	private final int id;

	private int seatCount;

	private List<SeatAssignment> heldSeatList;

	private Date creationDate;

	private String emailContact;

	public SeatHold() {
		super();
		id = SEAT_ID++;
		setCreationDate(new Date());
	}

	public SeatHold(List<SeatAssignment> heldSeatList, String emailContact) {
		super();
		id = SEAT_ID++;
		setCreationDate(new Date());
		this.heldSeatList = heldSeatList;
		this.setEmailContact(emailContact);
	}

	public int getId() {
		return id;
	}

	public List<SeatAssignment> getHeldSeatList() {
		return heldSeatList;
	}

	public void setHeldSeatList(List<SeatAssignment> heldSeatList) {
		this.heldSeatList = heldSeatList;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getEmailContact() {
		return emailContact;
	}

	public void setEmailContact(String emailContact) {
		this.emailContact = emailContact;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((emailContact == null) ? 0 : emailContact.hashCode());
		result = prime * result + ((heldSeatList == null) ? 0 : heldSeatList.hashCode());
		result = prime * result + id;
		result = prime * result + seatCount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SeatHold other = (SeatHold) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (emailContact == null) {
			if (other.emailContact != null)
				return false;
		} else if (!emailContact.equals(other.emailContact))
			return false;
		if (heldSeatList == null) {
			if (other.heldSeatList != null)
				return false;
		} else if (!heldSeatList.equals(other.heldSeatList))
			return false;
		if (id != other.id)
			return false;
		if (seatCount != other.seatCount)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SeatHold [id=" + id + ", seatCount=" + seatCount + ", heldSeatList=" + heldSeatList + ", creationDate="
				+ creationDate + ", emailContact=" + emailContact + "]";
	}

}