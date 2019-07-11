package com.github.infovip.core.es.query;

import java.lang.reflect.Type;
import java.util.List;
import com.github.infovip.core.data.Field;
import com.github.infovip.core.data.IndexMetaData;

/**
 * 
 * @author Attila Barna
 *
 */
public interface DocumentManager {

	public <T> T findDocumentByField(List<Field> fields, IndexMetaData metaData, Type type);
	
	public <T> List<T> findDocumentsByField(List<Field> fields, IndexMetaData metaData, Type type, int size, int from);

	public <T> T findDocumentByDocumentId(String id, IndexMetaData meta, Type type);
	
	public <T> Long countByField(List<Field> fields, IndexMetaData metaData);
}
