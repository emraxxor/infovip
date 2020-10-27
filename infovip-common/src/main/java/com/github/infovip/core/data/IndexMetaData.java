package com.github.infovip.core.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.ExclusionStrategy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author Attila Barna
 *
 */
@Data
@Builder
@AllArgsConstructor
public class IndexMetaData {

	private String indexName;

	private String routing;
	
	private List<ExclusionStrategy> strategies;

	
	public IndexMetaData(String indexName) {
		super();
		this.indexName = indexName;
		this.strategies = new ArrayList<>();
	}

	public static IndexMetaData create(String indexName) {
		return new IndexMetaData(indexName);
	}
	
	public static IndexMetaData create(String indexName, String routing) {
		IndexMetaData m = new IndexMetaData(indexName);
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


}
