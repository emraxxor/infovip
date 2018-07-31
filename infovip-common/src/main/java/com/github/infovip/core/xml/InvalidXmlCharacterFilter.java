package com.github.infovip.core.xml;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

/**
 * 
 * @author Attila Barna
 * @category infovip.core.data.xml
 */
public class InvalidXmlCharacterFilter extends FilterReader {

	public InvalidXmlCharacterFilter(Reader in) {
		super(in);
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		char current;
		int read = super.read(cbuf, off, len);
		if (read == -1)
			return read;

		for (int i = off; i < off + read; i++) {
			current = cbuf[i];
			if ( ( (current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF))
					|| ((current >= 0xE000) && (current <= 0xFFFD))
					|| ((current >= 0x10000) && (current <= 0x10FFFF)) )) {
				cbuf[i] = cbuf[i];
			} else {
				cbuf[i] = '?';
			}
		}
		return read;
	}
}
