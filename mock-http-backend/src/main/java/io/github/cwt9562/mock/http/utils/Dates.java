package io.github.cwt9562.mock.http.utils;

import java.text.ParseException;
import java.util.Date;

/**
 * <pre>
 * 日期类的工具类
 * 主要功能:
 *     1. 获取当前时间
 *     2. 日期转字符串 (format)
 *     3. 字符串转日期 (parse)
 * </pre>
 * @author cwentao@linewell.com
 * @date 2018/1/10
 */
public final class Dates {
    private Dates() {
    }

    /**
     * 默认的日期格式
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前日期
     *
     * @return 当前日期
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 字符串转日期
     */
    public static Date parse(Long milliseconds) {
        return new Date(milliseconds);
    }

    /**
     * 字符串转日期
     */
    public static Date parse(String input) {
        return parse(input, DATE_PATTERN);
    }

    /**
     * 字符串转日期
     */
    public static Date parse(String input, String pattern) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(input, pattern);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期转字符串
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期转字符串
     */
    public static String format(Date date, String pattern) {
        return org.apache.commons.lang3.time.DateFormatUtils.format(date, pattern);
    }
}
