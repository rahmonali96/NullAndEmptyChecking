package com.example.testing.service;

import com.example.testing.model.Message;
import com.example.testing.model.XReq;
import com.example.testing.repo.XR;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
@AllArgsConstructor
public class XS {
    private XR xr;
    @SneakyThrows
    public ResponseEntity<?> check(XReq xReq) {
        XReq xReqtrim = trim(xReq);
        Field[] fields = xReqtrim.getClass().getDeclaredFields();
        String message;
        for (Field field : fields) {
            field.setAccessible(true);
            Object object = field.get(xReq);
            if (object == null) {
                message = field.getName() + " is null";
                return new ResponseEntity<>(new Message<>(message, xReqtrim), HttpStatus.BAD_REQUEST);
            }else {
                if (object instanceof String){
                    String obj = (String) object;
                    if (obj.trim().isEmpty()){
                        message = field.getName() + " is empty";
                        return new ResponseEntity<>(new Message<>(message, xReqtrim), HttpStatus.BAD_REQUEST);
                    }
                }else if (object instanceof Integer){
                    int x = (Integer) object;
                    boolean b = x > 0 && x < 100;
                    if (!b) {
                        message = field.getName() + " should be greater than 0";
                        return new ResponseEntity<>(new Message<>(message, xReqtrim), HttpStatus.BAD_REQUEST);
                    }
                }

            }
        }
        String sql = String.format("insert into x(name,phone,age) values('%s','%s',%d)",
                xReqtrim.getName(), xReqtrim.getPhone(), xReqtrim.getAge());
        return new ResponseEntity<>(xr.save(sql), HttpStatus.OK);
    }

    public XReq trim(XReq xReq){
        XReq xReqTrim = new XReq();
        xReqTrim.setName(xReq.getName().trim());
        xReqTrim.setPhone(xReq.getPhone().trim());
        xReqTrim.setAge(xReq.getAge());
        return xReqTrim;
    }
}
