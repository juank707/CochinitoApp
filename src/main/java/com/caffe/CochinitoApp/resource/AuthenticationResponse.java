package com.caffe.CochinitoApp.resource;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String token;

    public AuthenticationResponse(String token) {
        super();
        this.token = token;
    }


}
