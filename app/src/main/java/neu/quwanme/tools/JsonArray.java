package neu.quwanme.tools;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 * Created by Lonie233 on 2016/4/7.
 */
public class JsonArray extends neu.quwanme.tools.JsonElement{

        public static Gson gson = new Gson();
        com.google.gson.JsonArray ja;

        public JsonArray() {
            ja = new com.google.gson.JsonArray();
        }

        public JsonArray(com.google.gson.JsonArray jarray) {
            if (jarray == null) {
                ja = new com.google.gson.JsonArray();
            } else {
                ja = jarray;
            }
        }

        public JsonArray(String s) {
            ja = gson.fromJson(s, com.google.gson.JsonArray.class);
        }

        public int length() {
            return ja.size();
        }

        public int getInt(int i) {
            return ja.get(i).getAsInt();
        }

        // don't use it!
        public Object opt(int i) {
            try {
                JsonElement je = ja.get(i);
                if (je.isJsonPrimitive()) {
                    JsonPrimitive jp = je.getAsJsonPrimitive();
                    if (jp.isString()) {
                        return jp.getAsString();
                    } else if (jp.isBoolean()) {
                        return jp.getAsBoolean();
                    } else if (jp.isNumber()) {
                        return jp.getAsNumber();
                    }
                    return je.getAsJsonPrimitive();
                } else if (je.isJsonObject()) {
                    return je.getAsJsonObject();
                } else if (je.isJsonArray()) {
                    return je.getAsJsonArray();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public String getString(int i) {
            return ja.get(i).getAsString();
        }

        public String optString(int i) {
            try {
                return getString(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        public JsonObject getJSONObject(int i) {
            return new JsonObject(ja.get(i).getAsJsonObject());
        }

        public JsonObject optJSONObject(int i) {
            try {
                return getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new JsonObject();
        }

        public JsonArray getJSONArray(int i) {
            return new JsonArray(ja.get(i).getAsJsonArray());
        }

        public void put(JsonObject jo) {
            ja.add(jo.job);
        }


        public String toString(int i) {
            return toString();
        }

        public String toString() {
            return ja.toString();
        }

        public String optString(String s) {
            return null;
        }

}
