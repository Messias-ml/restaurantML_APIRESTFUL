package com.messimari.restaurantml.infrastructure.interfaces;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

public interface PhotoStorage {
    void store(NewPhoto newPhoto);

    public static String nameFile(Long id, String nameOriginalFile){
        return String.valueOf(id).concat(nameOriginalFile);
    }

    @Getter
    @Builder
    class NewPhoto {
        private InputStream fileInput;
        private String nameFile;
    }
}

