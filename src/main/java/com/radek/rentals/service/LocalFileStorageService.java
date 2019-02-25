package com.radek.rentals.service;


import com.radek.rentals.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class LocalFileStorageService implements StorageService {

    private final Path fileStorageLocation;

    @Autowired
    public LocalFileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, this.fileStorageLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path filePath = fileStorageLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found");
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found " + filename, e);
        }
    }

    @Override
    public void deleteAll() {

    }
}
