package com.chuang.anarres.crud.event;

import org.springframework.context.ApplicationEvent;

public class ApiUpdatedEvent extends ApplicationEvent {

    public ApiUpdatedEvent(Object source) {
        super(source);
    }
}
