package org.kxml.parser;

import org.kxml.Xml;

/**
 * Stub class for legacy kxml compatibility
 */
public class ParseEvent {

    public int getType() {
        return Xml.END_DOCUMENT;
    }

    public String getName() {
        return "";
    }

    public String getText() {
        return "";
    }

    public String getAttribute(String name) {
        return "";
    }

    public int getAttributeCount() {
        return 0;
    }

    public String getAttributeName(int index) {
        return "";
    }

    public String getAttributeValue(int index) {
        return "";
    }
}
