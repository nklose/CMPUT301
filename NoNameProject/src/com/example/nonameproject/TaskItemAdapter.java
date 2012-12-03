package com.example.nonameproject;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class TaskItemAdapter implements JsonSerializer<TaskItem>, JsonDeserializer<TaskItem>{
	private static final String CLASSNAME = "CLASSNAME";
	private static final String INSTANCE = "INSTANCE";
	
	public JsonElement serialize(TaskItem src, Type typeOfSrc,
			JsonSerializationContext context) {
			
		JsonObject retValue = new JsonObject();
		String className = src.getClass().getCanonicalName();
		retValue.addProperty(CLASSNAME, className);
		JsonElement elem = context.serialize(src);
		retValue.add(INSTANCE, elem);
		return retValue;
	}
		
	public TaskItem deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		Class<?> classType = classType(json);
		JsonObject jsonObject = json.getAsJsonObject();
		return context.deserialize(jsonObject.get(INSTANCE), classType);
	}

	private Class<?> classType(JsonElement json) {
		JsonObject jsonObject = json.getAsJsonObject();
		JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
		String className = prim.getAsString();
		Class<?> classType = null;
		try {
			classType = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new JsonParseException(e.getMessage());
		}
		return classType;
	}
}