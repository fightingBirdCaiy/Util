package util.learn.caiy.com.script;

/**
 * Created by yongc on 2018/3/5.
 */

public class MiscClassTest {

    /**
     * 字符串 替换 最后一个匹配项
     * @param text
     * @param regex
     * @param replacement
     * @return
     */
    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }

    public static void main(String[] args){
        String sourceStr = "jumeimall://page/alldetail?itemid=ht1499411322p3160352&type=global_pop"
                + "&source=toutiao"
                + "&sell_label=xxx"
                + "&source=toutiao"
                + "&selltype=xxx&sellparams=xxx&backurl=__back_url__";
        System.out.println(replaceLast(sourceStr, "source=toutiao", "link_source=toutiao"));
    }
}
