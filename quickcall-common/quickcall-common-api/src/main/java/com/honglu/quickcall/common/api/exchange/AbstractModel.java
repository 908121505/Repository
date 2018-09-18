package com.honglu.quickcall.common.api.exchange;

import com.honglu.quickcall.common.api.tools.MyReflectionToStringBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by len.song on 2017-12-07.
 * @JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })  可以解决Json解析问题
 */
public class AbstractModel implements Serializable {
    private final Map<String, String> extFields = new HashMap();

    public AbstractModel() {
    }

    public final Map<String, String> getExtFields() {
        return this.extFields;
    }

    public void setExtFields(Map<String, String> extFields) {
        if (extFields == null) {
            throw new IllegalArgumentException("extFields is null");
        } else {
            Iterator i$ = extFields.keySet().iterator();

            while(i$.hasNext()) {
                String key = (String)i$.next();
                this.extFields.put(key, extFields.get(key));
            }

        }
    }

    public void copyExtFields(Map<String, String> extFields) {
        this.setExtFields(extFields);
    }

    public void removeExtField(String key) {
        if (key != null && !key.trim().equals("")) {
            this.extFields.remove(key);
        } else {
            throw new IllegalArgumentException("Key is null");
        }
    }

    public String getExtField(String key) {
        if (key != null && !key.trim().equals("")) {
            return (String)this.extFields.get(key);
        } else {
            throw new IllegalArgumentException("Key is null");
        }
    }

    public void putExtField(String key, String value) {
        if (key != null && !key.trim().equals("")) {
            if (value != null && !value.trim().equals("")) {
                this.extFields.put(key, value);
            } else {
                throw new IllegalArgumentException("Value is null");
            }
        } else {
            throw new IllegalArgumentException("Key is null");
        }
    }

    public String toString() {
        try {
            return (new MyReflectionToStringBuilder(this)).toString();
        } catch (Throwable var2) {
            return "";
        }
    }
}
