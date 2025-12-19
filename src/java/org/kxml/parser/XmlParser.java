package org.kxml.parser;

import java.io.Reader;
import java.io.IOException;
import org.kxml.Xml;

/**
 * Stub class for legacy kxml compatibility
 */
public class XmlParser {

    public XmlParser(Reader reader) throws IOException {
        // Stub constructor - throws IOException for compatibility
    }

    public ParseEvent read() throws IOException {
        return new ParseEvent();
    }

    public ParseEvent peek() throws IOException {
        return new ParseEvent();
    }

    public void skip() throws IOException {
        // Stub method
    }
}
