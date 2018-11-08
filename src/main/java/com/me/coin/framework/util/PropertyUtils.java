package com.me.coin.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.coin.framework.ioc.CoinIoc;



public class PropertyUtils {
    /** 日志 */
	private static Logger log = LoggerFactory.getLogger(CoinIoc.class);

    /** 配置文件拥有者 */
    private static Properties p = new Properties();

    /**
     * 类初始化
     */
    static {

        InputStream is = PropertyUtils.class.getResourceAsStream("/coin.properties");

        try {
            // 读取配置参数
            p.load(is);

            // 关闭数据流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("配置文件加载出错");;
        }

    }

    /**
     * 获得配置属性值
     * 
     * @param key
     *            配置属性
     * @return 配置属性值
     */
    public static String getProperty(String key) {
        return p.getProperty(key);
    }
    
    
    
    /**
     * 获得配置属性值
     * 
     * 转为数组形式
     * 
     * @param key
     *            配置属性
     * @return 配置属性值
     */
    public static String[] getPropertyArray(String key) {
        return p.getProperty(key).split(",") ;
    }

    /**
     * 获得配置属性值
     * 
     * @param key
     *            配置属性
     * @param defaultValue
     *            默认值
     * @return 配置属性值
     */
    public static String getProperty(String key, String defaultValue) {
        return p.getProperty(key, defaultValue);
    }
}
