package com.data.java.crawler.utils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties�ļ���ȡ������
 */
public class PropertyUtil {
    
    private static Properties props;
    static {
        loadProps();
    }

    synchronized static private void loadProps(){
        System.out.println("��ʼ����properties�ļ�����.......");
        props = new Properties();
        InputStream in = null;
        try {
            // ��һ�֣�ͨ������������л�ȡproperties�ļ���-->
            in = PropertyUtil.class.getClassLoader().getResourceAsStream("env.properties");
            // �ڶ��֣�ͨ������л�ȡproperties�ļ���-->
            //in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            System.out.println("env.properties�ļ�δ�ҵ�");
        } catch (IOException e) {
            System.out.println("����IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                System.out.println("env.properties�ļ����رճ����쳣");
            }
        }
        System.out.println("����properties�ļ��������...........");
        System.out.println("properties�ļ����ݣ�" + props);
    }

    /**
     * ����key��ȡ�����ļ��е�����
     */
    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    /**
     * ����key��ȡ�����ļ��е����ԣ���Ϊnullʱ����ָ����Ĭ��ֵ
     */
    public static String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }
}