package fr.dawan.training.controller;

import fr.dawan.training.exceptions.AuthenticateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/test")
public class TestController {

    final static Logger logger = LoggerFactory.getLogger("INFO");
    final static Logger otherLogs = LoggerFactory.getLogger("accessLogger");

    //url + méthode HTTP
    //@RequestMapping(method = RequestMethod.GET, value = "/test/m1")

    /**
     *
     * @param pageOpt
     * @return
     */
    @GetMapping(value = {"/m1","/m1/{page}"}, produces = "text/plain")
    public String m1(@PathVariable(value = "page", required = false)Optional<Integer> pageOpt)
    {
        if(pageOpt.isPresent())
        {
            return "m1 : "+pageOpt.get();
        }else
            return "m1";
    }

    /**
     *
     * @param page
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/m2")
    public ResponseEntity<String> m2(@RequestParam("page") int page)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED).
                contentType(MediaType.TEXT_PLAIN)
                .body("m2 crée "+page);
    }

    /**
     *
     * @param message
     * @param userAgent
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/m3", consumes = "text/plain", produces = "text/plain")
    public String m3(@RequestBody String message, @RequestHeader("User-Agent") String userAgent)
    {
        return "m3 : "+ message + " : userAgent : "+userAgent;
    }

    @GetMapping(value = "/exc1")
    public String testException() throws Exception
    {
        throw new AuthenticateException("Erreur : blablabla");
    }
    @GetMapping(value = "/exc2")
    public String otherException() throws Exception
    {
        throw new Exception("Test");
    }
}
