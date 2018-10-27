# 对maven所有jar包的统一版本管理

比如用户模块叫headline-user，下面有headline-facade和headline-service
那么以下代码就放到headline-facade和headline-service的pom.xml中，不要放到headline-user的pom.xml中.
    
    <parent>
        <groupId>com.honglu.quickcall</groupId>
        <artifactId>quickcall-parent</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>
    
    
#### setting.xml文件
将settings.xml文件放入到maven的conf中.