package neu.quwanme.tools;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import neu.quwanme.bean.School;


/**
 * Created by Lonie233 on 2016/3/17.
 */
public class GSONTOOLS {

    /**
     * 获取实体类  简单键值对
     * @param gsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T>T getBean(String gsonString,Class<T> cls) {
        T t = null ;
        try{
            Gson gson = new Gson();
            t = gson.fromJson(gsonString,cls);
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (t == null) {
            LogUtil.e("ERROR","gson decode bean error !");
        }
        return t ;
    }

    /**
     * 获取list
     * @param jsonString
     * @param <T>
     * @return
     */
    public static <T> List<T> getList(String jsonString, Type type) {
         List<T> list = new ArrayList<T>();
         try {
                Gson gson = new Gson();
                 list = gson.fromJson(jsonString,type);
         } catch (Exception e) {
            e.printStackTrace();
         }

         if (list.isEmpty()){
             LogUtil.e("ERROR","gson list is empty");
         }

         return list;
    }

    /**
     * 获取复杂键值对
     * @param jsonString
     * @param <T>
     * @return
     */
    public static <T> Map<String,T> getMap(String jsonString, Type type){
        Map<String,T> map = new HashMap<>();
        try{
            Gson gson = new Gson();
            map = gson.fromJson(jsonString,type);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (map.isEmpty()){
            LogUtil.e("ERROR","gson map decode error");
        }
        return map ;
    }

    /**
     * 简单的类型转化
     * @param cls
     * @return
     */
    public static Type getType(Class cls){

       return  new TypeToken<ArrayList<School>>(){}.getType();
    }



}
