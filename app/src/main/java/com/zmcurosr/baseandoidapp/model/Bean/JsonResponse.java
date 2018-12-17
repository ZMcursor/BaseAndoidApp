package com.zmcurosr.baseandoidapp.model.Bean;

public final class JsonResponse<T> {
    public String message;
    public T data;

    public boolean success() {
        return "success".equals(message);
    }
}
