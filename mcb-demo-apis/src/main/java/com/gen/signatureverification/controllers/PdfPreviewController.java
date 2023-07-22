package com.gen.signatureverification.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins = "http://localhost:4200")
public class PdfPreviewController {
	
	@PostMapping("/preview")
    public ResponseEntity<byte[]> previewPdf(@RequestPart("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            // Handle the case where no file is uploaded
            return ResponseEntity.badRequest().build();
        }

        // Create a temporary file to store the uploaded content
        File tempFile = File.createTempFile("uploaded", file.getOriginalFilename());
        file.transferTo(tempFile);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        // Read the file content into a byte array
        byte[] pdfBytes = Files.readAllBytes(tempFile.toPath());

        // Delete the temporary file
        tempFile.delete();

        // Return the PDF content as a response
        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}







