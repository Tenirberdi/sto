package com.sto.repair.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse<T> {
    private RestStatus status;
    private String message;
    private T data;

    public RestResponse (RestStatus status) {
        this.status = status;
    }

    public RestResponse (RestStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public RestResponse (RestStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public static RestResponse<Void> success() {
        return new RestResponse<>(RestStatus.SUCCESS);
    }

    public static <T> RestResponse<T> success(T data) {
        return new RestResponse<>(RestStatus.SUCCESS, data);
    }

    public static RestResponse<Void> error(String message) {
        return new RestResponse<>(RestStatus.ERROR, message);
    }
}
