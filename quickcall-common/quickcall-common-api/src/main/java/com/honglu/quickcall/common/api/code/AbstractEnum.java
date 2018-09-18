package com.honglu.quickcall.common.api.code;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class AbstractEnum implements Serializable {

    private static final Map<String, AbstractEnum> unique = new ConcurrentHashMap(32, 1.0F);
    private static final Map<Class<? extends AbstractEnum>, Boolean> initialized = new ConcurrentHashMap(32, 1.0F);
    private static final Set<Class<? extends AbstractEnum>> enumTypes = new HashSet();
    private static final Map<Class<? extends AbstractEnum>, Set<AbstractEnum>> mapping = new ConcurrentHashMap(32, 1.0F);
    private String name = null;
    private String desc = null;

    protected AbstractEnum() {

    }

    protected AbstractEnum(String name, String desc) {
        if (name != null && !name.isEmpty()) {
            String key = enumKey(this.getEnumType(), name);
            AbstractEnum $enum = (AbstractEnum)unique.get(key);
            if ($enum != null) {
                throw new IllegalArgumentException("枚举常量已经存在： " + key);
            } else {
                this.name = name;
                this.setDesc(desc);
                unique.put(key, this);
                unique.put(enumKey(this.getClass(), name), this);
                initialized.put(this.getClass(), true);
                synchronized(this.getEnumType()) {
                    enumTypes.add(this.getEnumType());
                    Set<AbstractEnum> set = (Set)mapping.get(this.getEnumType());
                    if (set == null) {
                        set = new HashSet(10, 1.0F);
                        mapping.put(this.getEnumType(), set);
                    }

                    ((Set)set).add(this);
                }
            }
        } else {
            throw new IllegalArgumentException("枚举类型的名称不能为空");
        }
    }

    public String name() {
        return this.name;
    }

    public String desc() {
        return this.desc;
    }

    private void setDesc(String desc) {
        this.desc = desc;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object other) {
        return this == other;
    }

    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public String toString() {
        return this.name;
    }

    protected abstract Class<? extends AbstractEnum> getEnumType();

    public static <T extends AbstractEnum> T valueOf(Class<? extends T> enumType, String name) {
        initialize(enumType);
        String key = enumKey(enumType, name);
        T value = (T) unique.get(key);
        if (value != null) {
            return value;
        } else {
            Class<?> superclass = enumType.getSuperclass();
            return AbstractEnum.class.isAssignableFrom(superclass) ? valueOf(enumType, name) : value;
        }
    }

    protected static String enumKey(Class<? extends AbstractEnum> enumType, String name) {
        return enumType.getName() + "#" + name;
    }

    protected static void initialize(Class<? extends AbstractEnum> enumType) {
        Boolean init = (Boolean)initialized.get(enumType);
        if (init == null || !init.booleanValue()) {
            try {
                Class.forName(enumType.getName(), true, enumType.getClassLoader());
                initialized.put(enumType, true);
            } catch (ClassNotFoundException var3) {
                initialized.put(enumType, false);
                throw new IllegalArgumentException("枚举类型不存在", var3);
            }
        }

        Class<?> superclass = enumType.getSuperclass();
        if (AbstractEnum.class.isAssignableFrom(superclass)) {
            initialize(enumType);
        }

    }

    public static <T extends AbstractEnum> Set<T> values(Class<? extends T> clazz) {
        initialize(clazz);
        Class<? extends AbstractEnum> enumType = getEnumType(clazz);
        if (enumType == null) {
            return Collections.emptySet();
        } else {
            Set<AbstractEnum> set = (Set)mapping.get(enumType);
            Set<T> copy = new HashSet(set.size());
            Iterator i$ = set.iterator();

            while(i$.hasNext()) {
                T value = (T)i$.next();
                copy.add(value);
            }

            return copy;
        }
    }

    public static boolean contains(Class<? extends AbstractEnum> enumType) {
        initialize(enumType);
        Iterator i$ = enumTypes.iterator();

        Class clazz;
        do {
            if (!i$.hasNext()) {
                return false;
            }

            clazz = (Class)i$.next();
        } while(!clazz.isAssignableFrom(enumType));

        return true;
    }

    public static Class<? extends AbstractEnum> getEnumType(Class<? extends AbstractEnum> clazz) {
        initialize(clazz);
        Iterator i$ = enumTypes.iterator();

        Class $clazz;
        do {
            if (!i$.hasNext()) {
                return null;
            }

            $clazz = (Class)i$.next();
        } while(!$clazz.isAssignableFrom(clazz));

        return $clazz;
    }

    protected Object readResolve() throws ObjectStreamException {
        return this.readResolve(this.name(), new AbstractEnum.ParseValue() {
            public AbstractEnum valueOf(Class<? extends AbstractEnum> enumType, String key) {
                AbstractEnum var10000 = AbstractEnum.this;
                return AbstractEnum.valueOf(enumType, key);
            }
        });
    }

    protected Object readResolve(String key, AbstractEnum.ParseValue parse) {
        initialize(this.getClass());
        Class enumType = this.getClass();

        while(true) {
            AbstractEnum enumValue = parse.valueOf(enumType, key);
            if (enumValue != null) {
                return enumValue;
            }

            Class<?> tempClass = enumType.getSuperclass();
            if (!AbstractEnum.class.isAssignableFrom(tempClass) || enumType.equals(this.getEnumType())) {
                enumType = this.getClass();
                synchronized(this.getEnumType()) {
                    if (enumValue != null) {
                        return enumValue;
                    } else {
                        while(true) {
                            String $key = enumKey(enumType, key);
                            unique.put($key, this);
                            if (!AbstractEnum.class.isAssignableFrom(tempClass) || enumType.equals(this.getEnumType())) {
                                return this;
                            }

                            enumType = tempClass;
                        }
                    }
                }
            }

            enumType = tempClass;
        }
    }

    protected interface ParseValue {
        AbstractEnum valueOf(Class<? extends AbstractEnum> var1, String var2);
    }
}
