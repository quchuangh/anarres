package com.chuang.anarres.office.sys.event;

import com.chuang.anarres.enums.I18nType;
import org.springframework.context.ApplicationEvent;

public class I18nUpdatedEvent extends ApplicationEvent {

    private I18nType type;

    public I18nUpdatedEvent(Object source, I18nType type) {
        super(source);
        this.type = type;
    }

    public I18nType getType() {
        return type;
    }
}
