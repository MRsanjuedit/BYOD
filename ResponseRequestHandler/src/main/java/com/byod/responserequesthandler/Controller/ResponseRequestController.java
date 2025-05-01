package com.byod.responserequesthandler.Controller;


import com.byod.responserequesthandler.Service.ResponseRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication/{uuid}/{api}")
public class ResponseRequestController {
    private final ResponseRequestService responseRequestService;
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
    @PostMapping("/perform-encryption")
    public ResponseEntity<String> doEncryption(@PathVariable String uuid, @PathVariable String api, @RequestBody String data) {
        return new ResponseEntity<>(responseRequestService.doEncryption(uuid, api, data) , HttpStatus.OK);
    }
    @PostMapping("/perform-decryption")
    public ResponseEntity<String> doDecryption(@PathVariable String uuid, @PathVariable String api, @RequestBody String data) {
        return new ResponseEntity<>(responseRequestService.doDecryption(uuid, api, data) , HttpStatus.OK);
    }

}
