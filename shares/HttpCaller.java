package shares;

import lombok.NonNull;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * call api, including methods put, get, post, delete
 */
public class HttpCaller {
    private final CloseableHttpClient httpClient;

    public HttpCaller() {
        httpClient = HttpClientBuilder.create().build();
    }

    public String get(String api, Map<String, String> headers, Map<String, String> params, HttpHost proxy, int timeout) throws IOException {
        if (params != null) {
            api += "?" + getParamsString(params);
        }

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectTimeout(timeout);
        if (proxy != null) configBuilder.setProxy(proxy);

        HttpGet request = new HttpGet(api);
        request.setConfig(configBuilder.build());

        if (headers != null) headers.keySet().forEach(k -> request.addHeader(k, headers.get(k)));

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        }
    }

    private String putParams(String api, Map<String, String> headers, Map<String, String> params, HttpHost proxy, int timeout) throws IOException {
        if (params != null) {
            api += "?" + getParamsString(params);
        }

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectTimeout(timeout);
        if (proxy != null) configBuilder.setProxy(proxy);

        HttpPut request = new HttpPut(api);
        request.setConfig(configBuilder.build());

        if (headers != null) headers.keySet().forEach(k -> request.addHeader(k, headers.get(k)));

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        }
    }

    private String putBodyContent(String api, Map<String, String> headers, @NonNull Object bodyContent, HttpHost proxy, int timeout) throws IOException {
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectTimeout(timeout);
        if (proxy != null) configBuilder.setProxy(proxy);

        HttpPut request = new HttpPut(api);
        request.setConfig(configBuilder.build());

        if (headers != null) headers.keySet().forEach(k -> request.addHeader(k, headers.get(k)));

        org.apache.http.entity.ContentType contentType = bodyContent instanceof String
                ? org.apache.http.entity.ContentType.TEXT_PLAIN
                : org.apache.http.entity.ContentType.APPLICATION_JSON;
        request.setEntity(new StringEntity(bodyContent.toString(), contentType));

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        }
    }

    private String putJSONContent(String api, Map<String, String> headers, @NonNull JSONObject json, HttpHost proxy, int timeout) throws IOException {
        return putBodyContent(api, headers, json, proxy, timeout);
    }

    private String putTextContent(String api, Map<String, String> headers, @NonNull String text, HttpHost proxy, int timeout) throws IOException {
        return putBodyContent(api, headers, text, proxy, timeout);
    }

    private String putFormData(String api, Map<String, String> headers, Map<String, String> formData, HttpHost proxy, int timeout)
            throws IOException {
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectTimeout(timeout);
        if (proxy != null) configBuilder.setProxy(proxy);

        HttpPut request = new HttpPut(api);
        request.setConfig(configBuilder.build());

        List<NameValuePair> formDataParams = new ArrayList<>();
        formData.keySet().forEach(k ->
                formDataParams.add(new BasicNameValuePair(k, formData.get(k)))
        );

        request.setEntity(new UrlEncodedFormEntity(formDataParams, StandardCharsets.UTF_8));
        if (headers != null) headers.keySet().forEach(k -> request.addHeader(k, headers.get(k)));

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        }
    }

    private String postParams(String api, Map<String, String> headers, Map<String, String> params, HttpHost proxy, int timeout) throws IOException {
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectTimeout(timeout);
        if (proxy != null) configBuilder.setProxy(proxy);

        if (params != null) {
            api += "?" + getParamsString(params);
        }

        HttpPost request = new HttpPost(api);
        request.setConfig(configBuilder.build());

        if (headers != null) headers.keySet().forEach(k -> request.addHeader(k, headers.get(k)));

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        }
    }

    private String postBodyContent(String api, Map<String, String> headers, @NonNull Object bodyContent, int timeout)
            throws IOException {
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectTimeout(timeout);

        HttpPost request = new HttpPost(api);
        request.setConfig(configBuilder.build());

        if (headers != null) headers.keySet().forEach(k -> request.addHeader(k, headers.get(k)));

        org.apache.http.entity.ContentType contentType = bodyContent instanceof JSONObject
                ? org.apache.http.entity.ContentType.APPLICATION_JSON
                : org.apache.http.entity.ContentType.TEXT_PLAIN;
        request.setEntity(new StringEntity(bodyContent.toString(), contentType));

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        }
    }

    private String postJSONContent(String api, Map<String, String> headers, @NonNull JSONObject json, int timeout) throws IOException {
        return postBodyContent(api, headers, json, timeout);
    }

    private String postTextContent(String api, Map<String, String> headers, @NonNull String text, int timeout) throws IOException {
        return postBodyContent(api, headers, text, timeout);
    }

    private String postFormData(String api, Map<String, String> headers, Map<String, String> formData, HttpHost proxy, int timeout)
            throws IOException {
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectTimeout(timeout);
        if (proxy != null) configBuilder.setProxy(proxy);

        HttpPost request = new HttpPost(api);
        request.setConfig(configBuilder.build());

        List<NameValuePair> formDataParams = new ArrayList<>();
        formData.keySet().forEach(k ->
                formDataParams.add(new BasicNameValuePair(k, formData.get(k)))
        );

        request.setEntity(new UrlEncodedFormEntity(formDataParams, StandardCharsets.UTF_8));
        if (headers != null) headers.keySet().forEach(k -> request.addHeader(k, headers.get(k)));

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        }
    }

    private String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

    public String post(String api, Map<String, String> headers, Object data, HttpHost proxy, int timeout, String... type) throws Exception {
        // Default is post with params
        if (type == null || type.length < 1) {
            Map<String, String> params;
            if (data == null) params = null;
            else if (data instanceof Map) {
                //noinspection unchecked
                params = (Map<String, String>) data;
            } else throw new Exception("Expected type of data is instanceof Map");
            return postParams(api, headers, params, proxy, timeout);
        } else {
            // With specify ContentType
            switch (type[0]) {
                case ContentType.APPLICATION_JSON:
                    if (data instanceof JSONObject) {
                        JSONObject json = (JSONObject) data;
                        return postJSONContent(api, headers, json, timeout);
                    } else throw new Exception("Expected type of data is instanceof JSONObject");
                case ContentType.TEXT:
                    String text = (String) data;
                    return postTextContent(api, headers, text, timeout);
                case ContentType.FORM_DATA:
                    @SuppressWarnings("unchecked") Map<String, String> formData = (Map<String, String>) data;
                    return postFormData(api, headers, formData, proxy, timeout);
                default:
                    return postParams(api, headers, null, proxy, timeout);
            }
        }
    }

    public String put(String api, Map<String, String> headers, Object data, HttpHost proxy, int timeout, String... type) throws Exception {
        // Default is post with params
        if (type == null || type.length < 1) {
            Map<String, String> params;
            if (data == null) params = null;
            else if (data instanceof Map) {
                //noinspection unchecked
                params = (Map<String, String>) data;
            } else throw new Exception("Expected type of data is instanceof Map");
            return putParams(api, headers, params, proxy, timeout);
        } else {
            // With specify ContentType
            switch (type[0]) {
                case ContentType.APPLICATION_JSON:
                    if (data instanceof JSONObject) {
                        JSONObject json = (JSONObject) data;
                        return putJSONContent(api, headers, json, proxy, timeout);
                    } else throw new Exception("Expected type of data is instanceof JSONObject");
                case ContentType.TEXT:
                    String text = (String) data;
                    return putTextContent(api, headers, text, proxy, timeout);
                case ContentType.FORM_DATA:
                    @SuppressWarnings("unchecked") Map<String, String> formData = (Map<String, String>) data;
                    return putFormData(api, headers, formData, proxy, timeout);
                default:
                    return putParams(api, headers, null, proxy, timeout);
            }
        }
    }

    public String delete(String api, Map<String, String> headers, Map<String, String> params, HttpHost proxy, int timeout) throws IOException {
        if (params != null) api += "?" + getParamsString(params);

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectTimeout(timeout);
        if (proxy != null) configBuilder.setProxy(proxy);

        HttpDelete request = new HttpDelete(api);
        request.setConfig(configBuilder.build());

        if (headers != null) headers.keySet().forEach(k -> request.addHeader(k, headers.get(k)));

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        }
    }

    public static class ContentType {
        public static final String APPLICATION_JSON = "APPLICATION_JSON";
        public static final String FORM_DATA = "URL_ENCODED_FORM_DATA";
        public static final String TEXT = "TEXT";
    }
}
