package org.service.decoration.helpers;

import org.springframework.stereotype.Component;

/**
 * Factory of StateManager's is created to inject prototype StateManager's (they need to be prototype) into singleton
 * beans.
 */
@Component
public abstract class StateManagerFactory {
    public abstract StateManager createStateManager();
}
