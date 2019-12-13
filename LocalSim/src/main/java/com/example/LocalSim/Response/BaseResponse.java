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
    public int status;

    public String message;
    public T data;

    @Override
    public String toString() {
        return "BaseResponse [status=" + status + "]";
    }

}
