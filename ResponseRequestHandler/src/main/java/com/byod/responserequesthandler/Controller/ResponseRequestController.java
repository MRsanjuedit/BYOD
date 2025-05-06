package com.byod.responserequesthandler.Controller;


import com.byod.responserequesthandler.Security.SecurityMethodsImplement;
import com.byod.responserequesthandler.Service.ResponseRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication/{uuid}/{api}")
public class ResponseRequestController {
    private final ResponseRequestService responseRequestService;
     final String key = "12345678901234567890123456789012";
    ResponseRequestController(ResponseRequestService responseRequestService) {
        this.responseRequestService = responseRequestService;
    }

    @GetMapping("/test-connection")
    public ResponseEntity<String> testConnection(@PathVariable Long uuid, @PathVariable Long api) {
        return new ResponseEntity<>(responseRequestService.testConnection(uuid, api) , HttpStatus.OK);
    }
    @GetMapping("/isAuthenticated")
    public ResponseEntity<Boolean> isAuthenticated(@PathVariable Long uuid , @PathVariable Long api) {
        if(responseRequestService.isAuthenticated(uuid, api)) return new ResponseEntity<>(true , HttpStatus.OK);
        return new ResponseEntity<>(false , HttpStatus.NOT_FOUND);
    }
    @PostMapping("/encrypt")
    public ResponseEntity<String> doEncryption(@PathVariable Long uuid, @PathVariable Long api, @RequestBody String plainText) throws Exception {
        return new ResponseEntity<>(responseRequestService.doEncryption(uuid,api,plainText,key) , HttpStatus.OK);
    }
    @PostMapping("/decryt")
    public ResponseEntity<String> doDecryption(@PathVariable Long uuid, @PathVariable Long api, @RequestBody String cipherTextAndIv) {
        try {
            String decryptedText = responseRequestService.doDecryption(uuid,api,cipherTextAndIv,key);
            return ResponseEntity.ok(decryptedText);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error decrypting: " + e.getMessage());
        }
    }
}
