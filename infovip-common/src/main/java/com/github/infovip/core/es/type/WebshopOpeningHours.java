package com.github.infovip.core.es.type;

/**
 * 
 * @author attila
 *
 */
public class WebshopOpeningHours {

	private OpeningHour monday;
	
	private OpeningHour tuesday;
	
	private OpeningHour wednesday;
	
	private OpeningHour thursday;
	
	private OpeningHour friday;
	
	private OpeningHour saturday;
	
	private OpeningHour sunday;

	public OpeningHour getMonday() {
		return monday;
	}

	public void setMonday(OpeningHour monday) {
		this.monday = monday;
	}

	public OpeningHour getTuesday() {
		return tuesday;
	}

	public void setTuesday(OpeningHour tuesday) {
		this.tuesday = tuesday;
	}


	public OpeningHour getWednesday() {
		return wednesday;
	}

	public void setWednesday(OpeningHour wednesday) {
		this.wednesday = wednesday;
	}

	public OpeningHour getThursday() {
		return thursday;
	}

	public void setThursday(OpeningHour thursday) {
		this.thursday = thursday;
	}

	public OpeningHour getFriday() {
		return friday;
	}

	public void setFriday(OpeningHour friday) {
		this.friday = friday;
	}

	public OpeningHour getSaturday() {
		return saturday;
	}

	public void setSaturday(OpeningHour saturday) {
		this.saturday = saturday;
	}

	public OpeningHour getSunday() {
		return sunday;
	}

	public void setSunday(OpeningHour sunday) {
		this.sunday = sunday;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((friday == null) ? 0 : friday.hashCode());
		result = prime * result + ((monday == null) ? 0 : monday.hashCode());
		result = prime * result + ((saturday == null) ? 0 : saturday.hashCode());
		result = prime * result + ((sunday == null) ? 0 : sunday.hashCode());
		result = prime * result + ((thursday == null) ? 0 : thursday.hashCode());
		result = prime * result + ((tuesday == null) ? 0 : tuesday.hashCode());
		result = prime * result + ((wednesday == null) ? 0 : wednesday.hashCode());
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
		WebshopOpeningHours other = (WebshopOpeningHours) obj;
		if (friday == null) {
			if (other.friday != null)
				return false;
		} else if (!friday.equals(other.friday))
			return false;
		if (monday == null) {
			if (other.monday != null)
				return false;
		} else if (!monday.equals(other.monday))
			return false;
		if (saturday == null) {
			if (other.saturday != null)
				return false;
		} else if (!saturday.equals(other.saturday))
			return false;
		if (sunday == null) {
			if (other.sunday != null)
				return false;
		} else if (!sunday.equals(other.sunday))
			return false;
		if (thursday == null) {
			if (other.thursday != null)
				return false;
		} else if (!thursday.equals(other.thursday))
			return false;
		if (tuesday == null) {
			if (other.tuesday != null)
				return false;
		} else if (!tuesday.equals(other.tuesday))
			return false;
		if (wednesday == null) {
			if (other.wednesday != null)
				return false;
		} else if (!wednesday.equals(other.wednesday))
			return false;
		return true;
	}
	
	
	
	
}
