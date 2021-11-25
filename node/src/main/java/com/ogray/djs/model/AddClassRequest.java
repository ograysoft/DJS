package com.ogray.djs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class AddClassRequest {
    @Setter
    @Getter
    String name;

    @Setter @Getter
    String data;
}
