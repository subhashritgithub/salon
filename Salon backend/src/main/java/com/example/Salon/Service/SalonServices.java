package com.example.Salon.Service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Service
public class SalonServices {
    public InputStream getResource(String path, String fileName)throws FileNotFoundException {
        String fullpath=path+ File.separator+fileName;
        InputStream file=new FileInputStream(fullpath);
        return file;
    }
}
