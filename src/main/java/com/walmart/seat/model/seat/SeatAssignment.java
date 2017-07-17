package com.walmart.seat.model.seat;

/**
 * 
 * @author andrew.slade
 *
 */
public class SeatAssignment {
	private int rowId;

	private int columnId;

	public SeatAssignment() {
		super();
	}

	public SeatAssignment(int rowId, int columnId) {
		super();
		this.rowId = rowId;
		this.columnId = columnId;
	}

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public int getColumnId() {
		return columnId;
	}

	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columnId;
		result = prime * result + rowId;
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
		SeatAssignment other = (SeatAssignment) obj;
		if (columnId != other.columnId)
			return false;
		if (rowId != other.rowId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seat [rowId=" + rowId + ", columnId=" + columnId + "]";
	}

}