package com.example.testing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message<T> {
    private String message;
    private T body;
}
