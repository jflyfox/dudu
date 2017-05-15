package com.jflyfox.dudu.component.beetl;

import com.jflyfox.util.DateUtils;
import com.jflyfox.util.StrUtils;
import com.jflyfox.util.extend.HtmlUtils;

public class BeetlFunctions {

    public static String getNow() {
        return DateUtils.getNow();
    }

    public static String getNow(String regex) {
        return DateUtils.getNow(regex);
    }

    public static String suojin(String str, int length) {
        return StrUtils.suojin(str, length);
    }

    /**
     * html预览
     * <p>
     * 2015年2月2日 下午3:40:34 flyfox 369191470@qq.com
     *
     * @param htmlStr
     * @return
     */
    public static String showHTML(String htmlStr, int num, String endStr) {
        String tmpStr = HtmlUtils.delHTMLTag(htmlStr);
        tmpStr = StrUtils.suojin(tmpStr, num + endStr.length(), endStr);
        return tmpStr;
    }
}
