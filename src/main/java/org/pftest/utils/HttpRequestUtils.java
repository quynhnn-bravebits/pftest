package org.pftest.utils;

import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.DeserializationFeature;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.openqa.selenium.remote.http.HttpMethod;
import org.json.*;
import org.pftest.report.AllureManager;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.pftest.keywords.WebUI.getJsExecutor;

public class HttpRequestUtils {

    public static <T> String classToJsonString(Class<T> instance) {
        Map<String, Object> properties = new HashMap<>();
        Field[] fields = instance.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // Make private fields accessible
            properties.put(field.getName(), field.getType().getSimpleName());
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(properties);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T sendRequest(Class<T> type, String url, HttpMethod method) throws JsonProcessingException {
        // send request
        String script = "var callback = arguments[arguments.length - 1];" +
                "(async () => {" +
                "  const token = await shopify.idToken();" +
                "  const response = await fetch(arguments[0], {" +
                "       method: arguments[1]," +
                "       headers: {" +
                "           'Authorization': 'Bearer ' + token" +
                "       }" +
                "   });" +
                "  const data = await response.text();" +
                "  callback(data);" +
                "})().catch(callback);";

        String res = (String) getJsExecutor().executeAsyncScript(script, url, method.name());
        System.out.println("====================================");
        System.out.println("url: " + url);
        System.out.println(res);
        System.out.println("====================================");

        JSONObject obj = new JSONObject(res);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        AllureManager.saveTextLog("Expected:\n" + classToJsonString(type));
        AllureManager.saveTextLog("Actual:\n" + obj.toString(4));

        return objectMapper.readValue(obj.toString(), type);
    }

    public static <T, S> T sendRequestWithBody(Class<T> type ,String url, HttpMethod method, Class<S> data) throws JsonProcessingException {
        // send request
        String script = "var callback = arguments[arguments.length - 1];" +
                "(async () => {" +
                "  const response = await fetch(arguments[0], {" +
                "       method: arguments[1]" +
                "   });" +
                "  const data = await response.text();" +
                "  callback(data);" +
                "})().catch(callback);";

        String res = (String) getJsExecutor().executeAsyncScript(script, url, method.name());
        JSONObject obj = new JSONObject(res);
        System.out.println(res);
        System.out.println("====================================");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        return objectMapper.readValue(obj.toString(), type);
    }
}

