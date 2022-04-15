package com.zahaand.webapp.util;

import com.google.gson.*;

import java.lang.reflect.Type;

public class JsonSectionAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
    private static final String CLASS_NAME = "CLASSNAME";
    private static final String INSTANCE = "INSTANCE";

    @Override
    public T deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = element.getAsJsonObject();
        JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonObject.get(CLASS_NAME);
        String className = jsonPrimitive.getAsString();
        try {
            Class<?> clazz = Class.forName(className);
            return context.deserialize(jsonObject.get(INSTANCE), clazz);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }

    @Override
    public JsonElement serialize(T section, Type type, JsonSerializationContext context) {
        JsonObject value = new JsonObject();
        value.addProperty(CLASS_NAME, section.getClass().getName());
        JsonElement jsonElement = context.serialize(section);
        value.add(INSTANCE, jsonElement);
        return value;
    }
}

