package com.github.infovip.core.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.ExclusionStrategy;

/**
 * 
 * @author Attila Barna
 *
 */
public class IndexMetaData {

	private String indexName;

	private String indexType;
	
	private String routing;
	
	private List<ExclusionStrategy> strategies;

	
	public IndexMetaData(String indexName, String indexType) {
		super();
		this.indexName = indexName;
		this.indexType = indexType;
		this.strategies = new ArrayList<>();
	}

	public static IndexMetaData create(String indexName, String indexType) {
		return new IndexMetaData(indexName, indexType);
	}
	
	public static IndexMetaData create(String indexName, String indexType, String routing) {
		IndexMetaData m = new IndexMetaData(indexName, indexType);
		m.setRouting(routing);
		return m;
	}
	
	public final List<ExclusionStrategy> exclusionStrategies() {
		return strategies;
	}
	
	public IndexMetaData addExclusionStrategy(ExclusionStrategy e) {
		strategies.add(e);
		return this;
	}

	
	/**
	 * @return the indexName
	 */
	public String getIndexName() {
		return indexName;
	}

	/**
	 * @param indexName the indexName to set
	 */
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	/**
	 * @return the indexType
	 */
	public String getIndexType() {
		return indexType;
	}

	/**
	 * @param indexType the indexType to set
	 */
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}

	public void setRouting(String routing) {
		this.routing = routing;
	}
	
	public String getRouting() {
		return routing;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((indexName == null) ? 0 : indexName.hashCode());
		result = prime * result + ((indexType == null) ? 0 : indexType.hashCode());
		result = prime * result + ((routing == null) ? 0 : routing.hashCode());
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
		IndexMetaData other = (IndexMetaData) obj;
		if (indexName == null) {
			if (other.indexName != null)
				return false;
		} else if (!indexName.equals(other.indexName))
			return false;
		if (indexType == null) {
			if (other.indexType != null)
				return false;
		} else if (!indexType.equals(other.indexType))
			return false;
		if (routing == null) {
			if (other.routing != null)
				return false;
		} else if (!routing.equals(other.routing))
			return false;
		return true;
	}	
	
}
