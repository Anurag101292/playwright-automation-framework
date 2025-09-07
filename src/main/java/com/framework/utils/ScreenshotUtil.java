package com.framework.utils;
import com.microsoft.playwright.Page; import java.nio.file.Paths;
public class ScreenshotUtil { public static void capture(Page page,String name){ try{ page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("target/screenshots/"+name+".png"))); }catch(Exception e){ e.printStackTrace(); } } }
