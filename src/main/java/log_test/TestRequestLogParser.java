package log_test;

/**
 * @(#)TestRequestLogParser.java, 2007-11-24.
 *
 * Copyright 2007 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import junit.framework.TestCase;

/**
 *
 * @author river
 *
 */
public class TestRequestLogParser extends TestCase {

    public void test() throws Exception {
        RequestLogParser parser = new RequestLogParser();
        for (int i = 0; i <= 10000000; i++) {
            parser.parse("API\tkeyfrom=toolbar.94" +
                    "\tp=http://www.tedahr.com/recruit/index.asp?field=006&keyword=&kwdtype=pos&period=30&disprec=20&ordertype=pub_time&Page=20" +
                    "\treq=rank" +
                    "\t[ip=125.36.195.128]" +
                    "\t[userid=-1136209709@125.36.191.91]" +
                    "\t[useragent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)]" +
                    "\t[referer=\\[NULL\\]]");
            assertEquals("bad category", "API", parser.getCategory());
            assertEquals("bad userid", "-1136209709@125.36.191.91", parser.getProperties().getProperty("userid"));
            assertEquals("bad referer", "[NULL]", parser.getProperties().getProperty("referer"));
            assertEquals("bad useragent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)", parser.getProperties().getProperty("useragent"));

            parser.parse("A");
            assertEquals("bad category", "A", parser.getCategory());
            assertEquals("bad value", null, parser.getProperties().getProperty("userid"));
        }
    }
}

