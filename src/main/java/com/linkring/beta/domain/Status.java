package com.linkring.beta.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Status implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
