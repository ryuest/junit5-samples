package com.whgtf.sportsbook.restApi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import spark.Request;
import spark.ResponseTransformer;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by juri on 25/11/2016.
 */
public class JsonUtil {
    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }


}
