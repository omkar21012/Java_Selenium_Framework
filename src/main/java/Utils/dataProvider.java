package Utils;

import com.google.gson.Gson;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class dataProvider {


    @DataProvider(name = "loginData")
    public Object[][] getDataFromJson() throws Exception {
        // Path to your JSON file
        FileReader reader = new FileReader("src/config/properties.json");

        // Parse JSON into List of Maps
        Gson gson = new Gson();
        Type type = new com.google.gson.reflect.TypeToken<List<Map<String, String>>>() {}.getType();
        List<Map<String, String>> data = gson.fromJson(reader, type);

        // Convert List to Object[][] for TestNG
        Object[][] result = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i);
        }

        return result;
    }
}
