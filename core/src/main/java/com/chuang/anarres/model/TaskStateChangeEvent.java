package com.chuang.anarres.model;

import com.chuang.anarres.enums.TaskStatus;
import lombok.Getter;

import java.util.EventObject;

@Getter
public class TaskStateChangeEvent extends EventObject {

    private final TaskStatus state;
    private final String message;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @param state
     * @param message
     * @throws IllegalArgumentException if source is null.
     */
    public TaskStateChangeEvent(Object source, TaskStatus state, String message) {
        super(source);
        this.state = state;
        this.message = message;
    }
}
