package com.github.infovip.core.scroll;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Attila Barna
 *
 * @param <DATA_TYPE>
 */
public class DefaultScrollResponseData<DATA_TYPE> {

	protected List<DATA_TYPE> data;
	
	protected String token;

	protected long total;
	
	protected int count;
	
	public DefaultScrollResponseData() {
		this.data = new ArrayList<>();
	}
	
	/**
	 * @return the data
	 */
	public List<DATA_TYPE> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<DATA_TYPE> data) {
		this.data = data;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + (int) (total ^ (total >>> 32));
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
		DefaultScrollResponseData other = (DefaultScrollResponseData) obj;
		if (count != other.count)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (total != other.total)
			return false;
		return true;
	}	
	
	
}
