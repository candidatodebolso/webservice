package br.com.candidatodebolso.webservice.service.impl;

import br.com.candidatodebolso.webservice.exception.FileNotFoundException;
import br.com.candidatodebolso.webservice.exception.StorageException;
import br.com.candidatodebolso.webservice.service.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path directory;

    public StorageServiceImpl() {
        this.directory = Paths.get("data");
    }

    @Override
    public void init() {
        try {
            boolean directoryDoesNotExist = !Files.exists(directory) || !Files.isDirectory(directory);
            if (directoryDoesNotExist) {
                Files.createDirectory(directory);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String storage(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Falha em salval o arquivo vazio " + file.getOriginalFilename());
            }
            String filename = ZonedDateTime.now(ZoneOffset.UTC).toInstant().toString() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), directory.resolve(filename));
            return filename;
        } catch (IOException e) {
            throw new StorageException("Falha em salvar o arquivo " + file.getOriginalFilename());
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return directory.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            UrlResource resource = new UrlResource(file.toUri());
            boolean fileExists = resource.exists() || resource.isReadable();
            if (fileExists) {
                return resource;
            }
            throw new FileNotFoundException("Não foi possivel ler o arquivo " + filename);
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Não foi possivel ler o arquivo " + filename);
        }
    }

    @Override
    public void removeAll() {

    }
}
