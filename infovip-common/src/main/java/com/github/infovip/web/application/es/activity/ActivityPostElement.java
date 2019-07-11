package com.github.infovip.web.application.es.activity;

import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.es.type.IgnoreField;
import com.google.gson.annotations.Expose;

/**
 * 
 * @author Attila Barna
 *
 */
public class ActivityPostElement extends ActivityElement {

	@Expose(serialize=true,deserialize=true)
	@IgnoreField
	private String date;

	@Expose(serialize=true,deserialize=false)
	@IgnoreField
	private String join;
	
	public ActivityPostElement() {
		super();
		this.date = DefaultDateFormatter.current();
		this.postType = ActivityJoinType.POST.value();
		this.join = ActivityJoinType.POST.value(); 
	}
	
	
	
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	/**
	 * @return the join
	 */
	public String getJoin() {
		return join;
	}

	/**
	 * @param join the join to set
	 */
	public void setJoin(String join) {
		this.join = join;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivityPostElement other = (ActivityPostElement) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
	
	
	
}
