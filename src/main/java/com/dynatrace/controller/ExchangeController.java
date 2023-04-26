package com.dynatrace.controller;

import com.dynatrace.model.Currency;
import com.dynatrace.model.ExchangeRate;
import com.dynatrace.service.ExchangeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/nbp/exchanges")
@RequiredArgsConstructor
@Validated
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping(value = "/average-exchange-rate")
    @Operation(summary = "Get average exchange rate by code and date")
    public float getAverageExchangeRate(@RequestParam String code, @RequestParam String date) throws JsonProcessingException {
        return exchangeService.getAverageExchangeRateByCodeAndDate(code, date);
    }

    @GetMapping(value = "/min-max-rate")
    @Operation(summary = "Get max and min average exchange rate by code and top count")
    public ExchangeRate getMaxAndMinAverageExchangeRate(@RequestParam String code, @RequestParam
        @Min(value = 1, message = "Liczba notowań musi być w przedziale od 1 do 255.")
        @Max(value = 255, message = "Liczba notowań musi być w przedziale od 1 do 255.") Integer topCount) throws JsonProcessingException {

        return exchangeService.getMaxAndMinAverageExchangeRate(code, topCount);
    }

    @GetMapping(value = "/difference", params = {"code", "topCount"})
    public Currency getAskBidDifference(@RequestParam String code, @RequestParam
        @Min(value = 1, message = "Liczba notowań musi być w przedziale od 1 do 255.")
        @Max(value = 255, message = "Liczba notowań musi być w przedziale od 1 do 255.") Integer topCount) throws JsonProcessingException {

        return exchangeService.getDifferenceBetweenAskBid(code,topCount);
    }
}
