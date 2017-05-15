package com.jflyfox.dudu.component.util;

import com.jflyfox.util.Config;
import com.jflyfox.util.StrUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JFlyFoxUtils {

    public static final String WEBSITE_TITLE = "WEBSITE_TITLE";
    public static final String TITLE_ATTR = "HEAD_TITLE";
    public static final String KEYWORDS_ATTR = "HEAD_KEYWORDS";
    public static final String DESCRIPTION_ATTR = "HEAD_DESCRIPTION";

    public static final int STATUS_SHOW = 1;
    public static final int STATUS_HIDE = 2;
    /**
     * 文章是否需要审核
     */
    public static final boolean ARTICLE_APPROVE = Config.getToBoolean("CMS.ARTICLE_APPROVE");

    /**
     * 承建部门ID
     */
    public static final int DEPART_BUILD_ID = 1;

    /**
     * 注册用户部门ID
     */
    public static final int DEPART_REGIST_ID = 2;

    /**
     * 第三方Oauth2部门ID
     */
    public static final int DEPART_THIRD_ID = 3;

    /**
     * 正常
     */
    public static final int USER_STATE_NORMAL = 10;

    /**
     * 管理员
     */
    public static final int USER_TYPE_ADMIN = 1;
    /**
     * 普通用户
     */
    public static final int USER_TYPE_NORMAL = 2;
    /**
     * 前台用户
     */
    public static final int USER_TYPE_FRONT = 3;
    /**
     * 第三方用户
     */
    public static final int USER_TYPE_THIRD = 4;
    /**
     * API用户
     */
    public static final int USER_TYPE_API = 5;
    /**
     * 其他用户
     */
    public static final int USER_TYPE_OTHER = 9;


    /**
     * session唯一Key
     */
    public static final String USER_KEY = "USER_KEY";

    public static final int MENU_ABOUT = 90;

    /**
     * 博文目录
     */
    public static final int MENU_BLOG = 100;

    public static final int IS_DELETED_NO = 1;

    public static final int IS_DELETED_YES = 2;

    /**
     * 目录类型
     */
    public static final int MATERIAL_TYPE_ARTICLE = 102; // 文章
    public static final int MATERIAL_TYPE_IMAGE = 103; // 图片
    public static final int MATERIAL_TYPE_VIDEO = 104; // 视频
    public static final int MATERIAL_TYPE_OTHER = 105; // 其他
    public static final int MATERIAL_TYPE_FOLDER = 106; // 栏目

    /**
     * 删除侵入脚本
     * <p>
     * 2015年6月20日 下午5:16:21 flyfox 369191470@qq.com
     *
     * @param htmlStr
     * @return
     */
    public static String delScriptTag(String htmlStr) {
        Pattern p_script = Pattern.compile("<script[^>]*?>[\\s\\S]*?<\\/script>", 2);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");
        Pattern p_style = Pattern.compile("<style[^>]*?>[\\s\\S]*?<\\/style>", 2);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");
        return htmlStr.trim();
    }


    /**
     * 是否是后台请求地址
     * <p>
     * 2015年2月27日 上午11:38:37 flyfox 369191470@qq.com
     *
     * @param path
     * @return
     */
    public static boolean isBack(String path) {
        // 后台不需要认证页面
        if (path.startsWith("admin/login")  //
                || path.startsWith("admin/logout") //
                || path.startsWith("admin/trans")) {
            return false;
        }

        return StrUtils.isNotEmpty(path) // 空是首页
                && (path.startsWith("system/") // 系统管理
                || path.startsWith("admin/") // 后台管理
        );
    }

    public static final char UNDERLINE = '_';

    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    /**
     * xss处理
     *
     * @param s
     * @return
     */
    public static String xssEncode(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
                    sb.append('＞');// 全角大于号
                    break;
                case '<':
                    sb.append('＜');// 全角小于号
                    break;
                case '\'':
                    sb.append('‘');// 全角单引号
                    break;
                case '\"':
                    sb.append('“');// 全角双引号
                    break;
                case '&':
                    sb.append('＆');// 全角
                    break;
                case '\\':
                    sb.append('＼');// 全角斜线
                    break;
                case '#':
                    sb.append('＃');// 全角井号
                    break;
                case '(':
                    sb.append('（');//
                    break;
                case ')':
                    sb.append('）');//
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }
}
