package com.sergiotrapiello.cursotesting.basico.imitacion.currency;

import lombok.Data;

@Data
public class CurrencyConverter {
	
	private QuotationProvider quotationProvider;

	public CurrencyConverter(QuotationProvider quotationProvider) {
		this.quotationProvider = quotationProvider;
	}

	public double convert(double amountTotConvert, CurrencyEnum from, CurrencyEnum to) {
		if(from == to)
			return amountTotConvert;
		double quotation = quotationProvider.getQuotation(from, to);
		return quotation * amountTotConvert;
	}

}
