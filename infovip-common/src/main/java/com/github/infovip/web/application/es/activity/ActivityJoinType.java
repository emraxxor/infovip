package com.github.infovip.web.application.es.activity;
import java.io.Serializable;

public enum ActivityJoinType implements Serializable {
	
	POST("post"),
	
	COMMENT("comment"),
	
	LIKE_COMMENT("like-comment"),
	
	LIKE_REPLY("like-reply"),
	
	LIKE_POST("like-post"),
	
	REPLY("reply");
	
    private String value;

    private ActivityJoinType(String val) {
        this.value = val;
    }

    public ActivityJoinType valueOfType(String v) {
        for (ActivityJoinType s : ActivityJoinType.values()) {
            if (s.toString().equals(v)) {
                return s;
            }
        }
        return null;
    }
    
    public String value() {
    	return this.value;
    }
    
    @Override
    public String toString() {
        return this.value;
    }

}
