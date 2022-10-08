package hackathon.vtb.backend.controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/backend/api")
public class AuthorizeController {
    @RequestMapping(value="hello", method = RequestMethod.GET)
    ResponseEntity<String> hello() throws IOException {
        String hello = "Hello world!";
        System.out.println("hello world!");
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJSdFFRcVN1X3hTeURMc1pIRS1Ba1ZsczYwa21mandaSlhNVi1lYlVfcmdnIn0.eyJleHAiOjE2NjUyNDMwNzcsImlhdCI6MTY2NTI0Mjc3NywianRpIjoiMzg1ZmU1YWItN2EwOC00MzhjLTk3OTctYmFkNmFjZGM0YzIyIiwiaXNzIjoiaHR0cHM6Ly9hdXRoLmJhbmtpbmdhcGkucnUvYXV0aC9yZWFsbXMva3ViZXJuZXRlcyIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI5YTg0YmJmOS0wMjllLTRmMjMtYjIxNC03YTlhNWI5OTVlYzgiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ0ZWFtOCIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsImRlZmF1bHQtcm9sZXMta3ViZXJuZXRlcyJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsImNsaWVudElkIjoidGVhbTgiLCJjbGllbnRIb3N0IjoiMTI3LjAuMC4xIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic2VydmljZS1hY2NvdW50LXRlYW04IiwiY2xpZW50QWRkcmVzcyI6IjEyNy4wLjAuMSJ9.kzLoaLBn5jCT7tNaYxeusNyl0g9McVCr8nzgt3qHYZG0cQg-OI9ag8F2sbBnWBlsc8otYRWsnNrDpHKfe9RszdWJPpeiKE2MnYIv2TiA33EVnSn56zWDzoS9ZNnQFCLN-ABeV51w3246nZq4dQFzDfIsc2Sd4ka4jT3SXz47BGy21HL7fYDkcgUtTK3zt7adFJpWg-hZ2JFPPebB3PsGTXQ3C-XZswFGGDHF23CGNODXfw3JdWx_5ovQODZrOy9IgefoQAdh7PCaTjPkMGQDYzkQmEjPDYNF7z22g3iYI2nc1N2OUtLbAP_JJIs72j8-GKMcnKRESYKbh9lymMzpjQ";
        Request request = new Request.Builder()
                .url("https://hackaton.bankingapi.ru/api/vtbid/v1/oauth2/authorize?scope=kiludtihh&redirect_uri=http://google.com&state=ID&client_id=team8&response_type=code")
                .get()
                .addHeader("Authorization", "Bearer "+token)
                .addHeader("accept", "application/json")
                .build();
        Response response = new OkHttpClient().newCall(request).execute();
        System.out.println(response.body().string());
        return new ResponseEntity<>(hello, HttpStatus.OK);
    }
}
