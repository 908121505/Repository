package com.honglu.quickcall.common.api.code;

import java.io.ObjectStreamException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by len.song on 2017-12-07.
 */
public abstract class AbstractCodedEnum extends AbstractEnum {
    private static final Map<String, AbstractCodedEnum> unique = new ConcurrentHashMap(32, 1.0F);
    private String code = null;

    protected AbstractCodedEnum() {
    }

    protected AbstractCodedEnum(String name, String code, String desc) {
        super(name, desc);
        if (code != null && !code.isEmpty()) {
            String key = enumKey(this.getEnumType(), code);
            AbstractEnum $enum = (AbstractEnum)unique.get(key);
            if ($enum != null) {
                throw new IllegalArgumentException("枚举常量已经存在： " + key);
            } else {
                this.setCode(code);
                unique.put(key, this);
                unique.put(enumKey(this.getClass(), code), this);
            }
        } else {
            throw new IllegalArgumentException("CodedEnum的编码值不能为空");
        }
    }

    public static <T extends AbstractCodedEnum> T valueByCode(Class<? extends AbstractEnum> enumType, String code) {
        initialize(enumType);
        String key = enumKey(enumType, code);
        T value = (T)unique.get(key);
        if (value != null) {
            return value;
        } else {
            Class<?> superclass = enumType.getSuperclass();
            return AbstractCodedEnum.class.isAssignableFrom(superclass) ? valueByCode(enumType, code) : value;
        }
    }

    protected Object readResolve() throws ObjectStreamException {
        super.readResolve();
        return this.readResolve(this.code(), new ParseValue() {
            public AbstractEnum valueOf(Class<? extends AbstractEnum> enumType, String key) {

                return AbstractCodedEnum.valueByCode(enumType, key);
            }
        });
    }

    public String code() {
        return this.code;
    }

    private void setCode(String code) {
        this.code = code;
    }
}
