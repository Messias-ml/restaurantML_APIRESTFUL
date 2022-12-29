package com.messimari.restaurantml.domain.service.implementation;

import com.messimari.restaurantml.infrastructure.interfaces.PhotoStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class PhotoStorageImple implements PhotoStorage {

    @Value("${storage_file_project}")
    private String locale;

    @Override
    public void store(NewPhoto newPhoto) {
        Path pathLocale = Path.of(locale);
        Path filePath = pathLocale.resolve(newPhoto.getNameFile());
        try {
            if (filePath != null && Files.exists(filePath)){
                Files.deleteIfExists(filePath);
            }
            FileCopyUtils.copy(newPhoto.getFileInput(), Files.newOutputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
