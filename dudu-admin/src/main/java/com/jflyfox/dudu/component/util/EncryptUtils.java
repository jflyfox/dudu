package com.jflyfox.dudu.component.util;

import com.jflyfox.util.encrypt.DES3Utils;

/**
 * Created by flyfox 369191470@qq.com on 2017/4/23.
 */
public class EncryptUtils {

    private static final DES3Utils des = new DES3Utils("flyoffox");
    private static final String BASE64_ADD = "A_A";
    private static final String BASE64_SLASH = "S_S";
    private static final String BASE64_EQUAL = "E_E";
    private static final String DEFAULT_PASSWORD = "123456";

    // admin:1RHFCLt64uOOViCTzgSaww== test:ldKI9edsQVM=
    public static void main(String[] args) {
        String password = "admin123";
        String tmp = passwordEncrypt(password);
        System.out.println(tmp);
        System.out.println(passwordDecrypt(tmp));

        password = "123456";
        tmp = passwordEncrypt(password);
        System.out.println(tmp);
        System.out.println(passwordDecrypt(tmp));

        password = "admin123";
        tmp = dataEncrypt(password);
        System.out.println(tmp);
        System.out.println(dataDecrypt(tmp));
    }

    /**
     * 数据编码
     * <p>
     * 2015年2月25日 下午2:22:08 flyfox 369191470@qq.com
     *
     * @param data
     * @return
     */
    public static String dataEncrypt(String data) {
        String str = encrypt(data);
        str = str.replaceAll("\\+", BASE64_ADD);
        str = str.replaceAll("/", BASE64_SLASH);
        str = str.replaceAll("=", BASE64_EQUAL);
        return str;
    }

    /**
     * 数据解码
     * <p>
     * 2015年2月25日 下午2:22:13 flyfox 369191470@qq.com
     *
     * @param data
     * @return
     */
    public static String dataDecrypt(String data) {
        String str = data;
        str = str.replaceAll(BASE64_ADD, "+");
        str = str.replaceAll(BASE64_SLASH, "/");
        str = str.replaceAll(BASE64_EQUAL, "=");
        return decrypt(str);
    }

    /**
     * 密码编码
     * <p>
     * 2015年2月25日 下午2:22:08 flyfox 369191470@qq.com
     *
     * @param password
     * @return
     */
    public static String passwordEncrypt(String password) {
        return encrypt(password);
    }

    /**
     * 密码解码
     * <p>
     * 2015年2月25日 下午2:22:13 flyfox 369191470@qq.com
     *
     * @param encryptPassword
     * @return
     */
    public static String passwordDecrypt(String encryptPassword) {
        return decrypt(encryptPassword);
    }

    /**
     * 默认密码
     * <p>
     * 2015年2月25日 下午2:23:37 flyfox 369191470@qq.com
     *
     * @return
     */
    public static String defaultPassword() {
        return passwordEncrypt(DEFAULT_PASSWORD);
    }

    private static String encrypt(String encryptStr) {
        return des.encryptString(encryptStr);
    }

    private static String decrypt(String encryptStr) {
        return des.decryptString(encryptStr);
    }
}
