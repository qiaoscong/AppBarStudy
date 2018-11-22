package text.qiao.com.appbarstudy;

import android.util.Log;

/**
 * Created by Shang on 2016/12/26.
 */
public class LogUtils {
    static String className;//类名
    static String methodName;//方法名
    static int lineNumber;//行数
    public static  boolean DEBUG = true;

    private LogUtils(){
        /* Protect from instantiations */
    }

    private static String createLog( String log ) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("["+methodName);
        buffer.append("(").append(className).append(":").append(lineNumber).append(")").append("]--  ");
        buffer.append(log);
        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements){
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }


    public static void e(String message){
        if (DEBUG) {
            // Throwable instance must be created before any methods
            getMethodNames(new Throwable().getStackTrace());
            Log.e(className, createLog(message));
        }
    }
    public static void i(String message){
        if (DEBUG){
            getMethodNames(new Throwable().getStackTrace());
            Log.i(className, createLog(message));
        }
    }

    public static void d(String message){
        if (DEBUG){
            getMethodNames(new Throwable().getStackTrace());
            Log.d(className, createLog(message));
        }
    }

    public static void v(String message){
        if (DEBUG){
            getMethodNames(new Throwable().getStackTrace());
            Log.v(className, createLog(message));
        }
    }

    public static void w(String message){
        if (DEBUG){
            getMethodNames(new Throwable().getStackTrace());
            Log.w(className, createLog(message));
        }
    }
    public static void wtf(String message){
        if (DEBUG){
            getMethodNames(new Throwable().getStackTrace());
            Log.wtf(className, createLog(message));
        }
    }
}
