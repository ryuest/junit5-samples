package com.whgtf.sportsbook.main.util;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Created by javierg on 28/06/2016.
 */
public class TranslationUtils {

    public static final Map<String,String> LANGUAGE_URL_MAP = ImmutableMap.<String,String>builder()
            .put("German", "de-de")
            .put("English", "en-gb")
            .put("Japanese","ja-jp")
            .put("Greek","el-gr")
            .put("Russian","ru-ru")
            .put("Swedish","sv-se")
            .build();
}
