package com.mtaparenka.mortygallery.metadata;

public enum JFIFTag {
    MAKE(0x010f, Type.ASCII),
    MODEL(0x0110, Type.ASCII),
    DATE_TIME(0x0132, Type.ASCII),
    ARTIST(0x013b, Type.ASCII),
    IMAGE_DESCRIPTION(0x010e, Type.ASCII);
    //TODO more tags

    public final int address;
    public final Type type;

    JFIFTag(int address, Type type) {
        this.address = address;
        this.type = type;
    }
}
