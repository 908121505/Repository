package com.honglu.quickcall.common.api.code;

/**
 * Created by len.song on 2017-12-07.
 */
public class ServiceCode extends AbstractCodedEnum {
    public static final ServiceCode Default = new ServiceCode("Default", "000", "默认");

    public ServiceCode() {

    }

    public ServiceCode(String name, String code, String desc) {
        super(name, code, desc);
    }

    protected Class<? extends AbstractEnum> getEnumType() {
        return ServiceCode.class;
    }
}