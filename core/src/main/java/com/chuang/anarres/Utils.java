package com.chuang.anarres;

import com.chuang.tauceti.support.exception.BusinessException;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Ath
 */
public class Utils {

    private static HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
    static {
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    public static String converterToFirstSpell(String chines) {
        StringBuilder pinyinName = new StringBuilder();
        char[] nameChar = chines.toCharArray();
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    pinyinName.append(PinyinHelper.toHanyuPinyinStringArray(
                            nameChar[i], defaultFormat)[0].charAt(0));
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName.append(nameChar[i]);
            }
        }
        return pinyinName.toString();
    }


    /**
     *
     */
    @SafeVarargs
    public static <T> T eachGet(T elseDefault, Supplier<T>... getters) {
        for(Supplier<T> getter : getters) {
            T value = getter.get();
            if(null != value) {
                return value;
            }
        }

        return elseDefault;
    }

    public static <T> T runUntilRetrunIsNull(Supplier<T> getter, Function<T, T>... runner) {
        T t = getter.get();
        if(null != t) {
            for (Function<T, T> run : runner) {
                t = run.apply(t);
                if(null == t) {
                    return null;
                }
            }
        }

        return null;
    }

    public static <T> void full(List<T> list, int count) {
        if(list.size() >= count || list.isEmpty()) {
            return;
        }

        int need = count - list.size();
        if(need >= list.size()) {

            list.addAll(list);
            full(list, count);
        } else {
            list.addAll(list.subList(0, need));
        }

    }

    public static List<Integer> idx(int cur, int max, int size, boolean addSelf) {
        if(addSelf) {
            size++;
        }

        if(size > max) {
            throw new BusinessException(-1, "索引不足");
        }

        int start = Math.max(cur - (size / 2), 0);
        int end = start + size + (addSelf ? -2 : 0);

        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end ; i++) {
            if(!addSelf && cur == i) {
                continue;
            }
            list.add(i);
        }

        return list;
    }


}
