package com.honglu.quickcall.common.api.code;

/**
 * Created by len.song on 2017-12-08.
 */
public class SourceCode extends AbstractCodedEnum {
    public static final SourceCode Web = new SourceCode("Web", "01", "网站");
    public static final SourceCode H5 = new SourceCode("H5", "02", "H5");
    public static final SourceCode IOS = new SourceCode("IOS", "03", "IOS");
    public static final SourceCode Android = new SourceCode("Android", "04", "Android");
    public static final SourceCode WeChat = new SourceCode("WeChat", "05", "微信");
    public static final SourceCode OpenApi = new SourceCode("OpenApi", "06", "开放平台");

    public SourceCode() {
    }

    public SourceCode(String name, String code, String desc) {
        super(name, code, desc);
    }

    protected Class<? extends AbstractEnum> getEnumType() {
        return SourceCode.class;
    }
}
