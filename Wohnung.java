/**
 * @author <Vladislav Kobyakov>
 * Matrikelnummer: 01500366
 */

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;

public abstract class Wohnung implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String type;
	private int id;
	private double flaeche;
	private int zimmerAnzahl;
	private int stockwerk;
	private int baujahr;
	private String pls;
	private String strasse;
	private int hausnummer;
	private int wohnungnummer;

	public Wohnung(String type, int id, double flaeche, int zimmerAnzahl, int stockwerk, int baujahr, String pls, String strasse, int hausnummer, int wohnungnummer) {
		//check each parameter and throw exception if an invalid argument has been provided.
		if (type.length() == 0) { throw new IllegalArgumentException("Error: Parameter ungueltig."); }
		else { this.type = type; }

		if (id < 0) { throw new IllegalArgumentException("Error: Parameter ungueltig."); }
		else { this.id = id; }

		if (flaeche < 1) { throw new IllegalArgumentException("Error: Parameter ungueltig."); }
		else { this.flaeche = flaeche; }

		if (zimmerAnzahl < 1) { throw new IllegalArgumentException("Error: Parameter ungueltig."); }
		else {this.zimmerAnzahl = zimmerAnzahl;}

		if (stockwerk < 0 || stockwerk > 100) { throw new IllegalArgumentException("Error: Parameter ungueltig."); }
		else {this.stockwerk = stockwerk;}

		if (baujahr < 1 || baujahr > Calendar.getInstance().get(Calendar.YEAR)) { throw new IllegalArgumentException("Error: Baujahr ungueltig."); }
		else {this.baujahr = baujahr;}

		if (pls.length() == 0) { throw new IllegalArgumentException("Error: Parameter ungueltig."); }
		else {this.pls = pls;}

		if (strasse.length() == 0) { throw new IllegalArgumentException("Error: Parameter ungueltig."); }
		else {this.strasse = strasse;}

		if (hausnummer < 1) { throw new IllegalArgumentException("Error: Parameter ungueltig."); }
		else {this.hausnummer = hausnummer;}

		if (wohnungnummer < 1) { throw new IllegalArgumentException("Error: Parameter ungueltig."); }
		else {this.wohnungnummer = wohnungnummer;}
	}

	public static DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		dfs.setDecimalSeparator('.');
		return new DecimalFormat("0.00", dfs);
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public double getFlaeche() {
		return flaeche;
	}

	public int getZimmerAnzahl() {
		return zimmerAnzahl;
	}

	public int getStockwerk() {
		return stockwerk;
	}

	public int getBaujahr() {
		return baujahr;
	}

	public String getPls() {
		return pls;
	}

	public String getStrasse() {
		return strasse;
	}

	public int getHausnummer() {
		return hausnummer;
	}

	public int getWohnungnummer() {
		return wohnungnummer;
	}

	public int getALter() {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		return currentYear - baujahr;
	}

	public abstract double gesamtKosten();

	@Override
	public String toString() {
		String flaecheFormatted = Wohnung.getDecimalFormat().format(flaeche);
		String idString = String.format("Id:             %d%n", id);
		String flaecheString = String.format("Flaeche:        %s%n", flaecheFormatted);
		String zimmerString = String.format("Zimmer:         %d%n", zimmerAnzahl);
		String stockString = String.format("Stock:          %d%n", stockwerk);
		String baujahrString = String.format("Baujahr:        %d%n", baujahr);
		String plzString = String.format("PLZ:            %s%n", pls);
		String strasseString = String.format("Strasse:        %s%n", strasse);
		String hausnummerString = String.format("Hausnummer:     %d%n", hausnummer);
		String wohnungnummerString = String.format("Top:            %d%n", wohnungnummer);
		return idString + flaecheString + zimmerString + stockString + baujahrString + plzString + strasseString + hausnummerString + wohnungnummerString;
	}

}