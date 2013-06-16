package org.focusns.common.web.tagext.control.event;

import java.util.HashMap;
import java.util.Map;

public class Event {

    private String eventName;
    //
    private Map<String, String> eventData = new HashMap<String, String>();

    public Event(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Map<String, String> getEventData() {
        return eventData;
    }

    public void setEventData(Map<String, String> eventData) {
        this.eventData = eventData;
    }
}
