package com.radek.rentals.controller;


import com.radek.rentals.service.LocalFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping(path = "/files")
public class FileController {

    private LocalFileStorageService storageService;

    @Autowired
    public FileController(LocalFileStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> handleFileDownload(@PathVariable String filename) {
        Resource resource = storageService.loadAsResource(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

}
