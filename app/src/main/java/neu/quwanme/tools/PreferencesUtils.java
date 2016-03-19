//package neu.quwanme.tools;
//
//import android.annotation.TargetApi;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.os.Build;
//import android.preference.PreferenceManager;
//import android.util.Base64;
//
//import com.tuan800.framework.develop.LogUtil;
//import com.tuan800.tao800.Tao800Application;
//import com.tuan800.tao800.share.utils.Tao800Util;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.util.Set;
//
///**
// * Created with IntelliJ IDEA.
// * User: qikai
// * Date: 13-3-7
// * Time: 下午11:24
// * To change this template use File | Settings | File Templates.
// */
//public class PreferencesUtils {
//    private static Context c;
//    private static String DEFAULT_SP_NAME = "_preferences";
//    static {
//        try {
//            c = Tao800Application.getInstance().createPackageContext("com.tuan800.tao800", Context.CONTEXT_IGNORE_SECURITY);
//        } catch (PackageManager.NameNotFoundException e) {
//            c = null;
//        }
//    }
//
//    /**
//     * 为了保证保存的数据文件名一致，建议spName命名采用"_name"的形式来命名
//     * @param spName
//     * @return
//     */
//    private static SharedPreferences getSP(String spName){
//        if (c!=null){
//            if (!Tao800Util.isNull(spName)){
//                if (Tao800Application.getInstance().isWebProcess()){
//                    return c.getSharedPreferences("com.tuan800.tao800" + spName, Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE | Context.MODE_MULTI_PROCESS);
//                }else{
//                    return c.getSharedPreferences("com.tuan800.tao800" + spName, Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
//                }
//            }else {
//                return PreferenceManager.getDefaultSharedPreferences(Tao800Application.getInstance());
//            }
//        }else {
//            return PreferenceManager.getDefaultSharedPreferences(Tao800Application.getInstance());
//        }
//    }
//
//    public static void putInteger(String spName,String name, int value) {
//        if (!Tao800Util.isNull(spName)){
//            getSP(spName).edit().putInt(name, value).commit();
//        }else {
//            putInteger(name,value);
//        }
//    }
//
//    public static void putInteger(String name, int value) {
//        getSP(DEFAULT_SP_NAME).edit().putInt(name, value).commit();
//    }
//
//    public static int getInteger(String spName,String name) {
//        if (!Tao800Util.isNull(spName)){
//            return getSP(spName).getInt(name, -1);
//        }else {
//            return getInteger(name);
//        }
//    }
//
//    public static int getInteger(String name) {
//        return getSP(DEFAULT_SP_NAME).getInt(name, -1);
//    }
//
//    public static void putString(String spName,String name, String value) {
//        if (!Tao800Util.isNull(spName)){
//            getSP(spName).edit().putString(name, value).commit();
//        }else {
//            putString(name,value);
//        }
//    }
//
//    public static void putString(String name, String value) {
//        getSP(DEFAULT_SP_NAME).edit().putString(name, value).commit();
//    }
//
//    public static String getString(String spName,String name){
//        if (!Tao800Util.isNull(spName)){
//            return getSP(spName).getString(name,"");
//        }else {
//            return getString(name,"");
//        }
//    }
//
//    public static String getString(String name) {
//        return getSP(DEFAULT_SP_NAME).getString(name, "");
//    }
//
//    /**
//     * by qz
//     *
//     * @param name
//     * @return
//     */
//    public static String getStringDefaultNull(String name) {
//        return getSP(DEFAULT_SP_NAME).getString(name, null);
//    }
//
//    public static void putBoolean(String spName,String name,boolean flag){
//        if (!Tao800Util.isNull(spName)){
//            getSP(spName).edit().putBoolean(name,flag).commit();
//        }else {
//            putBoolean(name,flag);
//        }
//    }
//
//    public static void putBoolean(String name, boolean flag) {
//        getSP(DEFAULT_SP_NAME).edit().putBoolean(name, flag).commit();
//    }
//
//    public static boolean getBoolean(String spName,String name){
//        if (!Tao800Util.isNull(spName)){
//            return getSP(spName).getBoolean(name,false);
//        }else {
//            return getBoolean(name);
//        }
//    }
//
//    public static boolean getBoolean(String name) {
//        return getSP(DEFAULT_SP_NAME).getBoolean(name, false);
//    }
//
//    public static boolean getBoolean(String spName,String name,boolean defaultVaule){
//        if (!Tao800Util.isNull(spName)){
//            return getSP(spName).getBoolean(name,defaultVaule);
//        }else {
//            return getBoolean(name,defaultVaule);
//        }
//    }
//
//    public static boolean getBoolean(String name, boolean defaultValue) {
//        return getSP(DEFAULT_SP_NAME).getBoolean(name, defaultValue);
//    }
//
//    public static long getLong(String spName,String name){
//        if (!Tao800Util.isNull(spName)){
//            return getSP(spName).getLong(name,0l);
//        }else {
//            return getLong(name);
//        }
//    }
//
//    public static long getLong(String name) {
//        return getSP(DEFAULT_SP_NAME).getLong(name, 0l);
//    }
//
//    public static void putLong(String spName,String name,long value){
//        if (!Tao800Util.isNull(spName)){
//            getSP(spName).edit().putLong(name,value).commit();
//        }else {
//            putLong(name,value);
//        }
//    }
//
//    public static void putLong(String name, long value) {
//        getSP(DEFAULT_SP_NAME).edit().putLong(name, value).commit();
//    }
//
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public static void putSet(String spName,String name, Set<String> set) {
//        if (!Tao800Util.isNull(spName)){
//            getSP(spName).edit().putStringSet(name,set).commit();
//        }else {
//            putSet(name,set);
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public static void putSet(String name, Set<String> set) {
//        getSP(DEFAULT_SP_NAME).edit().putStringSet(name, set).commit();
//    }
//
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public static Set<String> getSet(String spName,String name) {
//        if (!Tao800Util.isNull(spName)){
//            return getSP(spName).getStringSet(name,null);
//        }else {
//            return getSet(name);
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public static Set<String> getSet(String name) {
//        return getSP(DEFAULT_SP_NAME).getStringSet(name, null);
//    }
//
//    public static void clear(String spName){
//        if (!Tao800Util.isNull(spName)){
//            getSP(spName).edit().clear().commit();
//        }else {
//            clear();
//        }
//    }
//
//    public static void clear() {
//        getSP(DEFAULT_SP_NAME).edit().clear().commit();
//    }
//
//    public static void remove(String spName,String name){
//        if (!Tao800Util.isNull(spName)){
//            getSP(spName).edit().remove(name).commit();
//        }else {
//            remove(name);
//        }
//    }
//
//    public static void remove(String name) {
//        getSP(DEFAULT_SP_NAME).edit().remove(name).commit();
//    }
//
//    public static void storeObject(String spName,Object oo,String key){
//        if (!Tao800Util.isNull(spName)){
//            String stream = "";
//            ByteArrayOutputStream bao = null;
//            ObjectOutputStream oos = null;
//            try {
//                bao = new ByteArrayOutputStream();
//                oos = new ObjectOutputStream(bao);
//                oos.writeObject(oo);
//                oos.flush();
//                stream = Base64.encodeToString(bao.toByteArray(),Base64.DEFAULT);
//                putString(spName,key,stream);
//            }catch (Exception e){
//                LogUtil.w(e);
//            }finally {
//                try {
//                    if (oos!=null){
//                        oos.close();
//                    }
//                    if (bao!=null){
//                        bao.close();
//                    }
//                }catch (IOException e){
//                    LogUtil.w(e);
//                }
//            }
//        }else {
//            storeObject(oo,key);
//        }
//    }
//    public static void storeObject(Object oo, String Key) {
//        String stream = "";
//        ByteArrayOutputStream bao = null;
//        ObjectOutputStream oos = null;
//        try {
//            bao = new ByteArrayOutputStream();
//            oos = new ObjectOutputStream(bao);
//            oos.writeObject(oo);
//            oos.flush();
//            stream = Base64.encodeToString(bao.toByteArray(), Base64.DEFAULT);
//            putString(Key, stream);
//        } catch (Exception e) {
//            LogUtil.w(e);
//        }finally {
//            try {
//                if (oos!=null){
//                    oos.close();
//                }
//                if (bao!=null){
//                    bao.close();
//                }
//            }catch (IOException e){
//                LogUtil.w(e);
//            }
//        }
//    }
//
//    public static Object parseObject(String spName,String key){
//        if (!Tao800Util.isNull(spName)){
//            ByteArrayInputStream bis = null;
//            ObjectInputStream ois = null;
//            try {
//                String data = getString(spName,key);
//                if (Tao800Util.isNull(data))return null;
//                bis = new ByteArrayInputStream(Base64.decode(data,Base64.DEFAULT));
//                ois = new ObjectInputStream(bis);
//                Object object = ois.readObject();
//                return object;
//            }catch (Exception e){
//                LogUtil.w(e);
//            }finally {
//                try {
//                    if (bis!=null){
//                        bis.close();
//                    }
//                    if (ois!=null){
//                        ois.close();
//                    }
//                }catch (IOException e){
//                    LogUtil.w(e);
//                }
//            }
//            return null;
//        }else {
//            return paserObject(key);
//        }
//    }
//
//    public static Object paserObject(String Key) {
//        LogUtil.d("---------load self in thread paserStream()-------> ");
//        ByteArrayInputStream bis = null;
//        ObjectInputStream ois = null;
//        try {
//            String data = getString(Key);
//            LogUtil.d("wo cao ni mei a parserStream()-------> " + data);
//            if (data == null || data.equals("")) return null;
//            bis = new ByteArrayInputStream(Base64.decode(data, Base64.DEFAULT));
//            ois = new ObjectInputStream(bis);
//            Object object = ois.readObject();
//            return object;
//        } catch (Exception e) {
//            LogUtil.w(e);
//        } finally {
//            try {
//                if (bis != null) {
//                    bis.close();
//                }
//                if (ois != null) {
//                    ois.close();
//                }
//            } catch (IOException e) {
//                LogUtil.w(e);
//            }
//        }
//        return null;
//    }
//}
