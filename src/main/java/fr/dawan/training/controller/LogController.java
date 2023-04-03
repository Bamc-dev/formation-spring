package fr.dawan.training.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.training.dto.LogMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/logs")
public class LogController {
    public static Logger myRootLogger = LoggerFactory.getLogger(TestController.class);
    private static Logger myLogger2 = LoggerFactory.getLogger("accessLogger");
    @Value("${storage.folder}")
    private String storageFolder;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = "/{filename}", produces = "application/octet-stream")
    public ResponseEntity<Resource> getLogFile(@PathVariable("filename") String filename) throws Exception
    {
        File f = new File(storageFolder+"/"+filename);
        ByteArrayResource ressource = new ByteArrayResource(Files.readAllBytes(Paths.get(f.getAbsolutePath())));

        HttpHeaders headers = new HttpHeaders();

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(f.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(ressource);
    }

    @PostMapping(consumes =  "application/json")
    public ResponseEntity<Void> postLog(@RequestBody LogMsg msg) throws Exception{
        String logStr = objectMapper.writeValueAsString(msg);
        switch (msg.getLevel()) {
            case INFO:
                myRootLogger.info(logStr);
                break;
            case WARN:
                myRootLogger.warn(logStr);
                break;
            case ERROR:
                myRootLogger.error(logStr);
                break;
            default:
                break;

        }
        return ResponseEntity.ok().build();
    }
}
