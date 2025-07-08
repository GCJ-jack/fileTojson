package com.pig.design.fileExport;

public interface FileExport {

    void export2Db(String filepath);

    FileType supportedFileType();
}
