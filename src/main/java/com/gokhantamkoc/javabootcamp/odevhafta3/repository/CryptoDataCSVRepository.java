package com.gokhantamkoc.javabootcamp.odevhafta3.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.gokhantamkoc.javabootcamp.odevhafta3.model.Candle;

public class CryptoDataCSVRepository implements CSVRepository {
	
	private final String COMMA_DELIMITER = ",";

	@Override
	public List<Candle> readCSV(String filename) throws FileNotFoundException, IOException {
		List<Candle> candles = new ArrayList<Candle>();
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);
		// Bu alandan itibaren kodunuzu yazabilirsiniz
		BufferedReader br = new BufferedReader( new InputStreamReader((inputStream)));



		//skip first row pass every line to map (with lambda expression)
		//turn DataStream in to the List with collectors.toList()
		candles=br.lines().skip(1).map((line)->{
			//split rows by comma because csv comma separated data structure
			String[] p = line.split(",");

			//assign variables corresponding values
			long time=Long.valueOf(p[0]);
			double open=Double.valueOf(p[3]);
			double high=Double.valueOf(p[4]);
			double low=Double.valueOf(p[5]);
			double close=Double.valueOf(p[6]);
			double volume=Double.valueOf(p[7]);

			//generate new candle object
			Candle item = new Candle(time,open,high,low,close,volume);

			//return every candle object with filled data
			return item;

		}).collect(Collectors.toList());

		//close dataStream head pointer
		br.close();



		// Bu alandan sonra kalan kod'a dokunmayiniz.
		return candles;
	}

}
