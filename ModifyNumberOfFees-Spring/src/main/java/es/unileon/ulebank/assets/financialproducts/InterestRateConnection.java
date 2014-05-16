package es.unileon.ulebank.assets.financialproducts;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.jndi.toolkit.url.Uri;

public class InterestRateConnection {
	/**
	 * URL where we need to access to obtain the actual value to the interest
	 */
	private String urlDatosMacro = "http://www.datosmacro.com/hipotecas/";
	public String title;
	private Elements query;
	private double rate;

	public void connection(KindInterestRate rate) throws IOException {

		Document doc = Jsoup.connect(urlDatosMacro + rate.name).get();
		title = doc.title();
		this.query = doc.select("td[class=numero]");
		Element interest = this.query.first();
		char[] charsInterest = interest.text().toCharArray();
		charsInterest[1] = '.';
		String num = String.copyValueOf(charsInterest, 0,
				charsInterest.length - 1);
		this.rate = Double.valueOf(num);

	}

	public String getTitle() {
		return title;
	}

	public Elements getQuery() {
		return query;
	}

	public double getRate() {
		return rate;
	}

}
