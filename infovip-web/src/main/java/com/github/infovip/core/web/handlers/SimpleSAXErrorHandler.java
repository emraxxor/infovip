package com.github.infovip.core.web.handlers;

import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleSAXErrorHandler implements ErrorHandler {
    
	private Logger logger = Logger.getLogger(SimpleSAXErrorHandler.class);
	
	public void warning(SAXParseException e) throws SAXException {
        System.out.println(e.getMessage());
        logger.warn(e);
    }

    public void error(SAXParseException e) throws SAXException {
        System.out.println(e.getMessage());
        logger.error(e);
    }

    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println(e.getMessage());
        logger.fatal(e);
    }
}
