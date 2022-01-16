package com.example.testing.controller;

import com.example.testing.model.A;
import com.example.testing.model.Message;
import com.example.testing.model.XReq;
import com.example.testing.service.XS;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class XC {
    private XS xs;
    @PostMapping("/x")
    public ResponseEntity<?> getX(@RequestBody XReq xReq) {
        return xs.check(xReq);
    }

    @PostMapping("/obj")
    public ResponseEntity<?> getObj(@RequestBody A a) {
        return new ResponseEntity<>(xs.getFields(a), HttpStatus.OK);
    }
}
