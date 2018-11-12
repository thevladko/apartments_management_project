/**
 * @author <Vladislav Kobyakov>
 * Matrikelnummer: 01500366
 */


public class MietWohnung extends Wohnung {
	
	private static final long serialVersionUID = 1L;

	private double monatlicheMietkosten;
	private int anzahlDerMieter;
	private double zuschlag;
	private double maxZuschlag;

	public MietWohnung(String type, int id, double flaeche, int zimmerAnzahl, int stockwerk, int baujahr, String pls, String strasse, int hausnummer, int wohnungnummer, double monatlicheMietkosten, int anzahlDerMieter) {
		super(type, id, flaeche, zimmerAnzahl, stockwerk, baujahr, pls, strasse, hausnummer, wohnungnummer);

		if (monatlicheMietkosten < 0) { throw new IllegalArgumentException("Error: Parameter ungueltig."); }
		else { this.monatlicheMietkosten = monatlicheMietkosten; }

		if (anzahlDerMieter < 1) { throw new IllegalArgumentException("Error: Parameter ungueltig."); }
		else { this.anzahlDerMieter = anzahlDerMieter; }

		zuschlag = 0.025;
		maxZuschlag = 0.1;
	}

	public double getMonatlicheMietkosten() {
		return monatlicheMietkosten;
	}

	public void setMonatlicheMietkosten(double monatlicheMietkosten) {
		this.monatlicheMietkosten = monatlicheMietkosten;
	}

	public int getAnzahlDerMieter() {
		return anzahlDerMieter;
	}

	public void setAnzahlDerMieter(int anzahlDerMieter) {
		this.anzahlDerMieter = anzahlDerMieter;
	}

	@Override
	public double gesamtKosten() {
		double additional = ((anzahlDerMieter - 1) * zuschlag) > maxZuschlag ? maxZuschlag : (anzahlDerMieter - 1) * zuschlag;
		return (this.monatlicheMietkosten * this.getFlaeche()) + (this.monatlicheMietkosten * this.getFlaeche() * additional);
	}

	@Override
	public String toString() {
		String fromSuperClass = super.toString();
		String typString = String.format("Typ:            %s%n", this.getType());
		String formattedMonatlicheMietkosten = Wohnung.getDecimalFormat().format(monatlicheMietkosten);
		String mieteString = String.format("Miete/m2:       %s%n", formattedMonatlicheMietkosten);
		String anzahlVonMieterString = String.format("Anzahl Mieter:  %d%n", this.getAnzahlDerMieter());
		return typString + fromSuperClass + mieteString + anzahlVonMieterString;
	}

}