package com.sto.check.model.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum RestStatus {
    SUCCESS,
    ERROR,
    NOT_FOUND("entity.not.found"),
    INTERNAL_SERVER_ERROR("internal.server.error"),
    UNIQUE_ERROR("unique.error");
    private String key;
}
