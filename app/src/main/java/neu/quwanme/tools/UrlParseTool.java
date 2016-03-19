package neu.quwanme.tools;

import java.util.Map;

/**
 * Created by Lonie233 on 2016/3/18.
 */
public class UrlParseTool {

    /**
     * url拼接参数函数
     * @param url url
     * @param map 参数 名/值
     * @return
     */
    public static String parseParam(String url,Map<String,String> map) {

        String paramsStr ="";

        for (Map.Entry<String,String> entry : map.entrySet()) {
            paramsStr += entry.getKey()+"="+entry.getValue()+"&";
        }
            paramsStr = paramsStr.substring(0,paramsStr.length()-1);
        return url+"?"+paramsStr;
    }

    /**
     * url拼接函数
     * @param baseUrl url前半段
     * @param dis url目标action
     * @return
     */
    public static String parseUrl(String baseUrl,String dis){
        return baseUrl+dis;
    }

}
