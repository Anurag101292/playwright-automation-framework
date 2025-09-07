package com.framework.decorators;
public class RetryHandler {
    public static <T> T runWithRetry(Retryable<T> action, int retries) throws Exception {
        int attempt=0; while(true){ try{ return action.run(); } catch(Exception e){ attempt++; if(attempt>retries) throw e; Thread.sleep(200); } }
    }
    public interface Retryable<T>{ T run() throws Exception; }
}
