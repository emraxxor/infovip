package com.github.infovip.entities.listener;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

import com.github.infovip.entities.CfgProductCategoryValue;

public class CfgProductCategoryValueListener {

    @PrePersist 
    public void onPrePersist(Object o) {
    }
    
    @PostPersist
    public void onPostPersist(Object o) {
    }

}
