package com.framework.reporting;
import org.apache.logging.log4j.LogManager; import org.apache.logging.log4j.Logger;
public class LogManager { public static Logger getLogger(Class<?> cls){ return LogManager.getLogger(cls); } }
