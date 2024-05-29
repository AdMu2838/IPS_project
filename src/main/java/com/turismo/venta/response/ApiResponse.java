package com.turismo.venta.response;
import lombok.*;

@Data
@Setter
@Getter
public class ApiResponse {
    private String message;
    private boolean status;
    public ApiResponse(String string, boolean b) {
        // TODO Auto-generated constructor stub
    }

}