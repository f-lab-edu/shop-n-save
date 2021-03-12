package com.flab.demo.controller;

import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public enum ResponseEntityGroup {
    OK_RESPONSE_ENTITY(ResponseEntity.ok().build()),
    INTERNAL_SERVER_ERROR_RESPONSE_ENTITY(ResponseEntity.status(INTERNAL_SERVER_ERROR).build());

    private final ResponseEntity entity;

    ResponseEntityGroup(ResponseEntity entity) {
        this.entity = entity;
    }

    public ResponseEntity getEntity() {
        return entity;
    }
}
