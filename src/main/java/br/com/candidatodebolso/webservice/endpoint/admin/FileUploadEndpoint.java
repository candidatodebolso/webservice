package br.com.candidatodebolso.webservice.endpoint.admin;

import br.com.candidatodebolso.webservice.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadEndpoint {

    private final StorageService storageService;

    @Autowired
    public FileUploadEndpoint(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("admin/upload")
    public ResponseEntity<?> upload(@RequestBody MultipartFile file) {
        boolean validFormat = !file.getContentType().equalsIgnoreCase(MediaType.IMAGE_JPEG_VALUE) ^ !file.getContentType().equalsIgnoreCase("image/jpg");
        if (validFormat) {
            throw new InvalidMediaTypeException(file.getContentType(), "Formato de aquivo invalido");
        }
        String filename = storageService.storage(file);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body("{\"filename\":\"}" + filename + "\"}");
    }

    @GetMapping("files/{filename:.+}")
    public ResponseEntity<?> loadFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
    }
}
