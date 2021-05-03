package com.chuang.anarres.office.sys.event;

import org.springframework.context.ApplicationEvent;

public class ApiUpdatedEvent extends ApplicationEvent {

    public ApiUpdatedEvent(Object source) {
        super(source);
    }
}
