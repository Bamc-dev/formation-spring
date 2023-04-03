package fr.dawan.training.interceptors;

import fr.dawan.training.dto.LogMsg;
import fr.dawan.training.exceptions.AuthenticateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value={AuthenticateException.class})
    protected ResponseEntity<?> handleAuthenticateException(Exception ex, WebRequest request){
        LogMsg m = new LogMsg();
        m.setCode(401);
        m.setLevel(LogMsg.LogLevel.ERROR);
        m.setType(LogMsg.LogType.EXCEPTION);
        m.setMessage(ex.getMessage());
        m.setPath(((ServletWebRequest)request).getRequest().getRequestURI());
        ex.printStackTrace();
        //affichage de la trace dans la console
//		StringWriter sw = new StringWriter();
//		ex.printStackTrace(new PrintWriter(sw));
//		m.setStackTrace(sw.toString());

        return handleExceptionInternal(ex, m, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }


    @ExceptionHandler(value={Exception.class})
    protected ResponseEntity<?> handleGenericException(Exception ex, WebRequest request){
        LogMsg m = new LogMsg();
        m.setCode(500);
        m.setLevel(LogMsg.LogLevel.ERROR);
        m.setType(LogMsg.LogType.EXCEPTION);
        m.setMessage(ex.getMessage());
        m.setPath(((ServletWebRequest)request).getRequest().getRequestURI());
        ex.printStackTrace(); //affichage de la trace dans la console
//		StringWriter sw = new StringWriter();
//		ex.printStackTrace(new PrintWriter(sw));
//		m.setStackTrace(sw.toString());

        return handleExceptionInternal(ex, m, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}

