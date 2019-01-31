package com.github.infovip.core.meta;

/**
 * 
 * @author Attila Barna
 *
 * @param <T>
 */
public interface InternalMetaTagManager<T> {

    public void metaData(String requestURI, T view);

}
