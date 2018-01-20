package org.cool.zoo.entities.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.*;

public final class JResponseEntity<T> {

    @JsonProperty("MESSAGE")
    private String message;
    @JsonProperty("CODE")
    private Integer code;
    @JsonProperty("STATE")
    private Boolean state;
    @JsonProperty("HTTP_STATUS")
    private HttpStatus status;
    @JsonProperty("DATA")
    private final List<T> data = new ArrayList();
    @JsonProperty("MAP")
    private final Map<String, T> map = new HashMap();

    public JResponseEntity() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code == null ? (status != null ? code = status.value() : null) : code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<T> getData() {
        return data;
    }

    public Map<String, T> getMap() {
        return map;
    }

    public void addBody(T body) {
        if (body != null) {
            if (body instanceof List)
                data.addAll((Collection<? extends T>) body);
            else
                data.add(body);
        }
    }

    public void addBody(String key, T body) {
        if (body != null) {
            if (body instanceof List) {
                data.addAll((Collection<? extends T>) body);
            } else if (key != null && !key.isEmpty()) {
                map.put(key, body);
            }
        }
    }
}
