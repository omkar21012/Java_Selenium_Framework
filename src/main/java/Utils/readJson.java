package Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class readJson {
    private static final String CONFIG_PATH = "src/config/properties.json";

    public static JSONObject getConfigByIndex(int index) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(CONFIG_PATH)));
            JSONArray configs = new JSONArray(content);
            return configs.getJSONObject(index);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read config at index " + index, e);
        }
    }

    public static String getValueByKey(String key) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(CONFIG_PATH)));
            JSONArray configs = new JSONArray(content);
            JSONObject firstObj = configs.getJSONObject(0); // first object
            return firstObj.optString(key, null);           // returns null if key not found
        } catch (Exception e) {
            throw new RuntimeException("Failed to read key: " + key, e);
        }
    }
}
