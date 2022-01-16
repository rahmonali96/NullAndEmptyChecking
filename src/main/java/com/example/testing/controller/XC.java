package com.example.testing.controller;

import com.example.testing.model.A;
import com.example.testing.model.Message;
import com.example.testing.model.XReq;
import com.example.testing.service.XS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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

    @SneakyThrows
    @PostMapping("/obj")
    public ResponseEntity<?> getObj(@RequestBody String a) {
        ObjectMapper mapper = new ObjectMapper();
        A a1 = mapper.readValue(a, A.class);
        return new ResponseEntity<>(xs.getFields(a1), HttpStatus.OK);
    }

    @PostMapping("/string")
    public ResponseEntity<?> getS(@RequestBody String s){
        xs.test(s);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
