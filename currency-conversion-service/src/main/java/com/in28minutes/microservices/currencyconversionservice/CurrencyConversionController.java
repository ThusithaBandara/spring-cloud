package com.in28minutes.microservices.currencyconversionservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CurrencyConversionController {
	@Autowired
	private CurrencyExchangeProxy proxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {
		CurrencyConversionBean r = proxy.retrieveExchangeValue(from, to);
		
		
		return new CurrencyConversionBean(r.getId(),from,to,r.getConversionMultiple(),quantity,quantity.multiply(r.getConversionMultiple()),r.getPort());
	}
}
