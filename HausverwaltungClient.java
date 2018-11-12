/**
 * @author <Vladislav Kobyakov>
 * Matrikelnummer: 01500366
 */

public class HausverwaltungClient {

    public static void main(String[] args) {

//        String fileName = args[0];
//        String command = args[1];

        Hausverwaltung hausverwaltung = new Hausverwaltung("apartments.txt");

        MietWohnung mietWohnung = new MietWohnung("MW", 2, 25, 4,5, 1900, "1020", "Strasse", 13, 3, 3.0, 5);
        hausverwaltung.addApartment(mietWohnung);

        EigentumsWohnung eigentumsWohnung = new EigentumsWohnung("EW", 13, 50, 5, 4, 1990, "10213", "Strasse", 4, 4,90, 10);
        hausverwaltung.addApartment(eigentumsWohnung);

        System.out.println(mietWohnung.gesamtKosten());
        System.out.println(eigentumsWohnung.gesamtKosten());

        hausverwaltung.getDurchschnittlicheGesamtKostenAllerWohnungen();


//        try {
//            if (command.equals("list")) {
//                if (args.length == 2) {
//                    hausverwaltung.getInfoAboutAllApartments();
//                } else {
//                    hausverwaltung.getInfoAboutApartment(Integer.parseInt(args[2]));
//                }
//            } else if (command.equals("add")) {
//                double lastParameter;
//                if (args.length == 14) {
//                    lastParameter = Double.parseDouble(args[13]);
//                } else {
//                    lastParameter = -1;
//                }
//                if (args[2].equals("EW")) {
//                    try {
//                        checkParam(args[3]);
//                        EigentumsWohnung wohnung = new EigentumsWohnung(args[2], Integer.parseInt(args[3]), Double.parseDouble(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]), Integer.parseInt(args[7]), args[8], args[9], Integer.parseInt(args[10]), Integer.parseInt(args[11]), Double.parseDouble(args[12]), lastParameter);
//                        hausverwaltung.addApartment(wohnung);
//                    } catch (IllegalArgumentException e) {
//                        System.out.println(e.getMessage());
//                    } catch (Exception ex) {}
//                } else {
//                    try {
//                        MietWohnung wohnung = new MietWohnung(args[2], Integer.parseInt(args[3]), Double.parseDouble(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]), Integer.parseInt(args[7]), args[8], args[9], Integer.parseInt(args[10]), Integer.parseInt(args[11]), Double.parseDouble(args[12]), Integer.parseInt(args[13]));
//                        hausverwaltung.addApartment(wohnung);
//                    } catch (IllegalArgumentException e) {
//                        System.out.println(e.getMessage());
//                    } catch (Exception ex) {}
//                }
//            } else if (command.equals("delete")) {
//                try {
//                    hausverwaltung.deleteApartment(Integer.parseInt(args[2]));
//                } catch (IllegalArgumentException e) {
//                    System.out.println(e.getMessage());
//                }
//            } else if (command.equals("count")) {
//                if (args.length == 2) {
//                    hausverwaltung.countNumberOfAllApartments();
//                } else {
//                    if (args[2].equals("EW")) {
//                        hausverwaltung.countEigentumsWohnungen();
//                    } else {
//                        hausverwaltung.countMietwohnungen();
//                    }
//                }
//            } else if (command.equals("meancosts")) {
//                hausverwaltung.getDurchschnittlicheGesamtKostenAllerWohnungen();
//            } else if (command.equals("oldest")) {
//                hausverwaltung.getOldestApartments();
//            } else {
//                throw new IllegalArgumentException("Error: Parameter ungueltig.");
//            }
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
    }



    public static void checkParam(String param) {
        try {
            Integer.parseInt(param);
        }
        catch(NumberFormatException nFE) {
            throw new IllegalArgumentException("Error: Parameter ungueltig.");
        }
    }


}