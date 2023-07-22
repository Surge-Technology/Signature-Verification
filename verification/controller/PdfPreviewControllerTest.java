package com.gen.eChannel.verification.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PdfPreviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("PDF Preview Test")
    public void shouldPreviewPdf() throws Exception {
        // given - precondition or setup
        byte[] content = "PDF Content".getBytes();
        MockMultipartFile file = new MockMultipartFile("file", "test.pdf", "application/pdf", content);

        // when and then - action and behaviour that we are going to test
        mockMvc.perform(multipart("/api/pdf/preview")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(content().bytes(content));
    }

    @Test
    @DisplayName("PDF Preview Fail Test")
    public void shouldFailPreviewPdf() throws Exception {
        // given - precondition or setup
        byte[] content = new byte[0]; // empty file content
        MockMultipartFile file = new MockMultipartFile("file", "test.pdf", "application/pdf", content);

        // when and then - action and behaviour that we are going to test
        mockMvc.perform(multipart("/api/pdf/preview")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }
}

