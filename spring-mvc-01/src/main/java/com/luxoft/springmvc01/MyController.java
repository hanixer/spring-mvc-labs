package com.luxoft.springmvc01;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/lower")
    ResponseEntity<String> lower() {
        return ResponseEntity.ok("Morning");
    }
    
}
