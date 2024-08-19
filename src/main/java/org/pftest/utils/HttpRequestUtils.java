package org.pftest.utils;

import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.DeserializationFeature;
import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.qameta.allure.internal.shadowed.jackson.databind.node.ObjectNode;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v125.network.Network;
import org.openqa.selenium.devtools.v125.network.model.RequestId;
import org.openqa.selenium.devtools.v125.network.model.Response;
import org.openqa.selenium.remote.http.HttpMethod;
import org.json.*;
import org.pftest.driver.DriverManager;
import org.pftest.report.AllureManager;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.pftest.keywords.WebUI.getJsExecutor;

public class HttpRequestUtils {

//    public static <T> T getNetworkDevtoolsResponse(Class<T> type, String url) throws JsonProcessingException {
//        // only work with Chrome
//        DevTools devTools = ((ChromeDriver) DriverManager.getDriver()).getDevTools();
//        devTools.createSession();
//        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
//
//        final RequestId[] requestId = new RequestId[1];
//        AtomicReference<String> res = new AtomicReference<>();
//
//        devTools.addListener(Network.responseReceived(), responseReceived -> {
//            Response response = responseReceived.getResponse();
//            requestId[0] = responseReceived.getRequestId();
//            if (response.getUrl().contains(url)) {
//                String responseBody = devTools.send(Network.getResponseBody(requestId[0])).getBody();
//                res.set(responseBody);
//            }
//        });
//
//        JSONObject obj = new JSONObject(res.get());
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        JsonNode jsonNode = objectMapper.readTree(res.get());
//        JsonNode outputNode = convertNestedJson(jsonNode);
//
//        String expected = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputNode);
//        AllureManager.saveTextLog("Actual:\n" + obj.toString(4));
//
//        T result = objectMapper.readValue(obj.toString(), type);
//
//        System.out.println(expected);
//        AllureManager.saveTextLog("Expected:\n" + expected);
//
//        return result;
//    }

    public static JsonNode convertNestedJson(JsonNode input) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode output = mapper.createObjectNode();
        input.fields().forEachRemaining(entry -> {
            String key = entry.getKey();
            JsonNode value = entry.getValue();
            if (value.isObject()) {
                output.set(key, convertNestedJson(value));
            } else if (value.isArray()) {
                output.put(key, "array");
            } else if (value.isBoolean()) {
                output.put(key, "boolean");
            } else if (value.isNumber()) {
                output.put(key, "number");
            } else {
                output.put(key, "string");
            }
        });
        return output;
    }

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

    public static String waitForGlobalAuthData() {
        String script = "var callback = arguments[arguments.length - 1];" +
                "(async () => {" +
                "  const checkAuthData = async () => {" +
                "    while (!window.globalAuth || !window.globalAuth.data) {" +
                "      await new Promise(resolve => setTimeout(resolve, 100));" +
                "    }" +
                "    return window.globalAuth.data;" +
                "  };" +
                "  const authData = await checkAuthData();" +
                "  callback(authData);" +
                "})().catch(callback);";
        String res = (String) getJsExecutor().executeAsyncScript(script);
        System.out.println(res);
        return res;
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

        Object res =  getJsExecutor().executeAsyncScript(script, url, method.name());
        System.out.println("====================================");
        System.out.println("url: " + url);
        System.out.println(res);
        System.out.println("====================================");

        JSONObject obj = new JSONObject(res);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode jsonNode = objectMapper.readTree((String) res);
        JsonNode outputNode = convertNestedJson(jsonNode);

        String expected = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputNode);
        AllureManager.saveTextLog("Actual:\n" + obj.toString(4));

        T result = objectMapper.readValue(obj.toString(), type);

        System.out.println(expected);
        AllureManager.saveTextLog("Expected:\n" + expected);

        return result;
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

