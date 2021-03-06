package com.hanlinsir.io;

import java.io.InputStream;

/**
 * 读取配置文件工具类
 *
 * @author <a href="mailto:allen_lu_hh@163.com">lin</a>
 * @since 1.0
 */
public class MyResource {

    public static InputStream getResourceAsStream(String path) {
        return MyResource.class.getClassLoader().getResourceAsStream(path);
    }

}
