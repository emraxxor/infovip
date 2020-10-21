package com.github.infovip.web.application.es.activity;

import com.github.infovip.core.data.BaseDataElement;
import com.github.infovip.core.data.ESDefaultJoinRelation;
import com.github.infovip.core.date.DefaultDateFormatter;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 
 * @author Attila Barna
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
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

}
