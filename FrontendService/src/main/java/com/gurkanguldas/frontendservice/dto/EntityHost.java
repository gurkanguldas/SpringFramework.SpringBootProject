package com.gurkanguldas.frontendservice.dto;

import lombok.Getter;

public enum EntityHost
{
    //localhost
    //PersonHost("http://localhost:8081"),
    //CreditHost("http://localhost:8082");

    //docker
    PersonHost("http://personservice:8081"),
    CreditHost("http://creditservice:8082");

    @Getter
    private final String host;

    EntityHost(String host)
    {
        this.host = host;
    }
}
