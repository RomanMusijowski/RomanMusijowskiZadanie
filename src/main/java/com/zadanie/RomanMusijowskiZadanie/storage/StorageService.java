package com.zadanie.RomanMusijowskiZadanie.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	String store(MultipartFile file, String fileName);

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();

	void deleteFile(String path);

}
