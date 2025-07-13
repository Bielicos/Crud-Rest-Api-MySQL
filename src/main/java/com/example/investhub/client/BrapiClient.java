package com.example.investhub.client;

import com.example.investhub.client.dto.BrapiResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.cloud.openfeign.FeignClient(
        name = "client",
        url = "https://brapi.dev"
)
public interface BrapiClient {
    @GetMapping(value = "/api/quote/{stockId}")
    BrapiResponseDto getQuote(@PathVariable("stockId") String stockId,
                              @RequestHeader("Authorization") String token);
}
