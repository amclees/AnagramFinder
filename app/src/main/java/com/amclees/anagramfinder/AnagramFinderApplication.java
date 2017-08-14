package com.amclees.anagramfinder;

import android.app.Application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramFinderApplication extends Application {
    private static Map<String, List<String>> anagramMap;

    @Override
    public void onCreate() {
        super.onCreate();

        JSONReader jsonReader = new JSONReader(getResources(), R.raw.eng_map);
        jsonReader.readData();

        Map<String, List<String>> blueprintMap = new HashMap<>();
        anagramMap = jsonReader.createJSONObject(blueprintMap.getClass());
    }

    public Map<String, List<String>> getAnagramMap() {
        return anagramMap;
    }
}
