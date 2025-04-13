package com.example.demo.core.utility;

public class CoreUtil {
    /**
     * fileName -> by file name return type file from file
     * */
    public String getMimeType(String fileName) {
        String mimeType = "application/octet-stream";
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        mimeType = switch (extension) {
            case "pdf" -> "application/pdf";
            case "png" -> "image/png";
            case "jpg", "jpeg" -> "image/jpeg";
            case "txt" -> "text/plain";
            case "zip" -> "application/zip";
            case "docx" -> "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            default -> mimeType;
        };

        return mimeType;
    }


}
