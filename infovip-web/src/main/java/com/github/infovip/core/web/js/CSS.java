/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.core.web.js;

import com.github.infovip.core.web.exceptions.UnsupportedTypeException;

/**
 *
 * @author attila
 */
public class CSS extends BasicType<CSS.CSSType> {

    public enum CSSType {
        TEXT_CSS("text/css"),;

        private final String type;

        private CSSType(String s) {
            type = s;
        }

        public boolean equalsType(String otherType) {
            return (otherType == null) ? false : type.equals(otherType);
        }

        /**
         *
         * @param text
         * @return
         * @throws UnsupportedTypeException
         */
        public static CSSType value(String text) throws UnsupportedTypeException {
            for (CSSType t : CSSType.values()) {
                if (t.toString().equalsIgnoreCase(text)) {
                    return t;
                }
            }
            throw new UnsupportedTypeException(text);
        }

        @Override
        public String toString() {
            return type;
        }
    }

    public enum CSSMedia {
        ALL("all"),
        BRAILLE("braille"),
        EMBOSSED("embossed"),
        HANDHELD("handheld"),
        PRINT("print"),
        PROJECTION("projection"),
        SCREEN("screen"),
        SPEECH("speech"),
        TTY("tty"),
        TV("tv");

        private final String media;

        private CSSMedia(String v) {
            this.media = v;
        }

        public boolean equalsType(String otherType) {
            return (otherType == null) ? false : media.equals(otherType);
        }

        /**
         *
         * @param text
         * @return
         * @throws UnsupportedTypeException
         */
        public static CSSMedia value(String text) throws UnsupportedTypeException {
            for (CSSMedia t : CSSMedia.values()) {
                if (t.toString().equalsIgnoreCase(text)) {
                    return t;
                }
            }
            throw new UnsupportedTypeException(text);
        }

        @Override
        public String toString() {
            return this.media;
        }

    }

     public enum CSSREL {
        ALTERNATE("alternate"),
        AUTHOR("author"),
        HELP("help"),
        LICENSE("license"),
        NEXT("next"),
        PREFETCH("prefetch"),
        PREV("prev"),
        SEARCH("search"),
        STYLESHEET("stylesheet");

        private final String rel;

        private CSSREL(String v) {
            this.rel = v;
        }

        public boolean equalsType(String otherType) {
            return (otherType == null) ? false : rel.equals(otherType);
        }

        /**
         *
         * @param text
         * @return
         * @throws UnsupportedTypeException
         */
        public static CSSREL value(String text) throws UnsupportedTypeException {
            for (CSSREL t : CSSREL.values()) {
                if (t.toString().equalsIgnoreCase(text)) {
                    return t;
                }
            }
            throw new UnsupportedTypeException(text);
        }

        @Override
        public String toString() {
            return this.rel;
        }

    }
    
    /**
     * Rel tag of the css
     */
    private String rel;

    /**
     * Media tag of the css
     */
    private CSSMedia media;

    public CSS(String src, String type,String rel) throws UnsupportedTypeException {
        super(src, CSSType.value(type));
        this.media = null;
        this.rel = rel;
    }

    public CSS(String src, String type, String media,String rel) throws UnsupportedTypeException {
        super(src, CSSType.value(type));
        this.media = CSSMedia.value(media);
        this.rel = rel;
    }

    public CSS(String src, CSSType type,CSSREL rel) throws UnsupportedTypeException {
        super(src, type);
        this.media = null;
        this.rel = rel.toString();
    }

    public CSS(String src, CSSType type, CSSMedia media,CSSREL rel) throws UnsupportedTypeException {
        super(src, type);
        this.media = media;
        this.rel = rel.toString();
    }

    public CSS setMedia(CSSMedia media) {
        this.media = media;
        return this;
    }

    public CSSMedia getMedia() {
        return media;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    @Override
    public String toString() {
        return String.format("<link rel='%s' href='%s' type='%s' media='%s' />", rel, src, type, media == null ? "" : media);
    }
}
