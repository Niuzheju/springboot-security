package com.niuzj.springbootsecurity.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class Permission {

    private String resourceId;

    private String resourceName;

    private Map<String, String> privileges = new HashMap<>();


}
