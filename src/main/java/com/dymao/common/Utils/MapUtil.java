package com.dymao.common.Utils;

import java.util.*;

/**
 * @author Mervin
 * @Description:
 * @date 2018-04-24 00:21
 */
public class MapUtil {
    /**
     * 根据map获取map包含的key,返回set
     *
     * @param map
     * @return
     */
    public static Set<String> getKeySetByMap(Map<String, String> map) {

        Set<String> keySet = new HashSet<String>();
        keySet.addAll(map.keySet());

        return keySet;
    }

    /**
     * 根据key的set返回key的list
     *
     * @param set
     * @return
     */
    public static List<String> getKeyListBySet(Set<String> set) {
        List<String> keyList = new ArrayList<String>();
        keyList.addAll(set);
        return keyList;
    }

    /**
     * 根据map返回key和value的list
     *
     * @param map
     * @param isKey
     *            true为key,false为value
     * @return
     */
    public static List<String> getListByMap(Map<String, String> map, boolean isKey) {
        List<String> list = new ArrayList<String>();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            if (isKey) {
                list.add(key);
            } else {
                list.add(map.get(key));
            }
        }
        return list;
    }
}
