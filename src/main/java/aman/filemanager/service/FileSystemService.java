package aman.filemanager.service;

import aman.filemanager.data.FileSystemObject;
import aman.filemanager.exceptions.SystemExcpetion;

import java.io.FileNotFoundException;
import java.util.List;

public interface FileSystemService {

    FileSystemObject addOrUpdateDirectory(String directoryName, String parentDirectoryId) throws SystemExcpetion;

    FileSystemObject addOrUpdateFile(String fileName, String parentDirectoryId) throws FileNotFoundException;
    void delete(String id);

    List<FileSystemObject> find(String name);

    List<FileSystemObject> getAllChildren(String id);

    List<FileSystemObject> getAllDirectories();
}
