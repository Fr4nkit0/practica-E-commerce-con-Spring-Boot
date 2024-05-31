package com.example.persistence.util;


import jakarta.persistence.Embeddable;

@Embeddable
public enum Status {
    ENABLED, DISABLED;
}
