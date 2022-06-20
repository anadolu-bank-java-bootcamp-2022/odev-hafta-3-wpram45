package com.gokhantamkoc.javabootcamp.odevhafta3.service;

import com.gokhantamkoc.javabootcamp.odevhafta3.model.Candle;
import com.gokhantamkoc.javabootcamp.odevhafta3.repository.CSVRepository;
import com.gokhantamkoc.javabootcamp.odevhafta3.util.chart.CandleStickChart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChartService {
	
	CSVRepository cryptoDataCSVRepository;
	
	public ChartService(CSVRepository cryptoDataCSVRepository) {
		this.cryptoDataCSVRepository = cryptoDataCSVRepository;
	}
	
	public CandleStickChart createChartFromCryptoData() {
		// Bu metodu doldurmanizi bekliyoruz.
		List<Candle> candles=null;

		try {
			candles = cryptoDataCSVRepository.readCSV("Binance_BTCUSDT_d.csv");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		CandleStickChart candleStickChart=new CandleStickChart("BTC/USDT Chart");
		//iterate all candles and add them to the chart

		//just add first 7 data
		int counter=0;
		for(Candle candle :candles ){

			counter++;

			candleStickChart.addCandle(candle.getTime(),candle.getOpen(),candle.getHigh(),candle.getLow(),candle.getClose(),candle.getVolume());

			//added counter to prevent out of memory heap space error
		if(counter>7){
		break;
		}
		}


		//return final version of chart
		return candleStickChart;
	}
}
