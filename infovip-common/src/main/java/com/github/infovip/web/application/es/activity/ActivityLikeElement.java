package com.github.infovip.web.application.es.activity;

import com.github.infovip.core.data.BaseDataElement;
import com.github.infovip.core.data.ESDefaultJoinRelation;
import com.github.infovip.core.date.DefaultDateFormatter;


/**
 * 
 * @author Attila Barna
 *
 */
public class ActivityLikeElement extends BaseDataElement {

	private String date;
	
	private Long uid;
	
	private String type;

	private String doc;
	
    private ESDefaultJoinRelation join;
	
    public ActivityLikeElement() {}
    
	public ActivityLikeElement(String parent, Long uid,  ActivityJoinType t) {
		this.date = DefaultDateFormatter.current();
		this.uid = uid;
		this.type = t.value();
		this.doc = parent;
		this.join = new ESDefaultJoinRelation(t.value(), parent);
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
	 * @return the uid
	 */
	public Long getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	

	/**
	 * @return the doc
	 */
	public String getDoc() {
		return doc;
	}

	/**
	 * @param doc the doc to set
	 */
	public void setDoc(String doc) {
		this.doc = doc;
	}
	
	public ESDefaultJoinRelation getJoin() {
		return join;
	}
	
	public void setJoin(ESDefaultJoinRelation join) {
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
		result = prime * result + ((doc == null) ? 0 : doc.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
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
		ActivityLikeElement other = (ActivityLikeElement) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (doc == null) {
			if (other.doc != null)
				return false;
		} else if (!doc.equals(other.doc))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
}
