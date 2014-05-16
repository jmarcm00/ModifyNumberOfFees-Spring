package es.unileon.ulebank.assets.financialproducts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import es.unileon.ulebank.assets.financialproducts.exceptions.EuriborException;

public class Euribor implements InterestRate {

	private double euriborRate;

	public Euribor() throws EuriborException {
		this.euriborRate = getEuribor(PeriodTime.EuriborOneYear);
	}

	public Euribor(PeriodTime euriborPeriodTime) throws EuriborException {
		this.euriborRate = getEuribor(euriborPeriodTime);

	}

	/**
	 * Method to calculate the euribor
	 * 
	 * @return euribor The actual value of the euribor
	 * 
	 *         If something goes wrong the method returns 0.0
	 */
	public double getEuribor(PeriodTime periodTime) throws EuriborException {
		String url = "http://www.eleconomista.es/lista-quotes/JSON.php?lista="
				+ getEuriborPeriodTime(periodTime);
		JSONParser parser = new JSONParser();
		double euribor = 0;
		try {
			Object object = (Object) parser.parse(getJSON(url));

			JSONObject jsonObject = (JSONObject) object;
			JSONArray jsonArray = (JSONArray) jsonObject.get("Quote");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObj = (JSONObject) jsonArray.get(i);
				String valueEuribor = String.valueOf(jsonObj.get("ult"));
				String differential = String.valueOf(jsonObj.get("pDif"));

				differential = differential.substring(
						differential.indexOf('+') + 1,
						differential.indexOf("%"));

				NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
				Number numberEuribor = null;
				Number numberDifferential = null;

				try {
					numberEuribor = format.parse(valueEuribor);
					numberDifferential = format.parse(differential);
				} catch (java.text.ParseException e) {
					throw new EuriborException("Error al parsear los datos.");
				}

				euribor = numberEuribor.doubleValue()
						+ numberDifferential.doubleValue();

			}

			return euribor;

		} catch (ParseException e) {
			throw new EuriborException("Error al obtener los datos.");
		}
	}

	/**
	 * Method that returns the JSON object If some fail occurs, the method
	 * returns null
	 * 
	 * @param url
	 * @return un string that represents the JSON object
	 */
	private String getJSON(String url) {
		URL url12;
		try {
			url12 = new URL(url);

			URLConnection urlConn = url12.openConnection();
			InputStreamReader inStream = new InputStreamReader(
					urlConn.getInputStream());
			BufferedReader buff = new BufferedReader(inStream);

			StringBuilder json = new StringBuilder();
			String line = "";
			while ((line = buff.readLine()) != null) {
				json.append(line);
			}
			return json.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Method to get the Period of time that euribor need to recalculate
	 * 
	 * @param periodTime
	 * @return
	 */

	private String getEuriborPeriodTime(PeriodTime periodTime) {
		switch (periodTime) {
		case EuriborOneWeek:
			return EuriborPeriod.euriborOneWeek;
		case EuriborTwoWeeks:
			return EuriborPeriod.euriborTwoWeeks;
		case EuriborThreeWeeks:
			return EuriborPeriod.euriborThreeWeeks;
		case EuriborOneMonth:
			return EuriborPeriod.euriborOneMonth;
		case EuriborTwoMonths:
			return EuriborPeriod.euriborTwoMonths;
		case EuriborThreeMonths:
			return EuriborPeriod.euriborThreeMonths;
		case EuriborFourMonths:
			return EuriborPeriod.euriborFourMonths;
		case EuriborFiveMonths:
			return EuriborPeriod.euriborFiveMonths;
		case EuriborSixMonths:
			return EuriborPeriod.euriborSixMonths;
		case EuriborSevenMonths:
			return EuriborPeriod.euriborSevenMonths;
		case EuriborEightMonths:
			return EuriborPeriod.euriborEightMonths;
		case EuriborNineMonths:
			return EuriborPeriod.euriborNineMonths;
		case EuriborTenMonths:
			return EuriborPeriod.euriborTenMonths;
		case EuriborElevenMonths:
			return EuriborPeriod.euriborElevenMonths;
		case EuriborOneYear:
			return EuriborPeriod.euriborOneYear;
		default:
			return EuriborPeriod.euriborOneYear;
		}
	}

	public double getEuriborRate() {
		return this.euriborRate;
	}

	@Override
	public double getInterestRate() {
		return this.euriborRate;
	}

}
