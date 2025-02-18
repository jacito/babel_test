package com.mx.babel.employee.db.model.enums;

/**
 * Represents the different types of events that can occur in the system.
 */
public enum EventType {

    /**
     * Event triggered when a new entity is created.
     */
    CREATE,

    /**
     * Event triggered when an existing entity is updated.
     */
    UPDATE,

    /**
     * Event triggered when an entity is deleted.
     */
    DELETE,

    /**
     * Event triggered when retrieving an entity by its ID.
     */
    CONSULT_BY_ID,

    /**
     * Event triggered when retrieving only active entities.
     */
    CONSULT_ACTIVE,

    /**
     * Event triggered when retrieving all entities, regardless of status.
     */
    CONSULT_ALL
}
