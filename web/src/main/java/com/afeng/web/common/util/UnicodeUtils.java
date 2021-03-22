package com.afeng.web.common.util;


import org.apache.commons.lang3.StringEscapeUtils;

import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * Unicode处理工具类。
 * 含Emoji表情处理、中日韩字符判断、Unicode格式化表示等，可用于解决微信登录Emoji表情昵称乱码问题。
 * <p>
 * Created by liyujiang on 2019/11/21
 *
 * @author 大定府羡民
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class UnicodeUtils {

    private static final HashMap<String, String> SB_UNICODE = new HashMap<>();

    /**
     * 把参数中的emoji表情转换为Unicode编码
     *
     * @author AFeng
     * @createDate 2021/1/7 14:54
     **/
    public static String emojiToUnicode(String str) {
        return emojiEncode(false, str);
    }

    /**
     * 把参数文本全部转换为Unicode编码
     *
     * @author AFeng
     * @createDate 2021/1/7 14:54
     **/
    public static String stringToUnicode(String str) {
        return emojiEncode(true, str);
    }


    /**
     * Unicode编码转表情
     *
     * @author AFeng
     * @date 2021/2/24 14:23
     **/
    public static String unicodeToEmoji(String str) {
        if (str != null && str.length() > 0) {
            return StringEscapeUtils.unescapeJson(str);
        }
        return str;
    }

    /**
     * Emoji表情有很多种版本，其中包括Unified、DoCoMo、KDDI、SoftBank和Google，不同版本的Unicode代码并不一定相同。
     * <pre>
     * 微信昵称中的Emoji表情截止2019.12.06已知支持三种版本：
     * 1、SoftBank版本（网上一般称之为SB Unicode），如😂为E412；
     * 2、Unified版本，如😂为1F602；
     * 3、自定义表情版本，如[捂脸]。
     * 😂(喜极而泣)的各种编码如下：
     * SoftBank：0000E412
     * Unified：0001F602（U+1F602）
     * DoCoMo：0000E72A
     * KDDI：0000EB64
     * Google：000FE334
     * UTF-8：F09F9882（%F0%9F%98%82）
     * UTF-16BE：FEFFD83DDE02（\uD83D\uDE02）
     * UTF-16LE：FFFE3DD802DE
     * UTF-32BE：0000FEFF0001F602
     * UTF-32LE：FFFE000002F60100
     * Emoji表情代码表参阅：
     * http://punchdrunker.github.io/iOSEmoji/table_html/index.html
     * http://www.oicqzone.com/tool/emoji
     * https://github.com/iamcal/emoji-data/blob/master/emoji.json
     * https://github.com/google/emoji4unicode/blob/master/data/emoji4unicode.xml
     * </pre>
     */
    private static String emojiEncode(boolean encodeCJK, String str) {
        if (str == null || "".equals(str.trim())) {
            return "";
        }
        StringBuilder sb = new StringBuilder(str.length());
        char[] chars = str.toCharArray();
        for (int i = 0, n = chars.length; i < n; i++) {
            int codePoint = str.codePointAt(i);
            char aChar = str.charAt(i);
            if ((encodeCJK && isCJKCharacter(codePoint)) || isEmojiCharacter(codePoint)) {
                sb.append(toUnicodeFormal(aChar));
            } else {
                sb.append(aChar);
            }
        }
        return sb.toString();
    }

    /**
     * 判断是否是Emoji表情符号，参阅 https://www.cnblogs.com/hahahjx/p/4522913.html
     * <p>
     * 杂项象形符号:1F300-1F5FF
     * 表情符号：1F600-1F64F
     * 交通和地图符号:1F680-1F6FF
     * 杂项符号：2600-26FF
     * 符号字体:2700-27BF
     * 国旗：1F100-1F1FF
     * 箭头：2B00-2BFF 2900-297F
     * 各种技术符号：2300-23FF
     * 字母符号: 2100–214F
     * 中文符号： 303D 3200–32FF 2049 203C
     * Private Use Area:E000-F8FF;
     * High Surrogates D800..DB7F;
     * High Private Use Surrogates  DB80..DBFF
     * Low Surrogates DC00..DFFF  D800-DFFF E000-F8FF
     * 标点符号：2000-200F 2028-202F 205F 2065-206F
     * 变异选择器：IOS独有 FE00-FE0F
     */
    private static boolean isEmojiCharacter(int codePoint) {
        return (codePoint >= 0x2600 && codePoint <= 0x27BF) // 杂项符号与符号字体
                || codePoint == 0x303D
                || codePoint == 0x2049
                || codePoint == 0x203C
                || (codePoint >= 0x2000 && codePoint <= 0x200F)//
                || (codePoint >= 0x2028 && codePoint <= 0x202F)//
                || codePoint == 0x205F //
                || (codePoint >= 0x2065 && codePoint <= 0x206F)//
                || (codePoint >= 0x2100 && codePoint <= 0x214F)// 字母符号
                || (codePoint >= 0x2300 && codePoint <= 0x23FF)// 各种技术符号
                || (codePoint >= 0x2B00 && codePoint <= 0x2BFF)// 箭头A
                || (codePoint >= 0x2900 && codePoint <= 0x297F)// 箭头B
                || (codePoint >= 0x3200 && codePoint <= 0x32FF)// 中文符号
                || (codePoint >= 0xD800 && codePoint <= 0xDFFF)// 高低位替代符保留区域
                || (codePoint >= 0xE000 && codePoint <= 0xF8FF)// 私有保留区域
                || (codePoint >= 0xFE00 && codePoint <= 0xFE0F)// 变异选择器
                || codePoint >= 0x10000; // Plane在第二平面以上的，char都不可以存，全部都转
    }

    /**
     * 判断是否是中日韩字符
     * <p>
     * Unicode编码范围：
     * 汉字：[0x4e00,0x9fa5]（或十进制[19968,40869]）
     * 数字：[0x30,0x39]（或十进制[48, 57]）
     * 小写字母：[0x61,0x7a]（或十进制[97, 122]）
     * 大写字母：[0x41,0x5a]（或十进制[65, 90]）
     * <p>
     * UTF-8编码范围：
     * 中文：[\u4e00-\u9fa5]
     * 日文：[\u0800-\u4e00]
     * 韩文：[\uac00-\ud7ff]
     */
    private static boolean isCJKCharacter(int codePoint) {
        // Determines if the specified character (Unicode code point) is a CJKV
        // (Chinese, Japanese, Korean and Vietnamese) ideograph, as defined by
        // the Unicode Standard.
        return Character.isIdeographic(codePoint);
    }

    /**
     * SoftBank版本的Emoji表情与Unified版本的Emoji表情的UNICODE对应关系
     */
    private static String obtainUnicodeBySoftBank(String softbank) {
        if (SB_UNICODE.size() == 0) {
            SB_UNICODE.put("E210", "\\u0023\\uFE0F\\u20E3");
            SB_UNICODE.put("E225", "\\u0030\\uFE0F\\u20E3");
            SB_UNICODE.put("E21C", "\\u0031\\uFE0F\\u20E3");
            SB_UNICODE.put("E21D", "\\u0032\\uFE0F\\u20E3");
            SB_UNICODE.put("E21E", "\\u0033\\uFE0F\\u20E3");
            SB_UNICODE.put("E21F", "\\u0034\\uFE0F\\u20E3");
            SB_UNICODE.put("E220", "\\u0035\\uFE0F\\u20E3");
            SB_UNICODE.put("E221", "\\u0036\\uFE0F\\u20E3");
            SB_UNICODE.put("E222", "\\u0037\\uFE0F\\u20E3");
            SB_UNICODE.put("E223", "\\u0038\\uFE0F\\u20E3");
            SB_UNICODE.put("E224", "\\u0039\\uFE0F\\u20E3");
            SB_UNICODE.put("E24E", "\\u00A9\\uFE0F");
            SB_UNICODE.put("E24F", "\\u00AE\\uFE0F");
            SB_UNICODE.put("E12D", "\\uD83C\\uDC04");
            SB_UNICODE.put("E532", "\\uD83C\\uDD70\\uFE0F");
            SB_UNICODE.put("E533", "\\uD83C\\uDD71\\uFE0F");
            SB_UNICODE.put("E535", "\\uD83C\\uDD7E\\uFE0F");
            SB_UNICODE.put("E14F", "\\uD83C\\uDD7F\\uFE0F");
            SB_UNICODE.put("E534", "\\uD83C\\uDD8E");
            SB_UNICODE.put("E214", "\\uD83C\\uDD92");
            SB_UNICODE.put("E229", "\\uD83C\\uDD94");
            SB_UNICODE.put("E212", "\\uD83C\\uDD95");
            SB_UNICODE.put("E24D", "\\uD83C\\uDD97");
            SB_UNICODE.put("E213", "\\uD83C\\uDD99");
            SB_UNICODE.put("E12E", "\\uD83C\\uDD9A");
            SB_UNICODE.put("E513", "\\uD83C\\uDDE8\\uD83C\\uDDF3");
            SB_UNICODE.put("E50E", "\\uD83C\\uDDE9\\uD83C\\uDDEA");
            SB_UNICODE.put("E511", "\\uD83C\\uDDEA\\uD83C\\uDDF8");
            SB_UNICODE.put("E50D", "\\uD83C\\uDDEB\\uD83C\\uDDF7");
            SB_UNICODE.put("E510", "\\uD83C\\uDDEC\\uD83C\\uDDE7");
            SB_UNICODE.put("E50F", "\\uD83C\\uDDEE\\uD83C\\uDDF9");
            SB_UNICODE.put("E50B", "\\uD83C\\uDDEF\\uD83C\\uDDF5");
            SB_UNICODE.put("E514", "\\uD83C\\uDDF0\\uD83C\\uDDF7");
            SB_UNICODE.put("E512", "\\uD83C\\uDDF7\\uD83C\\uDDFA");
            SB_UNICODE.put("E50C", "\\uD83C\\uDDFA\\uD83C\\uDDF8");
            SB_UNICODE.put("E203", "\\uD83C\\uDE01");
            SB_UNICODE.put("E228", "\\uD83C\\uDE02\\uFE0F");
            SB_UNICODE.put("E216", "\\uD83C\\uDE1A");
            SB_UNICODE.put("E22C", "\\uD83C\\uDE2F");
            SB_UNICODE.put("E22B", "\\uD83C\\uDE33");
            SB_UNICODE.put("E22A", "\\uD83C\\uDE35");
            SB_UNICODE.put("E215", "\\uD83C\\uDE36");
            SB_UNICODE.put("E217", "\\uD83C\\uDE37\\uFE0F");
            SB_UNICODE.put("E218", "\\uD83C\\uDE38");
            SB_UNICODE.put("E227", "\\uD83C\\uDE39");
            SB_UNICODE.put("E22D", "\\uD83C\\uDE3A");
            SB_UNICODE.put("E226", "\\uD83C\\uDE50");
            SB_UNICODE.put("E443", "\\uD83C\\uDF00");
            SB_UNICODE.put("E43C", "\\uD83C\\uDF02");
            SB_UNICODE.put("E44B", "\\uD83C\\uDF03");
            SB_UNICODE.put("E04D", "\\uD83C\\uDF04");
            SB_UNICODE.put("E449", "\\uD83C\\uDF05");
            SB_UNICODE.put("E146", "\\uD83C\\uDF06");
            SB_UNICODE.put("E44A", "\\uD83C\\uDF07");
            SB_UNICODE.put("E44C", "\\uD83C\\uDF08");
            SB_UNICODE.put("E43E", "\\uD83C\\uDF0A");
            SB_UNICODE.put("E04C", "\\uD83C\\uDF19");
            SB_UNICODE.put("E335", "\\uD83C\\uDF1F");
            SB_UNICODE.put("E307", "\\uD83C\\uDF34");
            SB_UNICODE.put("E308", "\\uD83C\\uDF35");
            SB_UNICODE.put("E304", "\\uD83C\\uDF37");
            SB_UNICODE.put("E030", "\\uD83C\\uDF38");
            SB_UNICODE.put("E032", "\\uD83C\\uDF39");
            SB_UNICODE.put("E303", "\\uD83C\\uDF3A");
            SB_UNICODE.put("E305", "\\uD83C\\uDF3B");
            SB_UNICODE.put("E444", "\\uD83C\\uDF3E");
            SB_UNICODE.put("E110", "\\uD83C\\uDF40");
            SB_UNICODE.put("E118", "\\uD83C\\uDF41");
            SB_UNICODE.put("E119", "\\uD83C\\uDF42");
            SB_UNICODE.put("E447", "\\uD83C\\uDF43");
            SB_UNICODE.put("E349", "\\uD83C\\uDF45");
            SB_UNICODE.put("E34A", "\\uD83C\\uDF46");
            SB_UNICODE.put("E348", "\\uD83C\\uDF49");
            SB_UNICODE.put("E346", "\\uD83C\\uDF4A");
            SB_UNICODE.put("E345", "\\uD83C\\uDF4E");
            SB_UNICODE.put("E347", "\\uD83C\\uDF53");
            SB_UNICODE.put("E120", "\\uD83C\\uDF54");
            SB_UNICODE.put("E33D", "\\uD83C\\uDF58");
            SB_UNICODE.put("E342", "\\uD83C\\uDF59");
            SB_UNICODE.put("E33E", "\\uD83C\\uDF5A");
            SB_UNICODE.put("E341", "\\uD83C\\uDF5B");
            SB_UNICODE.put("E340", "\\uD83C\\uDF5C");
            SB_UNICODE.put("E33F", "\\uD83C\\uDF5D");
            SB_UNICODE.put("E339", "\\uD83C\\uDF5E");
            SB_UNICODE.put("E33B", "\\uD83C\\uDF5F");
            SB_UNICODE.put("E33C", "\\uD83C\\uDF61");
            SB_UNICODE.put("E343", "\\uD83C\\uDF62");
            SB_UNICODE.put("E344", "\\uD83C\\uDF63");
            SB_UNICODE.put("E33A", "\\uD83C\\uDF66");
            SB_UNICODE.put("E43F", "\\uD83C\\uDF67");
            SB_UNICODE.put("E046", "\\uD83C\\uDF70");
            SB_UNICODE.put("E34C", "\\uD83C\\uDF71");
            SB_UNICODE.put("E34D", "\\uD83C\\uDF72");
            SB_UNICODE.put("E147", "\\uD83C\\uDF73");
            SB_UNICODE.put("E043", "\\uD83C\\uDF74");
            SB_UNICODE.put("E338", "\\uD83C\\uDF75");
            SB_UNICODE.put("E30B", "\\uD83C\\uDF76");
            SB_UNICODE.put("E044", "\\uD83C\\uDF78");
            SB_UNICODE.put("E047", "\\uD83C\\uDF7A");
            SB_UNICODE.put("E30C", "\\uD83C\\uDF7B");
            SB_UNICODE.put("E314", "\\uD83C\\uDF80");
            SB_UNICODE.put("E112", "\\uD83C\\uDF81");
            SB_UNICODE.put("E34B", "\\uD83C\\uDF82");
            SB_UNICODE.put("E445", "\\uD83C\\uDF83");
            SB_UNICODE.put("E033", "\\uD83C\\uDF84");
            SB_UNICODE.put("E448", "\\uD83C\\uDF85");
            SB_UNICODE.put("E117", "\\uD83C\\uDF86");
            SB_UNICODE.put("E440", "\\uD83C\\uDF87");
            SB_UNICODE.put("E310", "\\uD83C\\uDF88");
            SB_UNICODE.put("E312", "\\uD83C\\uDF89");
            SB_UNICODE.put("E143", "\\uD83C\\uDF8C");
            SB_UNICODE.put("E436", "\\uD83C\\uDF8D");
            SB_UNICODE.put("E438", "\\uD83C\\uDF8E");
            SB_UNICODE.put("E43B", "\\uD83C\\uDF8F");
            SB_UNICODE.put("E442", "\\uD83C\\uDF90");
            SB_UNICODE.put("E446", "\\uD83C\\uDF91");
            SB_UNICODE.put("E43A", "\\uD83C\\uDF92");
            SB_UNICODE.put("E439", "\\uD83C\\uDF93");
            SB_UNICODE.put("E124", "\\uD83C\\uDFA1");
            SB_UNICODE.put("E433", "\\uD83C\\uDFA2");
            SB_UNICODE.put("E03C", "\\uD83C\\uDFA4");
            SB_UNICODE.put("E03D", "\\uD83C\\uDFA5");
            SB_UNICODE.put("E507", "\\uD83C\\uDFA6");
            SB_UNICODE.put("E30A", "\\uD83C\\uDFA7");
            SB_UNICODE.put("E502", "\\uD83C\\uDFA8");
            SB_UNICODE.put("E503", "\\uD83C\\uDFA9");
            SB_UNICODE.put("E125", "\\uD83C\\uDFAB");
            SB_UNICODE.put("E324", "\\uD83C\\uDFAC");
            SB_UNICODE.put("E130", "\\uD83C\\uDFAF");
            SB_UNICODE.put("E133", "\\uD83C\\uDFB0");
            SB_UNICODE.put("E42C", "\\uD83C\\uDFB1");
            SB_UNICODE.put("E03E", "\\uD83C\\uDFB5");
            SB_UNICODE.put("E326", "\\uD83C\\uDFB6");
            SB_UNICODE.put("E040", "\\uD83C\\uDFB7");
            SB_UNICODE.put("E041", "\\uD83C\\uDFB8");
            SB_UNICODE.put("E042", "\\uD83C\\uDFBA");
            SB_UNICODE.put("E015", "\\uD83C\\uDFBE");
            SB_UNICODE.put("E013", "\\uD83C\\uDFBF");
            SB_UNICODE.put("E42A", "\\uD83C\\uDFC0");
            SB_UNICODE.put("E132", "\\uD83C\\uDFC1");
            SB_UNICODE.put("E115", "\\uD83C\\uDFC3");
            SB_UNICODE.put("E017", "\\uD83C\\uDFC4");
            SB_UNICODE.put("E131", "\\uD83C\\uDFC6");
            SB_UNICODE.put("E42B", "\\uD83C\\uDFC8");
            SB_UNICODE.put("E42D", "\\uD83C\\uDFCA");
            SB_UNICODE.put("E036", "\\uD83C\\uDFE0");
            SB_UNICODE.put("E038", "\\uD83C\\uDFE2");
            SB_UNICODE.put("E153", "\\uD83C\\uDFE3");
            SB_UNICODE.put("E155", "\\uD83C\\uDFE5");
            SB_UNICODE.put("E14D", "\\uD83C\\uDFE6");
            SB_UNICODE.put("E154", "\\uD83C\\uDFE7");
            SB_UNICODE.put("E158", "\\uD83C\\uDFE8");
            SB_UNICODE.put("E501", "\\uD83C\\uDFE9");
            SB_UNICODE.put("E156", "\\uD83C\\uDFEA");
            SB_UNICODE.put("E157", "\\uD83C\\uDFEB");
            SB_UNICODE.put("E504", "\\uD83C\\uDFEC");
            SB_UNICODE.put("E508", "\\uD83C\\uDFED");
            SB_UNICODE.put("E505", "\\uD83C\\uDFEF");
            SB_UNICODE.put("E506", "\\uD83C\\uDFF0");
            SB_UNICODE.put("E52D", "\\uD83D\\uDC0D");
            SB_UNICODE.put("E134", "\\uD83D\\uDC0E");
            SB_UNICODE.put("E529", "\\uD83D\\uDC11");
            SB_UNICODE.put("E528", "\\uD83D\\uDC12");
            SB_UNICODE.put("E52E", "\\uD83D\\uDC14");
            SB_UNICODE.put("E52F", "\\uD83D\\uDC17");
            SB_UNICODE.put("E526", "\\uD83D\\uDC18");
            SB_UNICODE.put("E10A", "\\uD83D\\uDC19");
            SB_UNICODE.put("E441", "\\uD83D\\uDC1A");
            SB_UNICODE.put("E525", "\\uD83D\\uDC1B");
            SB_UNICODE.put("E019", "\\uD83D\\uDC1F");
            SB_UNICODE.put("E522", "\\uD83D\\uDC20");
            SB_UNICODE.put("E523", "\\uD83D\\uDC24");
            SB_UNICODE.put("E521", "\\uD83D\\uDC26");
            SB_UNICODE.put("E055", "\\uD83D\\uDC27");
            SB_UNICODE.put("E527", "\\uD83D\\uDC28");
            SB_UNICODE.put("E530", "\\uD83D\\uDC2B");
            SB_UNICODE.put("E520", "\\uD83D\\uDC2C");
            SB_UNICODE.put("E053", "\\uD83D\\uDC2D");
            SB_UNICODE.put("E52B", "\\uD83D\\uDC2E");
            SB_UNICODE.put("E050", "\\uD83D\\uDC2F");
            SB_UNICODE.put("E52C", "\\uD83D\\uDC30");
            SB_UNICODE.put("E04F", "\\uD83D\\uDC31");
            SB_UNICODE.put("E054", "\\uD83D\\uDC33");
            SB_UNICODE.put("E01A", "\\uD83D\\uDC34");
            SB_UNICODE.put("E109", "\\uD83D\\uDC35");
            SB_UNICODE.put("E052", "\\uD83D\\uDC36");
            SB_UNICODE.put("E10B", "\\uD83D\\uDC37");
            SB_UNICODE.put("E531", "\\uD83D\\uDC38");
            SB_UNICODE.put("E524", "\\uD83D\\uDC39");
            SB_UNICODE.put("E52A", "\\uD83D\\uDC3A");
            SB_UNICODE.put("E051", "\\uD83D\\uDC3B");
            SB_UNICODE.put("E419", "\\uD83D\\uDC40");
            SB_UNICODE.put("E41B", "\\uD83D\\uDC42");
            SB_UNICODE.put("E41A", "\\uD83D\\uDC43");
            SB_UNICODE.put("E41C", "\\uD83D\\uDC44");
            SB_UNICODE.put("E22E", "\\uD83D\\uDC46");
            SB_UNICODE.put("E22F", "\\uD83D\\uDC47");
            SB_UNICODE.put("E230", "\\uD83D\\uDC48");
            SB_UNICODE.put("E231", "\\uD83D\\uDC49");
            SB_UNICODE.put("E00D", "\\uD83D\\uDC4A");
            SB_UNICODE.put("E41E", "\\uD83D\\uDC4B");
            SB_UNICODE.put("E420", "\\uD83D\\uDC4C");
            SB_UNICODE.put("E00E", "\\uD83D\\uDC4D");
            SB_UNICODE.put("E421", "\\uD83D\\uDC4E");
            SB_UNICODE.put("E41F", "\\uD83D\\uDC4F");
            SB_UNICODE.put("E422", "\\uD83D\\uDC50");
            SB_UNICODE.put("E10E", "\\uD83D\\uDC51");
            SB_UNICODE.put("E318", "\\uD83D\\uDC52");
            SB_UNICODE.put("E302", "\\uD83D\\uDC54");
            SB_UNICODE.put("E006", "\\uD83D\\uDC55");
            SB_UNICODE.put("E319", "\\uD83D\\uDC57");
            SB_UNICODE.put("E321", "\\uD83D\\uDC58");
            SB_UNICODE.put("E322", "\\uD83D\\uDC59");
            SB_UNICODE.put("E323", "\\uD83D\\uDC5C");
            SB_UNICODE.put("E007", "\\uD83D\\uDC5F");
            SB_UNICODE.put("E13E", "\\uD83D\\uDC60");
            SB_UNICODE.put("E31A", "\\uD83D\\uDC61");
            SB_UNICODE.put("E31B", "\\uD83D\\uDC62");
            SB_UNICODE.put("E536", "\\uD83D\\uDC63");
            SB_UNICODE.put("E001", "\\uD83D\\uDC66");
            SB_UNICODE.put("E002", "\\uD83D\\uDC67");
            SB_UNICODE.put("E004", "\\uD83D\\uDC68");
            SB_UNICODE.put("E005", "\\uD83D\\uDC69");
            SB_UNICODE.put("E428", "\\uD83D\\uDC6B");
            SB_UNICODE.put("E152", "\\uD83D\\uDC6E");
            SB_UNICODE.put("E429", "\\uD83D\\uDC6F");
            SB_UNICODE.put("E515", "\\uD83D\\uDC71");
            SB_UNICODE.put("E516", "\\uD83D\\uDC72");
            SB_UNICODE.put("E517", "\\uD83D\\uDC73");
            SB_UNICODE.put("E518", "\\uD83D\\uDC74");
            SB_UNICODE.put("E519", "\\uD83D\\uDC75");
            SB_UNICODE.put("E51A", "\\uD83D\\uDC76");
            SB_UNICODE.put("E51B", "\\uD83D\\uDC77");
            SB_UNICODE.put("E51C", "\\uD83D\\uDC78");
            SB_UNICODE.put("E11B", "\\uD83D\\uDC7B");
            SB_UNICODE.put("E04E", "\\uD83D\\uDC7C");
            SB_UNICODE.put("E10C", "\\uD83D\\uDC7D");
            SB_UNICODE.put("E12B", "\\uD83D\\uDC7E");
            SB_UNICODE.put("E11A", "\\uD83D\\uDC7F");
            SB_UNICODE.put("E11C", "\\uD83D\\uDC80");
            SB_UNICODE.put("E253", "\\uD83D\\uDC81");
            SB_UNICODE.put("E51E", "\\uD83D\\uDC82");
            SB_UNICODE.put("E51F", "\\uD83D\\uDC83");
            SB_UNICODE.put("E31C", "\\uD83D\\uDC84");
            SB_UNICODE.put("E31D", "\\uD83D\\uDC85");
            SB_UNICODE.put("E31E", "\\uD83D\\uDC86");
            SB_UNICODE.put("E31F", "\\uD83D\\uDC87");
            SB_UNICODE.put("E320", "\\uD83D\\uDC88");
            SB_UNICODE.put("E13B", "\\uD83D\\uDC89");
            SB_UNICODE.put("E30F", "\\uD83D\\uDC8A");
            SB_UNICODE.put("E003", "\\uD83D\\uDC8B");
            SB_UNICODE.put("E034", "\\uD83D\\uDC8D");
            SB_UNICODE.put("E035", "\\uD83D\\uDC8E");
            SB_UNICODE.put("E111", "\\uD83D\\uDC8F");
            SB_UNICODE.put("E306", "\\uD83D\\uDC90");
            SB_UNICODE.put("E425", "\\uD83D\\uDC91");
            SB_UNICODE.put("E43D", "\\uD83D\\uDC92");
            SB_UNICODE.put("E327", "\\uD83D\\uDC93");
            SB_UNICODE.put("E023", "\\uD83D\\uDC94");
            SB_UNICODE.put("E328", "\\uD83D\\uDC97");
            SB_UNICODE.put("E329", "\\uD83D\\uDC98");
            SB_UNICODE.put("E32A", "\\uD83D\\uDC99");
            SB_UNICODE.put("E32B", "\\uD83D\\uDC9A");
            SB_UNICODE.put("E32C", "\\uD83D\\uDC9B");
            SB_UNICODE.put("E32D", "\\uD83D\\uDC9C");
            SB_UNICODE.put("E437", "\\uD83D\\uDC9D");
            SB_UNICODE.put("E204", "\\uD83D\\uDC9F");
            SB_UNICODE.put("E10F", "\\uD83D\\uDCA1");
            SB_UNICODE.put("E334", "\\uD83D\\uDCA2");
            SB_UNICODE.put("E311", "\\uD83D\\uDCA3");
            SB_UNICODE.put("E13C", "\\uD83D\\uDCA4");
            SB_UNICODE.put("E331", "\\uD83D\\uDCA6");
            SB_UNICODE.put("E330", "\\uD83D\\uDCA8");
            SB_UNICODE.put("E05A", "\\uD83D\\uDCA9");
            SB_UNICODE.put("E14C", "\\uD83D\\uDCAA");
            SB_UNICODE.put("E12F", "\\uD83D\\uDCB0");
            SB_UNICODE.put("E149", "\\uD83D\\uDCB1");
            SB_UNICODE.put("E14A", "\\uD83D\\uDCB9");
            SB_UNICODE.put("E11F", "\\uD83D\\uDCBA");
            SB_UNICODE.put("E00C", "\\uD83D\\uDCBB");
            SB_UNICODE.put("E11E", "\\uD83D\\uDCBC");
            SB_UNICODE.put("E316", "\\uD83D\\uDCBD");
            SB_UNICODE.put("E126", "\\uD83D\\uDCBF");
            SB_UNICODE.put("E127", "\\uD83D\\uDCC0");
            SB_UNICODE.put("E148", "\\uD83D\\uDCD6");
            SB_UNICODE.put("E301", "\\uD83D\\uDCDD");
            SB_UNICODE.put("E00B", "\\uD83D\\uDCE0");
            SB_UNICODE.put("E14B", "\\uD83D\\uDCE1");
            SB_UNICODE.put("E142", "\\uD83D\\uDCE2");
            SB_UNICODE.put("E317", "\\uD83D\\uDCE3");
            SB_UNICODE.put("E103", "\\uD83D\\uDCE9");
            SB_UNICODE.put("E101", "\\uD83D\\uDCEB");
            SB_UNICODE.put("E102", "\\uD83D\\uDCEE");
            SB_UNICODE.put("E00A", "\\uD83D\\uDCF1");
            SB_UNICODE.put("E104", "\\uD83D\\uDCF2");
            SB_UNICODE.put("E250", "\\uD83D\\uDCF3");
            SB_UNICODE.put("E251", "\\uD83D\\uDCF4");
            SB_UNICODE.put("E20B", "\\uD83D\\uDCF6");
            SB_UNICODE.put("E008", "\\uD83D\\uDCF7");
            SB_UNICODE.put("E12A", "\\uD83D\\uDCFA");
            SB_UNICODE.put("E128", "\\uD83D\\uDCFB");
            SB_UNICODE.put("E129", "\\uD83D\\uDCFC");
            SB_UNICODE.put("E141", "\\uD83D\\uDD0A");
            SB_UNICODE.put("E114", "\\uD83D\\uDD0D");
            SB_UNICODE.put("E03F", "\\uD83D\\uDD11");
            SB_UNICODE.put("E144", "\\uD83D\\uDD12");
            SB_UNICODE.put("E145", "\\uD83D\\uDD13");
            SB_UNICODE.put("E325", "\\uD83D\\uDD14");
            SB_UNICODE.put("E24C", "\\uD83D\\uDD1D");
            SB_UNICODE.put("E207", "\\uD83D\\uDD1E");
            SB_UNICODE.put("E11D", "\\uD83D\\uDD25");
            SB_UNICODE.put("E116", "\\uD83D\\uDD28");
            SB_UNICODE.put("E113", "\\uD83D\\uDD2B");
            SB_UNICODE.put("E23E", "\\uD83D\\uDD2F");
            SB_UNICODE.put("E209", "\\uD83D\\uDD30");
            SB_UNICODE.put("E031", "\\uD83D\\uDD31");
            SB_UNICODE.put("E21A", "\\uD83D\\uDD32");
            SB_UNICODE.put("E21B", "\\uD83D\\uDD33");
            SB_UNICODE.put("E219", "\\uD83D\\uDD34");
            SB_UNICODE.put("E024", "\\uD83D\\uDD50");
            SB_UNICODE.put("E025", "\\uD83D\\uDD51");
            SB_UNICODE.put("E026", "\\uD83D\\uDD52");
            SB_UNICODE.put("E027", "\\uD83D\\uDD53");
            SB_UNICODE.put("E028", "\\uD83D\\uDD54");
            SB_UNICODE.put("E029", "\\uD83D\\uDD55");
            SB_UNICODE.put("E02A", "\\uD83D\\uDD56");
            SB_UNICODE.put("E02B", "\\uD83D\\uDD57");
            SB_UNICODE.put("E02C", "\\uD83D\\uDD58");
            SB_UNICODE.put("E02D", "\\uD83D\\uDD59");
            SB_UNICODE.put("E02E", "\\uD83D\\uDD5A");
            SB_UNICODE.put("E02F", "\\uD83D\\uDD5B");
            SB_UNICODE.put("E03B", "\\uD83D\\uDDFB");
            SB_UNICODE.put("E509", "\\uD83D\\uDDFC");
            SB_UNICODE.put("E51D", "\\uD83D\\uDDFD");
            SB_UNICODE.put("E404", "\\uD83D\\uDE01");
            SB_UNICODE.put("E412", "\\uD83D\\uDE02");
            SB_UNICODE.put("E057", "\\uD83D\\uDE03");
            SB_UNICODE.put("E415", "\\uD83D\\uDE04");
            SB_UNICODE.put("E405", "\\uD83D\\uDE09");
            SB_UNICODE.put("E056", "\\uD83D\\uDE0A");
            SB_UNICODE.put("E40A", "\\uD83D\\uDE0C");
            SB_UNICODE.put("E106", "\\uD83D\\uDE0D");
            SB_UNICODE.put("E402", "\\uD83D\\uDE0F");
            SB_UNICODE.put("E40E", "\\uD83D\\uDE12");
            SB_UNICODE.put("E108", "\\uD83D\\uDE13");
            SB_UNICODE.put("E403", "\\uD83D\\uDE14");
            SB_UNICODE.put("E407", "\\uD83D\\uDE16");
            SB_UNICODE.put("E418", "\\uD83D\\uDE18");
            SB_UNICODE.put("E417", "\\uD83D\\uDE1A");
            SB_UNICODE.put("E105", "\\uD83D\\uDE1C");
            SB_UNICODE.put("E409", "\\uD83D\\uDE1D");
            SB_UNICODE.put("E058", "\\uD83D\\uDE1E");
            SB_UNICODE.put("E059", "\\uD83D\\uDE20");
            SB_UNICODE.put("E416", "\\uD83D\\uDE21");
            SB_UNICODE.put("E413", "\\uD83D\\uDE22");
            SB_UNICODE.put("E406", "\\uD83D\\uDE23");
            SB_UNICODE.put("E401", "\\uD83D\\uDE25");
            SB_UNICODE.put("E40B", "\\uD83D\\uDE28");
            SB_UNICODE.put("E408", "\\uD83D\\uDE2A");
            SB_UNICODE.put("E411", "\\uD83D\\uDE2D");
            SB_UNICODE.put("E40F", "\\uD83D\\uDE30");
            SB_UNICODE.put("E107", "\\uD83D\\uDE31");
            SB_UNICODE.put("E410", "\\uD83D\\uDE32");
            SB_UNICODE.put("E40D", "\\uD83D\\uDE33");
            SB_UNICODE.put("E40C", "\\uD83D\\uDE37");
            SB_UNICODE.put("E423", "\\uD83D\\uDE45");
            SB_UNICODE.put("E424", "\\uD83D\\uDE46");
            SB_UNICODE.put("E426", "\\uD83D\\uDE47");
            SB_UNICODE.put("E427", "\\uD83D\\uDE4C");
            SB_UNICODE.put("E41D", "\\uD83D\\uDE4F");
            SB_UNICODE.put("E10D", "\\uD83D\\uDE80");
            SB_UNICODE.put("E01E", "\\uD83D\\uDE83");
            SB_UNICODE.put("E435", "\\uD83D\\uDE84");
            SB_UNICODE.put("E01F", "\\uD83D\\uDE85");
            SB_UNICODE.put("E434", "\\uD83D\\uDE87");
            SB_UNICODE.put("E039", "\\uD83D\\uDE89");
            SB_UNICODE.put("E159", "\\uD83D\\uDE8C");
            SB_UNICODE.put("E150", "\\uD83D\\uDE8F");
            SB_UNICODE.put("E431", "\\uD83D\\uDE91");
            SB_UNICODE.put("E430", "\\uD83D\\uDE92");
            SB_UNICODE.put("E432", "\\uD83D\\uDE93");
            SB_UNICODE.put("E15A", "\\uD83D\\uDE95");
            SB_UNICODE.put("E01B", "\\uD83D\\uDE97");
            SB_UNICODE.put("E42E", "\\uD83D\\uDE99");
            SB_UNICODE.put("E42F", "\\uD83D\\uDE9A");
            SB_UNICODE.put("E202", "\\uD83D\\uDEA2");
            SB_UNICODE.put("E135", "\\uD83D\\uDEA4");
            SB_UNICODE.put("E14E", "\\uD83D\\uDEA5");
            SB_UNICODE.put("E137", "\\uD83D\\uDEA7");
            SB_UNICODE.put("E30E", "\\uD83D\\uDEAC");
            SB_UNICODE.put("E208", "\\uD83D\\uDEAD");
            SB_UNICODE.put("E136", "\\uD83D\\uDEB2");
            SB_UNICODE.put("E201", "\\uD83D\\uDEB6");
            SB_UNICODE.put("E138", "\\uD83D\\uDEB9");
            SB_UNICODE.put("E139", "\\uD83D\\uDEBA");
            SB_UNICODE.put("E151", "\\uD83D\\uDEBB");
            SB_UNICODE.put("E13A", "\\uD83D\\uDEBC");
            SB_UNICODE.put("E140", "\\uD83D\\uDEBD");
            SB_UNICODE.put("E309", "\\uD83D\\uDEBE");
            SB_UNICODE.put("E13F", "\\uD83D\\uDEC0");
            SB_UNICODE.put("E537", "\\u2122\\uFE0F");
            SB_UNICODE.put("E237", "\\u2196\\uFE0F");
            SB_UNICODE.put("E236", "\\u2197\\uFE0F");
            SB_UNICODE.put("E238", "\\u2198\\uFE0F");
            SB_UNICODE.put("E239", "\\u2199\\uFE0F");
            SB_UNICODE.put("E23C", "\\u23E9");
            SB_UNICODE.put("E23D", "\\u23EA");
            SB_UNICODE.put("E23A", "\\u25B6\\uFE0F");
            SB_UNICODE.put("E23B", "\\u25C0\\uFE0F");
            SB_UNICODE.put("E04A", "\\u2600\\uFE0F");
            SB_UNICODE.put("E049", "\\u2601\\uFE0F");
            SB_UNICODE.put("E009", "\\u260E\\uFE0F");
            SB_UNICODE.put("E04B", "\\u2614");
            SB_UNICODE.put("E045", "\\u2615");
            SB_UNICODE.put("E00F", "\\u261D\\uFE0F");
            SB_UNICODE.put("E414", "\\u263A\\uFE0F");
            SB_UNICODE.put("E23F", "\\u2648");
            SB_UNICODE.put("E240", "\\u2649");
            SB_UNICODE.put("E241", "\\u264A");
            SB_UNICODE.put("E242", "\\u264B");
            SB_UNICODE.put("E243", "\\u264C");
            SB_UNICODE.put("E244", "\\u264D");
            SB_UNICODE.put("E245", "\\u264E");
            SB_UNICODE.put("E246", "\\u264F");
            SB_UNICODE.put("E247", "\\u2650");
            SB_UNICODE.put("E248", "\\u2651");
            SB_UNICODE.put("E249", "\\u2652");
            SB_UNICODE.put("E24A", "\\u2653");
            SB_UNICODE.put("E20E", "\\u2660\\uFE0F");
            SB_UNICODE.put("E20F", "\\u2663\\uFE0F");
            SB_UNICODE.put("E20C", "\\u2665\\uFE0F");
            SB_UNICODE.put("E20D", "\\u2666\\uFE0F");
            SB_UNICODE.put("E123", "\\u2668\\uFE0F");
            SB_UNICODE.put("E20A", "\\u267F");
            SB_UNICODE.put("E252", "\\u26A0\\uFE0F");
            SB_UNICODE.put("E13D", "\\u26A1");
            SB_UNICODE.put("E018", "\\u26BD");
            SB_UNICODE.put("E016", "\\u26BE");
            SB_UNICODE.put("E048", "\\u26C4");
            SB_UNICODE.put("E24B", "\\u26CE");
            SB_UNICODE.put("E037", "\\u26EA");
            SB_UNICODE.put("E121", "\\u26F2");
            SB_UNICODE.put("E014", "\\u26F3");
            SB_UNICODE.put("E01C", "\\u26F5");
            SB_UNICODE.put("E122", "\\u26FA");
            SB_UNICODE.put("E03A", "\\u26FD");
            SB_UNICODE.put("E313", "\\u2702\\uFE0F");
            SB_UNICODE.put("E01D", "\\u2708\\uFE0F");
            SB_UNICODE.put("E010", "\\u270A");
            SB_UNICODE.put("E012", "\\u270B");
            SB_UNICODE.put("E011", "\\u270C\\uFE0F");
            SB_UNICODE.put("E32E", "\\u2728");
            SB_UNICODE.put("E206", "\\u2733\\uFE0F");
            SB_UNICODE.put("E205", "\\u2734\\uFE0F");
            SB_UNICODE.put("E333", "\\u274C");
            SB_UNICODE.put("E020", "\\u2753");
            SB_UNICODE.put("E336", "\\u2754");
            SB_UNICODE.put("E337", "\\u2755");
            SB_UNICODE.put("E021", "\\u2757");
            SB_UNICODE.put("E022", "\\u2764\\uFE0F");
            SB_UNICODE.put("E234", "\\u27A1\\uFE0F");
            SB_UNICODE.put("E211", "\\u27BF");
            SB_UNICODE.put("E235", "\\u2B05\\uFE0F");
            SB_UNICODE.put("E232", "\\u2B06\\uFE0F");
            SB_UNICODE.put("E233", "\\u2B07\\uFE0F");
            SB_UNICODE.put("E32F", "\\u2B50");
            SB_UNICODE.put("E332", "\\u2B55");
            SB_UNICODE.put("E12C", "\\u303D\\uFE0F");
            SB_UNICODE.put("E30D", "\\u3297\\uFE0F");
            SB_UNICODE.put("E315", "\\u3299\\uFE0F");
        }
        return SB_UNICODE.get(softbank);
    }

    private static String toUnicodeFormal(String str) {
        StringBuilder sb = new StringBuilder();
        byte[] utf16beBytes = str.getBytes(Charset.forName("UTF-16BE"));
        String hexString = bytesToHexString(utf16beBytes);
        for (int i = 0, n = hexString.length(); i < n; i = i + 4) {
            sb.append("\\u").append(hexString.charAt(i)).append(hexString.charAt(i + 1)).append(hexString.charAt(i + 2)).append(hexString.charAt(i + 3));
        }
        return sb.toString();
    }

    private static boolean containsCJK(String str) {
        return hasMultiCharacter(str, true, false);
    }

    private static boolean containsEmoji(String str) {
        return hasMultiCharacter(str, false, true);
    }

    private static String bytesToHexString(byte[] bArray) {
        int length = bArray.length;
        StringBuilder sb = new StringBuilder(length);
        String sTemp;
        for (byte b : bArray) {
            sTemp = Integer.toHexString(0xFF & b);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    private static byte[] singleHexStringToBytes(String hexString) {
        if (hexString == null || hexString.length() == 0 || hexString.length() > 8) {
            throw new IllegalArgumentException();
        }
        int anInt = Integer.parseInt(hexString, 16);
        return intToBytes(anInt);
    }

    private static byte[] intToBytes(int integer) {
        int byteNum = (40 - Integer.numberOfLeadingZeros(integer < 0 ? ~integer : integer)) / 8;
        byte[] byteArray = new byte[4];
        for (int n = 0; n < byteNum; n++) {
            byteArray[3 - n] = (byte) (integer >>> (n * 8));
        }
        return byteArray;
    }

    private static String toUnicodeFormal(char aChar) {
        String unicode = Integer.toHexString(aChar);
        if (unicode.length() <= 2) {
            unicode = "00" + unicode;
        }
        String str = unicode.toUpperCase();
        String sbStr = obtainUnicodeBySoftBank(str);
        if (sbStr == null || sbStr.length() == 0) {
            unicode = "\\u" + str;
        } else {
            unicode = sbStr;
        }
        return unicode;
    }

    private static boolean hasMultiCharacter(String str, boolean containsCJK, boolean containEmoji) {
        if (str == null || "".equals(str.trim())) {
            return false;
        }
        int cpCount = str.codePointCount(0, str.length());
        int firCodeIndex = str.offsetByCodePoints(0, 0);
        int lstCodeIndex = str.offsetByCodePoints(0, cpCount - 1);
        for (int index = firCodeIndex; index <= lstCodeIndex; index++) {
            int codePoint = str.codePointAt(index);
            if (containsCJK && isCJKCharacter(codePoint)) {
                return true;
            }
            if (containEmoji && isEmojiCharacter(codePoint)) {
                return true;
            }
        }
        return false;
    }

}
