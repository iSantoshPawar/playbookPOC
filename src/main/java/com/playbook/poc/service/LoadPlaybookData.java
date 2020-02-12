package com.playbook.poc.service;

import com.playbook.poc.utility.ExcelHelperPOI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

@Service
public class LoadPlaybookData {

    @Autowired
    ExcelHelperPOI excelHelperPOI;

    public ResponseEntity<ByteArrayResource> getPlaybookExcelByteArrayOutputStream() {
        try {
            excelHelperPOI.getPlaybookConfigurationFromTable(1);
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
}
