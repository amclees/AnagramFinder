package com.amclees.anagramfinder;

import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONReader {
   private boolean dataRead = false;
   private String string;
   private Resources resources;
   private int resourceId;
   private static final String LOGTAG = JSONReader.class.getSimpleName();

   public JSONReader(Resources resources, int resourceId) {
       this.resources = resources;
       this.resourceId = resourceId;
   }

   public void readData() {
       InputStream resourceReader = resources.openRawResource(resourceId);
       try {
           BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
           String line = reader.readLine();
           string = line;
           dataRead = true;
       } catch (Exception e) {
           Log.e(LOGTAG, "Unhandled exception reading JSON", e);
       } finally {
           try {
               resourceReader.close();
           } catch (Exception e) {
               Log.e(LOGTAG, "Unhandled exception while closing reader used for reading JSON", e);
           }
       }
   }

    public <T> T createJSONObject(Class<T> type) {
        if (!dataRead) {
            return null;
        }
        return (new GsonBuilder().create()).fromJson(string, type);
    }
}
