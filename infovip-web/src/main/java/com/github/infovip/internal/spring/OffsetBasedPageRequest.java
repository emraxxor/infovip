package com.github.infovip.internal.spring;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * 
 * @author attila
 *
 */
public class OffsetBasedPageRequest implements Pageable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9005221984264399684L;

	private int size;
	
	private long offset;

	private final Sort sort;

	public OffsetBasedPageRequest(long offset, int size) {
		this(offset,size,Sort.unsorted());
	}

	public OffsetBasedPageRequest(long offset, int size, Direction direction, String... properties) {
		this(offset, size, new Sort(direction, properties));
	}

	public OffsetBasedPageRequest(long offset, int size, Sort sort) {
		this.offset = offset;
		this.size = size;
		this.sort = sort;
	}
	

	public Sort getSort() {
		return sort;
	}

	public Pageable next() {
		return new OffsetBasedPageRequest(getPageNumber() + 1, getPageSize(), getSort());
	}

	public OffsetBasedPageRequest previous() {
		return getPageNumber() == 0 ? this : new OffsetBasedPageRequest(getPageNumber() - 1, getPageSize(), getSort());
	}

	public Pageable first() {
		return new OffsetBasedPageRequest(0, getPageSize(), getSort());
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj) { return true; }

		if (!(obj instanceof OffsetBasedPageRequest)) {return false;}

		OffsetBasedPageRequest that = (OffsetBasedPageRequest) obj;

		boolean sortEqual = this.sort == null ? that.getSort() == null : this.sort.equals(that.getSort());

		return super.equals(that) && sortEqual;
	}

	@Override
	public int hashCode() {
		return 31 * super.hashCode() + (null == sort ? 0 : sort.hashCode());
	}

	@Override
	public String toString() {
		return String.format("Page request [number: %d, size %d, sort: %s]", getPageNumber(), getPageSize(), sort == null ? null : sort.toString());
	}

	public int getPageNumber() {
		return Long.valueOf(offset).intValue();
	}

	public int getPageSize() {
		return size;
	}

	public long getOffset() {
		return offset;
	}

	public Pageable previousOrFirst() {
		return hasPrevious() ? previous() : first();
	}

	public boolean hasPrevious() {
		return offset > 0;
	}
	
}
