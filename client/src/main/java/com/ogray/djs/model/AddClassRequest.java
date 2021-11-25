package com.ogray.djs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Base64;

@AllArgsConstructor
public class AddClassRequest {
    @Setter
    @Getter
    String name;

    @Setter @Getter
    String data;

    public AddClassRequest(String name, byte[] data) {
        this.name = name;
        this.data = Base64.getEncoder().encodeToString(data);
    }
}
