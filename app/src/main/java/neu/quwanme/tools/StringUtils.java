package neu.quwanme.tools;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lonie233 on 2016/4/11.
 */
public class StringUtils {
    public static String join(Collection<String> s, String delimiter) {
        if (s.size() == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (String str : s) {
            sb.append(str).append(delimiter);
        }
        if (sb.length() > 0)
            sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    public static String inputStreamToString(final InputStream stream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        return sb.toString();
    }

    public static String getFromStream(final InputStream stream) throws IOException {
        return inputStreamToString(stream);
    }
    /*
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        char[] buffer = new char[512];
        int read;
        while ((read = br.read(buffer, 0, buffer.length)) > 0) {
            sb.append(buffer, 0, read);
        }
        br.close();
        return sb.toString();
    }
*/

    public static Boolean isEmpty(String str) {
        return null == str || str.length() == 0 || "null".equals(str);
    }

    public static Boolean isEmptyTrim(String str) {
        return isEmpty(str) || str.trim().length() == 0;
    }

    public static boolean isNull(String str) {
        return isEmpty(str) || "null".equals(str);
    }

    public static String getValueOrDefault(String str, String defaultValue) {
        return isEmpty(str) ? defaultValue : str;
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static String fromBytes(byte[] bytes) {
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < bytes.length; offset++) {
            int i = bytes[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    public static JsonObject parseJSON(String json) {
        try {
            return new JsonObject(json);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonObject();
        }
    }

    public static Map<String, Object> parseHttpParamsToHash(String params) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] arr = params.split("&");
        for (String kv : arr) {
            if (kv.indexOf("=") > 0) {
                String[] kvArr = kv.split("=");
                map.put(kvArr[0], kvArr[1]);
            }
        }
        return map;
    }

    public static String simpleFormat(String str, Object... replacements) {
        String[] parts = str.split("%s");
        if (parts.length < 2) return str;
        StringBuilder sb = new StringBuilder();
        int rl = replacements.length;
        for (int i = 0; i < parts.length; i++) {
            sb.append(parts[i]);
            if (i < rl)
                sb.append(replacements[i]);
        }
        return sb.toString();
    }


    public static String getLeft(String chatParticipant, String participant) {

        if (StringUtils.isEmpty(chatParticipant)) return chatParticipant;
        String[] ss = chatParticipant.split(participant);
        if (ss.length >= 2)
            return ss[0];
        else return chatParticipant;
    }

    public static String getRight(String chatParticipant, String participant) {

        if (StringUtils.isEmpty(chatParticipant)) return chatParticipant;
        String[] ss = chatParticipant.split(participant);
        if (ss.length >= 2)
            return ss[1];
        else return chatParticipant;
    }

    public static boolean isServer(String jid) {
        return (jid != null) && (jid.startsWith("3_"));
    }

    public static String getFrist(String image_url_small) {
        if (isEmpty(image_url_small)) return image_url_small;

        return image_url_small;
    }

    public static boolean isEmpty(Object... objects) {
        return (objects == null || objects.length <= 0);
    }
}
