package com.messimari.restaurantml.domain.service.implementation;

import com.messimari.restaurantml.infrastructure.interfaces.PhotoStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class PhotoStorageImple implements PhotoStorage {

    @Value("${storage_file_project}")
    private String directoryPhoto;

    @Override
    public void store(NewPhoto newPhoto) {
        Path filePath = getFilePath(newPhoto.getNameFile());
        try {
            Files.deleteIfExists(filePath);
            FileCopyUtils.copy(newPhoto.getFileInput(), Files.newOutputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path getFilePath(String nameFile) {
        Path directory = Path.of(directoryPhoto);
        return directory.resolve(nameFile);
    }

    @Override
    public InputStream recupereFile(String nameFile) throws IOException {
        Path filePath = getFilePath(nameFile);
        return Files.newInputStream(filePath);
    }

    @Override
    public void removeFile(String nameFile) {
        Path filePath = getFilePath(nameFile);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
