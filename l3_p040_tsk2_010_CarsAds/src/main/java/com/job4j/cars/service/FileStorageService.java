package com.job4j.cars.service;


import com.job4j.cars.entity.FileMetaData;
import com.job4j.cars.exception.FileStorageException;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;

public interface FileStorageService {

    FileMetaData store(MultipartFile file, int theAdId) throws FileStorageException;
    List<Path> getAllFiles();
    FileMetaData getFile(String fileName) throws FileNotFoundException;
}
