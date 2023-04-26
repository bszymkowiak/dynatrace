package com.dynatrace.service;

import com.dynatrace.exception.DateBadFormatException;
import com.dynatrace.exception.NoResultsException;
import com.dynatrace.model.Currency;
import com.dynatrace.model.ExchangeRate;
import com.dynatrace.model.Rate;
import com.dynatrace.validator.DateValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    public float getAverageExchangeRateByCodeAndDate(String code, String date) throws JsonProcessingException {

        if(!DateValidator.isValidDate(date)){
            throw new DateBadFormatException("Podana data jest błędna! Data musi być w formacie yyyy-MM-dd");
        }
        RestTemplate restTemplate = new RestTemplate();
        String url
                = "http://api.nbp.pl/api/exchangerates/rates/A/" + code + "/" + date + "/?format=json";

        ResponseEntity<String> response
                = restTemplate.getForEntity(url , String.class);
        ObjectMapper mapper = new ObjectMapper();
        Currency currency = mapper.readValue(response.getBody(), new TypeReference<>(){});

        if(currency.getRates() == null){
            throw new NoResultsException("Nie znaleziono wyników zapytania.");
        }
        return currency.getRates().get(0).getMid();
    }

    public ExchangeRate getMaxAndMinAverageExchangeRate(String code, Integer topCount) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.nbp.pl/api/exchangerates/rates/A/" + code + "/last/" + topCount + "/?format=json";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        Currency currency = mapper.readValue(response.getBody(), new TypeReference<>(){});

        float minMid = currency.getRates()
                .stream()
                .map(Rate::getMid)
                .min(Float::compare)
                .get();

        float maxMid = currency.getRates()
                .stream()
                .map(Rate::getMid)
                .max(Float::compare)
                .get();

        return new ExchangeRate(currency.getCode(), minMid, maxMid);
    }

    public Currency getDifferenceBetweenAskBid(String code, Integer topCount) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.nbp.pl/api/exchangerates/rates/C/" + code + "/last/" + topCount + "/?format=json";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        Currency currency = mapper.readValue(response.getBody(), new TypeReference<>(){});

        for (Rate r : currency.getRates()) {
            r.setDiffAskBid(r.getAsk() - r.getBid());
        }
        return currency;
    }
}
