package com.github.infovip.core;

import javax.persistence.EntityManager;

public interface DefaultEntityInterface {

	 /**
     * Gets the entity manager
     * @return
     */
    public EntityManager getEntityManager();
}
