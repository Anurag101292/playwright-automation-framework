package com.framework.utils;
import java.nio.file.Files; import java.nio.file.Paths; import org.json.JSONObject;
public class JsonUtil { public static JSONObject read(String path){ try{ return new JSONObject(new String(Files.readAllBytes(Paths.get(path)))); }catch(Exception e){ throw new RuntimeException(e);} }
}
