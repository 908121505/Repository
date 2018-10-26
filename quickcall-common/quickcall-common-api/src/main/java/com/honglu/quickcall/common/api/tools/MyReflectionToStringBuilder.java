package com.honglu.quickcall.common.api.tools;


import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by len.song on 2017-12-07.
 */
public class MyReflectionToStringBuilder extends ReflectionToStringBuilder {
    private boolean useFieldNames = true;
    private String fieldNameValueSeparator = "=";
    private String contentStart = "[";
    private String nullText = "<null>";
    private boolean defaultFullDetail = true;
    private boolean fieldSeparatorAtStart = false;
    private String fieldSeparator = ",";
    private String sizeStartText = "<size=";
    private String sizeEndText = ">";
    private int prefix = 0;
    private int suffix = 0;
    private String mask = "******";
    private int type = 0;
    private static final ThreadLocal REGISTRY = new ThreadLocal();

    static Map getRegistry() {
        return (Map)REGISTRY.get();
    }

    static boolean isRegistered(Object value) {
        Map m = getRegistry();
        return m != null && m.containsKey(value);
    }

    public MyReflectionToStringBuilder(Object object) {
        super(object);
    }

    protected boolean accept(Field field) {
        this.type = 0;
        boolean result = super.accept(field);
        FieldIgnore ignoreString = (FieldIgnore)field.getAnnotation(FieldIgnore.class);
        if (result && ignoreString == null) {
            FieldMask hiddenString = (FieldMask)field.getAnnotation(FieldMask.class);
            if (hiddenString == null) {
                return true;
            } else {
                this.prefix = hiddenString.prelen();
                this.suffix = hiddenString.suflen();
                if (this.prefix == -1 && this.suffix == -1) {
                    return false;
                } else {
                    this.mask = hiddenString.mask();
                    this.type = 1;
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    protected void appendFieldsIn(Class clazz) {
        if (clazz.isArray()) {
            this.reflectionAppendArray(this.getObject());
        } else {
            Field[] fields = clazz.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);

            for(int i = 0; i < fields.length; ++i) {
                Field field = fields[i];
                String fieldName = field.getName();
                if (this.accept(field)) {
                    try {
                        Object fieldValue = this.getValue(field);
                        this.append(fieldName, fieldValue, field);
                    } catch (IllegalAccessException var7) {
                        throw new InternalError("Unexpected IllegalAccessException: " + var7.getMessage());
                    }
                }
            }

        }
    }

    public ToStringBuilder append(String fieldName, Object obj, Field field) {
        this.append(this.getStringBuffer(), fieldName, obj, (Boolean)null, field);
        return this;
    }

    public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetail, Field field) {
        this.appendFieldStart(buffer, fieldName);
        if (value == null) {
            this.appendNullText(buffer, fieldName);
        } else {
            this.appendInternal(buffer, fieldName, value, this.isFullDetail(fullDetail), field);
        }

        this.appendFieldEnd(buffer, fieldName);
    }

    protected void appendFieldEnd(StringBuffer buffer, String fieldName) {
        this.appendFieldSeparator(buffer);
    }

    static void register(Object value) {
        if (value != null) {
            Map m = getRegistry();
            if (m == null) {
                m = new WeakHashMap();
                REGISTRY.set(m);
            }

            ((Map)m).put(value, (Object)null);
        }

    }

    public void appendStart(StringBuffer buffer, Object object) {
        if (object != null) {
            this.appendClassName(buffer, object);
            this.appendIdentityHashCode(buffer, object);
            this.appendContentStart(buffer);
            if (this.fieldSeparatorAtStart) {
                this.appendFieldSeparator(buffer);
            }
        }

    }

    protected void appendFieldSeparator(StringBuffer buffer) {
        buffer.append(this.fieldSeparator);
    }

    protected void appendContentStart(StringBuffer buffer) {
        buffer.append(this.contentStart);
    }

    protected void appendClassName(StringBuffer buffer, Object object) {
        register(object);
        buffer.append(object.getClass().getName());
    }

    protected void appendIdentityHashCode(StringBuffer buffer, Object object) {
        register(object);
        buffer.append('@');
        buffer.append(Integer.toHexString(System.identityHashCode(object)));
    }

    protected void appendCyclicObject(StringBuffer buffer, String fieldName, Object value) {
        ObjectUtils.identityToString(buffer, value);
    }

    protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
        buffer.append(value);
    }

    protected void appendDetail(StringBuffer buffer, String fieldName, Object value, Field field) {
        if (this.type == 1) {
            buffer.append(mask(String.valueOf(value), this.prefix, this.suffix, this.mask));
        } else {
            buffer.append(value);
        }

    }

    public static String mask(String src, int prefix, int suffix, String mask) {
        String dest = null;
        String maskString = mask == null ? "" : mask;
        if (StringUtils.isNotBlank(src)) {
            dest = StringUtils.left(src, prefix) + maskString + StringUtils.right(src, suffix);
        }

        return dest;
    }

    protected void appendSummarySize(StringBuffer buffer, String fieldName, int size) {
        buffer.append(this.sizeStartText);
        buffer.append(size);
        buffer.append(this.sizeEndText);
    }

    protected void appendInternal(StringBuffer buffer, String fieldName, Object value, boolean detail, Field field) {
        if (isRegistered(value) && !(value instanceof Number) && !(value instanceof Boolean) && !(value instanceof Character)) {
            this.appendCyclicObject(buffer, fieldName, value);
        } else {
            register(value);

            try {
                if (value instanceof Collection) {
                    if (detail) {
                        this.appendDetail(buffer, fieldName, (Collection)value);
                    } else {
                        this.appendSummarySize(buffer, fieldName, ((Collection)value).size());
                    }
                } else if (value instanceof Map) {
                    if (detail) {
                        this.appendDetail(buffer, fieldName, (Map)value);
                    } else {
                        this.appendSummarySize(buffer, fieldName, ((Map)value).size());
                    }
                } else if (value instanceof long[]) {
                    if (detail) {
                        this.appendDetail(buffer, fieldName, (long[])((long[])value));
                    }
                } else if (value instanceof int[]) {
                    if (detail) {
                        this.appendDetail(buffer, fieldName, (int[])((int[])value));
                    }
                } else if (value instanceof short[]) {
                    if (detail) {
                        this.appendDetail(buffer, fieldName, (short[])((short[])value));
                    }
                } else if (value instanceof byte[]) {
                    if (detail) {
                        this.appendDetail(buffer, fieldName, (byte[])((byte[])value));
                    }
                } else if (value instanceof char[]) {
                    if (detail) {
                        this.appendDetail(buffer, fieldName, (char[])((char[])value));
                    }
                } else if (value instanceof double[]) {
                    if (detail) {
                        this.appendDetail(buffer, fieldName, (double[])((double[])value));
                    }
                } else if (value instanceof float[]) {
                    if (detail) {
                        this.appendDetail(buffer, fieldName, (float[])((float[])value));
                    }
                } else if (value instanceof boolean[]) {
                    if (detail) {
                        this.appendDetail(buffer, fieldName, (boolean[])((boolean[])value));
                    }
                } else if (value.getClass().isArray()) {
                    if (detail) {
                        this.appendDetail(buffer, fieldName, (Object[])((Object[])value));
                    }
                } else if (detail) {
                    this.appendDetail(buffer, fieldName, value, field);
                }
            } finally {
                unregister(value);
            }

        }
    }

    static void unregister(Object value) {
        if (value != null) {
            Map m = getRegistry();
            if (m != null) {
                m.remove(value);
                if (m.isEmpty()) {
                    REGISTRY.set((Object)null);
                }
            }
        }

    }

    protected boolean isFullDetail(Boolean fullDetailRequest) {
        return fullDetailRequest == null ? this.defaultFullDetail : fullDetailRequest.booleanValue();
    }

    protected void appendNullText(StringBuffer buffer, String fieldName) {
        buffer.append(this.nullText);
    }

    protected void appendFieldStart(StringBuffer buffer, String fieldName) {
        if (this.useFieldNames && fieldName != null) {
            buffer.append(fieldName);
            buffer.append(this.fieldNameValueSeparator);
        }

    }

    public void setUseFieldNames(boolean useFieldNames) {
        this.useFieldNames = useFieldNames;
    }

    public void setNullText(String nullText) {
        this.nullText = nullText;
    }

    public void setDefaultFullDetail(boolean defaultFullDetail) {
        this.defaultFullDetail = defaultFullDetail;
    }
}
