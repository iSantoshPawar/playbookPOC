package com.playbook.poc.controller;

import com.playbook.poc.service.LoadPlaybookData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

@RestController
@RequestMapping("/sara")
public class PlaybookController {
    @Autowired
    LoadPlaybookData loadPlaybookData;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = "/get-greeting", method = RequestMethod.GET)
    public String greeting() { /** *   @LocaleContextHolder.getLocale() *  Return the Locale associated with the given user context,if any, or the system default Locale otherwise. *  This is effectively a replacement for Locale.getDefault(), able to optionally respect a user-level Locale setting. */
        return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
    }

    @RequestMapping(value = "/get-greeting-name", method = RequestMethod.GET)
    public String greetingWithName() {
        String[] params = new String[]{"Ikhiloya", "today"};
        return messageSource.getMessage("good.morning.name", params, LocaleContextHolder.getLocale());
    }

    @GetMapping(value = "/download")
    public ResponseEntity<ByteArrayResource> downloadTemplate() throws Exception {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            File file = new File("Playbook.xlsx");
            FileInputStream fis = new FileInputStream(file);
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                stream.write(buf, 0, readNum);
            }
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "force-download"));
            header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Playbook.xlsx");
            return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),
                    header, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/playbookExcel")
    public ResponseEntity<ByteArrayResource> providePlaybookExcel() throws Exception {
        return loadPlaybookData.getPlaybookExcelByteArrayOutputStream();
    }

}