package com.framework.mocks;
import com.microsoft.playwright.Page; import com.microsoft.playwright.Route;
import org.json.JSONArray; import org.json.JSONObject;
import java.nio.file.Files; import java.nio.file.Paths;
public class MockingUtil {
    public static void applyMocks(Page page, String configPath) {
        try { String cfg = new String(Files.readAllBytes(Paths.get(configPath))); JSONObject root = new JSONObject(cfg); JSONArray arr = root.optJSONArray("flightMocks");
            page.route("**/api/flights/search", route -> {
                String post = route.request().postData();
                if (post==null) { route.resume(); return; }
                if (arr!=null) {
                    for (int i=0;i<arr.length();i++) {
                        JSONObject m = arr.getJSONObject(i);
                        if (post.contains(m.getString("from")) && post.contains(m.getString("to")) && post.contains(m.getString("date"))) {
                            String mf = m.getString("mockFile");
                            try { String body = new String(Files.readAllBytes(Paths.get(mf))); route.fulfill(new Route.FulfillOptions().setContentType("application/json").setBody(body)); return; } catch(Exception ex){ ex.printStackTrace(); }
                        }
                    }
                }
                route.resume();
            });
        } catch(Exception e){ throw new RuntimeException(e); }
    }
}
