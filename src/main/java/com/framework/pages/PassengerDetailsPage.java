package com.framework.pages;
import com.microsoft.playwright.Page;
public class PassengerDetailsPage {
    private final Page page; public PassengerDetailsPage(Page page){ this.page = page; }
    public void fillPassenger(String f,String l,String email,String phone){
        page.fill("input[name='firstName']", f); page.fill("input[name='lastName']", l);
        page.fill("input[type='email']", email); page.fill("input[type='tel']", phone); page.click("#continueBtn");
    }
}
