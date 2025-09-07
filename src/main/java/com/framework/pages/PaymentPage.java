package com.framework.pages;
import com.microsoft.playwright.Page;
public class PaymentPage {
    private final Page page; public PaymentPage(Page page){ this.page = page; }
    public void payWithCard(String card,String exp,String cvv){
        page.fill("input[name='cardNumber']", card); page.fill("input[name='expiry']", exp);
        page.fill("input[name='cvv']", cvv); page.click("#payNow"); 
    }
}
