package org.Gonzalo.Manzano.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {
    // Read json to string
    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") +
                "\\src\\test\\java\\org\\Gonzalo\\Manzano\\data\\PurchaseOrder.json"), StandardCharsets.UTF_8);

        // String to hash map
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
    }
}
