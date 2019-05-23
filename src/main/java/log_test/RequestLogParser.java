package log_test;

/**
 * @(#)RequestLogParser.java, 2007-11-24.
 *
 * Copyright 2007 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
import java.text.ParseException;
import java.util.Properties;

/**
 * Parse request log into properties.
 * @author river
 */
public class RequestLogParser {
    private static final char TAB = '\t';
    private static final char EQ = '=';

    private String line;
    private String category;
    private Properties values = new Properties();
    private String message;
    private int propStart = 0;
    private boolean propParsed = false;
    private boolean escape = true;

    /**
     *
     * @param escape : getProperties()时，是否对\r \n \t 进行转译
     */
    public RequestLogParser(Boolean escape) {
        setEscape(escape);
    }
    public RequestLogParser() { }

    public void parse(String line) throws ParseException {
        this.line = line;
        parseCategory();
        propParsed = false;
    }

    public String getCategory() {
        return category;
    }

    public Properties getProperties() throws ParseException {
        if (!propParsed) {
            if (escape) parseProperties();
            else parseProperties(false);
            propParsed = true;
        }
        return values;
    }

    private int skipSpace(String line, int from) {
        int len = line.length();
        int pos = from;
        while (pos < len && line.charAt(pos) == TAB)
            pos ++;
        return (pos==len) ? -1 : pos;
    }

    private String escape(String s) {
        return escape(s, true);
    }
    private String escape(String s, boolean doit) {
        if (!doit) return s;

        StringBuilder buffer = null;
        int len = s.length();
        int start = 0;
        for (int i=0; i<len; i++) {
            char ch = s.charAt(i);
            if (ch == '\\' && i+1 < len) {
                if (buffer == null) {
                    buffer = new StringBuilder();
                }
                if (i>start) {
                    buffer.append(s, start, i);
                }
                i++;
                ch = s.charAt(i);
                switch(ch) {
                    case 't' : buffer.append('\t'); break;
                    case 'n' : buffer.append('\n'); break;
                    case 'r' : buffer.append('\r'); break;
                    default:
                        buffer.append(ch);
                }
                start = i+1;
            }
        }
        if (buffer == null) return s;
        else {
            if (start < len) {
                buffer.append(s, start, len);
            }
            return buffer.toString();
        }
    }

    private void parseCategory() throws ParseException {
        int from = skipSpace(line, 0);
        if (from < 0) {
            throw new ParseException(line, 0);
        }

        int pos = line.indexOf(TAB, from);
        if (pos < 0) {
            category = line.substring(from);
            propStart = -1;
        } else {
            category = line.substring(from, pos);
            propStart = skipSpace(line, pos);
        }
        category = escape(category);
    }

    private void parseProperties() throws ParseException {
        parseProperties(true);
    }

    private void parseProperties(Boolean escape) throws ParseException {
        values.clear();
        if (propStart < 0) return;
        while (propStart >=0) {
            int pos = line.indexOf(TAB, propStart);
            if (pos < 0) {
                pos = line.length();
            }


            int eqPos = line.indexOf(EQ, propStart);
            if (eqPos >= pos || eqPos < 0) {
                // parse [[message]]
                try {
                    if (line.charAt(propStart)=='[' && line.charAt(propStart+1)=='['
                            && line.charAt(pos-1)==']' && line.charAt(pos-2)==']') {
                        message = escape(line.substring(propStart+2, pos-2), escape);
                    } else {
                        throw new ParseException(line, propStart);
                    }
                } catch (IndexOutOfBoundsException e) {
                    new ParseException(line, propStart).printStackTrace();
                }

                propStart = skipSpace(line, pos);
                continue ;
            }

            String key, value;
            if (line.charAt(propStart)=='[' && line.charAt(pos-1)==']') {
                key = line.substring(propStart+1, eqPos);
                value = line.substring(eqPos+1, pos-1);
            } else {
                key = line.substring(propStart, eqPos);
                value = line.substring(eqPos+1, pos);
            }
            key = escape(key, escape);
            if (!values.containsKey(key)) {
                value = escape(value, escape);
                values.setProperty(key, value);
            }
            propStart = skipSpace(line, pos);
        }
    }

    /**
     * @param escape the escape to set
     */
    public void setEscape(boolean escape) {
        this.escape = escape;
    }
    /**
     * @return the message
     * @throws ParseException
     */
    public String getMessage() throws ParseException {
        getProperties();

        return message;
    }

}
