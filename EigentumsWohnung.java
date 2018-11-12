/**
 * @author <Vladislav Kobyakov>
 * Matrikelnummer: 01500366
 */

public class EigentumsWohnung extends Wohnung {

	private static final long serialVersionUID = 1L;

	private double betriebskosten;
	private double reparaturBeitrag;
	private double zuschlag;

	public EigentumsWohnung(String type, int id, double flaeche, int zimmerAnzahl, int stockwerk, int baujahr, String pls, String strasse, int hausnummer, int wohnungnummer, double betriebskosten, double reparaturBeitrag) {
		super(type, id, flaeche, zimmerAnzahl, stockwerk, baujahr, pls, strasse, hausnummer, wohnungnummer);

		if (betriebskosten < 0) { throw new IllegalArgumentException("Error: Parameter ungueltig."); }
		else { this.betriebskosten = betriebskosten; }

		if (reparaturBeitrag < 0) { throw new IllegalArgumentException("Error: Parameter ungueltig."); }
		else { this.reparaturBeitrag = reparaturBeitrag; }

		zuschlag = 0.02;
	}

	public double getBetriebskosten() {
		return betriebskosten;
	}

	public void setBetriebskosten(double betriebskosten) {
		this.betriebskosten = betriebskosten;
	}

	public double getReparaturBeitrag() {
		return reparaturBeitrag;
	}

	public void setReparaturBeitrag(double reparaturBeitrag) {
		this.reparaturBeitrag = reparaturBeitrag;
	}



	@Override
	public double gesamtKosten() {
		double costs = this.getFlaeche() * (betriebskosten + reparaturBeitrag);
		return costs + costs * this.getStockwerk() * zuschlag;
	}

	@Override
	public String toString() {
		String fromSuperClass = super.toString();
		String formattedBetriebskosten = Wohnung.getDecimalFormat().format(betriebskosten);
		String formattedReparaturBeitrag = Wohnung.getDecimalFormat().format(reparaturBeitrag);
		String typeString = String.format("Typ:            %s%n", this.getType());
		String betriebskostenString = String.format("Betriebskosten: %s%n", formattedBetriebskosten);
		String reparaturBeitragString = String.format("Ruecklage:      %s%n", formattedReparaturBeitrag);
		return typeString + fromSuperClass + betriebskostenString + reparaturBeitragString;
	}
}