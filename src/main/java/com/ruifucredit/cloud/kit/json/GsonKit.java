package com.ruifucredit.cloud.kit.json;

import com.google.gson.Gson;

public class GsonKit {

    public static final Gson gson = new Gson();

    public static <T> T fromJson(String json, Class<T> clazz) {

        return gson.fromJson(json, clazz);

    }

    public static String toJson(Object object) {

        return gson.toJson(object);

    }

}
