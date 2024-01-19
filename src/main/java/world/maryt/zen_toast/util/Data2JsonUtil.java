package world.maryt.zen_toast.util;

import crafttweaker.api.data.IData;
import java.util.Map;

public class Data2JsonUtil {
    public static String data2Json(IData data) {
        StringBuilder result = new StringBuilder();
        result.append("{");
        Map<String, IData> map = data.asMap();
        for (String key: map.keySet()) {
            result.append("\"").append(key).append("\"").append(":").append(map.get(key)).append(",");
        }
        result.deleteCharAt(result.length() - 1);
        result.append("}");
        return result.toString();
    }
}
