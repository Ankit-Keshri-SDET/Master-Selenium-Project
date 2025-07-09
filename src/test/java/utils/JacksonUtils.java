package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import objects.BillingAddress;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {

    public static BillingAddress deserializeJson(InputStream filePath,
                                                 BillingAddress billingAddress) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(filePath, billingAddress.getClass());
    }
}
