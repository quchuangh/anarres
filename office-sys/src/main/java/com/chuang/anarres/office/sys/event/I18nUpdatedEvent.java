package com.chuang.anarres.office.sys.event;

import org.springframework.context.ApplicationEvent;

public class I18nUpdatedEvent extends ApplicationEvent {

    public I18nUpdatedEvent(Object source) {
        super(source);
    }
}
