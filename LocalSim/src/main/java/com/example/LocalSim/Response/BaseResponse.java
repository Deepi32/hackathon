package com.example.LocalSim.Response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    protected int status;

    protected String message;
    protected T data;

    @Override
    public String toString() {
        return "BaseResponse [status=" + status + "]";
    }

}
