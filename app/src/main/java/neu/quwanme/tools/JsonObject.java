package neu.quwanme.tools;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Lonie233 on 2016/4/7.
 */
public class JsonObject extends neu.quwanme.tools.JsonElement{

    public static Gson gson = new Gson();
    public com.google.gson.JsonObject job;

    public JsonObject() {
        job = new com.google.gson.JsonObject();
    }

    public JsonObject(com.google.gson.JsonObject jo) {
        if (jo == null) {
            job = new com.google.gson.JsonObject();
        } else {
            job = jo;
        }
    }

    public JsonObject(String s) {
        com.google.gson.JsonObject jo = gson.fromJson(s, com.google.gson.JsonObject.class);
        if (jo != null) {
            job = jo;
        }
    }

    public int getInt(String s) {
        return job.get(s).getAsInt();
    }

    public int optInt(String s) {
        return optInt(s, 0);
    }

    public int optInt(String s, int d) {
        int i = d;
        if (has(s)) {
            try {
                i = getInt(s);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("GSON parse error on: ", s);
            }
        }
        return i;
    }

    public long getLong(String s) {
        return job.get(s).getAsLong();
    }

    public long optLong(String s) {
        try {
            return getLong(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public double getDouble(String s) {
        return job.get(s).getAsDouble();
    }

    public double optDouble(String s) {
        return optDouble(s, 0);
    }

    public double optDouble(String s, double d) {
        try {
            return getDouble(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public String getString(String s) {
        JsonElement je = job.get(s);

        if (je.isJsonPrimitive()) {
            return je.getAsString();
        } else {
            return je.toString();
        }
    }

    public String optString(String s) {
        return optString(s, "");
    }

    public String optString(String s, String d) {
        String ss = d;
        if (has(s)) {
            try {
                ss = getString(s);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("GSON parse error on: ", s);
            }
        }
        return ss;
    }

    public boolean getBoolean(String s) {
        return job.get(s).getAsBoolean();
    }

    public boolean optBoolean(String s) {
        return optBoolean(s, false);
    }

    public boolean optBoolean(String s, boolean bb) {
        if (has(s)) {
            try {
                return getBoolean(s);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("GSON parse error on: ", s);
            }
        }
        return bb;
    }

    public boolean has(String s) {
        return job.has(s);
    }

    public JsonObject optJSONObject(String s) {
        if (has(s)) {
            try {
                return getJSONObject(s);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("GSON parse error on: ", s);
            }
        }
        return null;
    }

    // don't use it!
    public Object opt(String s) {
        if (!has(s)) {
            return null;
        }
        try {
            JsonElement je = job.get(s);
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

    public JsonObject getJSONObject(String s) {
        return new JsonObject(job.get(s).getAsJsonObject());
    }

    public neu.quwanme.tools.JsonElement getJSONObjectOrArray(String s) {
        JsonElement je = job.get(s);
        if (je.isJsonObject()) {
            return new JsonObject(je.getAsJsonObject());
        } else if (je.isJsonArray()) {
            return new JsonArray(je.getAsJsonArray());
        } else {
            return null;
        }
    }

    public neu.quwanme.tools.JsonElement optJSONObjectOrArray(String s) {
        try {
            return getJSONObjectOrArray(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JsonArray optJSONArray(String s) {
        try {
            return getJSONArray(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonArray();
    }

    public JsonArray getJSONArray(String s) {
        return new JsonArray(job.get(s).getAsJsonArray());
    }

    public void put(String s, JsonObject o) {
        job.add(s, o.job);
    }

    public void put(String s, JsonArray o) {
        job.add(s, o.ja);
    }

    public void put(String s, String o) {
        try {
            job.add(s, new JsonPrimitive(o));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void put(String s, long o) {
        job.add(s, new JsonPrimitive(o));
    }

    public void put(String s, int o) {
        job.add(s, new JsonPrimitive(o));
    }

    public void accumulate(String s, JsonArray a) {
        JsonArray jarr = optJSONArray(s);
        jarr.ja.addAll(a.ja);
        put(s, jarr);
    }

    public Iterator keys() {
        LinkedList<String> list = new LinkedList<>();
        for (Map.Entry<String, JsonElement> en : job.entrySet()) {
            list.add(en.getKey());
        }
        return list.iterator();
    }

    public String toString(int i) {
        return toString();
    }

    @Override
    public String toString() {
        return job.toString();
    }
}
