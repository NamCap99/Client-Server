package shares;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.File;
import java.io.IOException;

/**
 * is a class that converts from object to json, json to object, reads json from file, writes json to file
 */
public class MyObjectMapper {
    private static final ObjectMapper instance = new ObjectMapper();
    private static final SimpleModule module = new SimpleModule();

    static {
        instance.registerModule(module);
        instance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        instance.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        instance.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static ObjectMapper instance() {
        return instance;
    }

    public static String serialize(Object o) {
        try {
            return instance.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    public static void writeFile(File file, Object o) {
        try {
            instance.writeValue(file, o);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    public static <T> T deserialize(String json, Class<T> clazz) {
        try {
            return instance.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    public static <T> T deserialize(String json, TypeReference<T> reference) {
        try {
            return instance.readValue(json, reference);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    public static <T> T readFile(File file, TypeReference<T> reference) {
        try {
            return instance.readValue(file, reference);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
    public static <T> void addSerializer(Class<? extends T> type,
                                         com.fasterxml.jackson.databind.JsonSerializer<T> ser) {
        module.addSerializer(type, ser);
    }

    public static <T> void addDeserializer(Class<T> type,
                                           com.fasterxml.jackson.databind.JsonDeserializer<? extends T> deser) {
        module.addDeserializer(type, deser);
    }
}
