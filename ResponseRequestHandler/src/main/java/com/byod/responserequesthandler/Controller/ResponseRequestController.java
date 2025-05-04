package com.byod.responserequesthandler.Controller;


import com.byod.responserequesthandler.Security.SecurityMethodsImplement;
import com.byod.responserequesthandler.Service.ResponseRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/authentication/{uuid}/{api}")
public class ResponseRequestController {
    private final ResponseRequestService responseRequestService;
     final String key = "12345678901234567890123456789012";
    ResponseRequestController(ResponseRequestService responseRequestService) {
        this.responseRequestService = responseRequestService;
    }

    @GetMapping("/test-connection")
    public ResponseEntity<String> testConnection(@PathVariable String uuid, @PathVariable String api) {
        return new ResponseEntity<>(responseRequestService.testConnection(uuid, api) , HttpStatus.OK);
    }
    @GetMapping("/isAuthenticated")
    public ResponseEntity<Boolean> isAuthenticated(@PathVariable String uuid , @PathVariable String api) {
        if(responseRequestService.isAuthenticated(uuid, api)) return new ResponseEntity<>(true , HttpStatus.OK);
        return new ResponseEntity<>(false , HttpStatus.NOT_FOUND);
    }
    @PostMapping("/encrypt")
    public ResponseEntity<String> doEncryption(@PathVariable String uuid, @PathVariable String api, @RequestBody String plainText) throws Exception {
        return new ResponseEntity<>(responseRequestService.doEncryption(uuid,api,plainText,key) , HttpStatus.OK);
    }
    @PostMapping("/decryt")
    public ResponseEntity<String> doDecryption(@PathVariable String uuid, @PathVariable String api, @RequestBody String cipherTextAndIv) {
        try {
            String decryptedText = responseRequestService.doDecryption(uuid,api,cipherTextAndIv,key);
            return ResponseEntity.ok(decryptedText);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error decrypting: " + e.getMessage());
        }
    }

    @PostMapping("/test-encrypt")
    public String encrypt(@RequestBody String plainText) throws Exception {
        return SecurityMethodsImplement.encryptTextCombined(plainText, key);
    }

    @PostMapping("/test-decrypt")
    public ResponseEntity<String> decrypt(@RequestBody String cipherTextAndIv) {
        try {
            String decryptedText = SecurityMethodsImplement.decryptTextCombined(cipherTextAndIv, key);
            return ResponseEntity.ok(decryptedText);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error decrypting: " + e.getMessage());
        }
    }


}
