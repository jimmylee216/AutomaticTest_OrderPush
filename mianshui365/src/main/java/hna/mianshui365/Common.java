package hna.mianshui365;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 解析Json内容
 * 
 * @author Jimmy Lee
 * @version 1.0 2017/2/9
 * @return JsonValue 返回JsonString中JsonId对应的Value
 **/

public class Common {
    public static String getJsonValue(String JsonString, String JsonId) {
        String JsonValue = "";
        if (JsonString == null || JsonString.trim().length() < 1) {
            return null;
        }
        try {
            JSONObject obj1 = new JSONObject(JsonString);
            JsonValue = (String) obj1.getString(JsonId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return JsonValue;
    }
}
