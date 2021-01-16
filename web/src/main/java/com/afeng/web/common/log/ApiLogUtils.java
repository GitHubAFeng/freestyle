package com.afeng.web.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理并记录日志文件
 *
 * @author AFeng
 */
public class ApiLogUtils {
    private static Logger log = LoggerFactory.getLogger(ApiLogUtils.class);

    /**
     * Description: 打印输出，生产环境不会输出
     *
     * @author: afeng
     * @date: 2020/2/23 12:57
     */
    public static void debugPrint(String out) {
        try {
            //获取调用者的类名
            String classname = new Exception().getStackTrace()[1].getClassName();
            //获取调用者的方法名
            String method_name = new Exception().getStackTrace()[1].getMethodName();
            if (log.isDebugEnabled()) {
                log.debug(classname + "." + method_name + ":" + out);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Description: 打印输出
     *
     * @author: afeng
     * @date: 2020/2/23 12:57
     */
    public static void errorPrint(String out) {
        try {
            //获取调用者的类名
            String classname = new Exception().getStackTrace()[1].getClassName();
            //获取调用者的方法名
            String method_name = new Exception().getStackTrace()[1].getMethodName();
            if (log.isErrorEnabled()) {
                log.error(classname + "." + method_name + ":" + out);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Description: 打印输出
     *
     * @author: afeng
     * @date: 2020/2/23 12:57
     */
    public static void infoPrint(String out) {
        try {
            //获取调用者的类名
            String classname = new Exception().getStackTrace()[1].getClassName();
            //获取调用者的方法名
            String method_name = new Exception().getStackTrace()[1].getMethodName();
            if (log.isInfoEnabled()) {
                log.info(classname + "." + method_name + ":" + out);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Description: 打印输出
     *
     * @author: afeng
     * @date: 2020/2/23 12:57
     */
    public static void warnPrint(String out) {
        try {
            //获取调用者的类名
            String classname = new Exception().getStackTrace()[1].getClassName();
            //获取调用者的方法名
            String method_name = new Exception().getStackTrace()[1].getMethodName();
            if (log.isWarnEnabled()) {
                log.warn(classname + "." + method_name + ":" + out);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
