package com.mateudev.microserviceregister.controllers;

import com.mateudev.microserviceregister.dto.OfferRequestDto;
import com.mateudev.microserviceregister.dto.OfferResponseDto;
import com.mateudev.microserviceregister.services.OfferService;
import com.mateudev.microserviceregister.util.DebugInfoUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/proposta")
@Slf4j
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public ResponseEntity<OfferResponseDto> createOffer(@RequestBody OfferRequestDto reequestDto) {

        OfferResponseDto response = offerService.createOffer(reequestDto);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri())
                .body(response);
    }

    @GetMapping
    public ResponseEntity<?> getOffer() {
        try {
            List<OfferResponseDto> offerResponseDtoList =  offerService.getOffer();
            return ResponseEntity.ok(offerResponseDtoList);
        }catch (Exception e){
            log.error(e.getMessage());
        //    DebugInfoUtil.logExceptionDetails(e);
        //  return   ResponseEntity.internalServerError().body(DebugInfoUtil.logExceptionDetails(e));
        //    return   ResponseEntity.internalServerError().body(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
