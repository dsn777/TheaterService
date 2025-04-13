package com.dsn.Theater.API.client;

import com.dsn.Theater.API.dto.out.PurchaseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${feign.email.url}", name = "mailClient")
public interface MailClient {

    @PostMapping("/send")
    ResponseEntity<?> sendToMail(PurchaseDto purchaseDto);
}
