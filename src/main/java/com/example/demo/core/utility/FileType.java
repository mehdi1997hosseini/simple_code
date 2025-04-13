package com.example.demo.core.utility;

public enum FileType {
    JPG("jpg"), JPEG("jpeg"), GIF("gif"), TEXT("text"), PDF("pdf"), DOC("doc"), DOCX("docx"), XLS("xls"), XLSX("xlsx");

    private final String mimeType;

    FileType(String mimeType) {
        this.mimeType = mimeType;
    }
    public String getMimeType() {
        return mimeType;
    }


}
