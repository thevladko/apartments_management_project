/**
 * @author <Vladislav Kobyakov>
 * Matrikelnummer: 01500366
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Hausverwaltung {

	private HausverwaltungDAO hausverwaltungDAO;

    public Hausverwaltung(String fileName) {
        hausverwaltungDAO = HausverwaltungSerializationDAO.getInstance(fileName);
    }

    public void getOldestApartments() {
        if (hausverwaltungDAO.getWohnungen().isEmpty()) {
            return;
        }
        Set<Integer> ids = new HashSet<>();
        Wohnung oldest = hausverwaltungDAO.getWohnungen().get(0);
        for(int i = 1; i < hausverwaltungDAO.getWohnungen().size(); i++){
            if (hausverwaltungDAO.getWohnungen().get(i).getALter() > oldest.getALter()) {
                oldest = hausverwaltungDAO.getWohnungen().get(i);
            }
        }
        ids.add(oldest.getId());
        for (Wohnung w : hausverwaltungDAO.getWohnungen()) {
            if (oldest.getALter() == w.getALter()) {
                ids.add(w.getId());
            }
        }
        for (Integer number : ids) {
            System.out.format("Id: %d%n", number);
        }
    }

    public void getInfoAboutAllApartments() {
        for (Wohnung wohnung : hausverwaltungDAO.getWohnungen()) {
            System.out.println(wohnung);
        }
    }

    public void getInfoAboutApartment(int id) {
        for (Wohnung wohnung : hausverwaltungDAO.getWohnungen()) {
            if (wohnung.getId() == id) {
                System.out.println(wohnung);
            }
        }
    }

    public void addApartment(Wohnung apartment) {
        hausverwaltungDAO.saveWohnung(apartment);
        System.out.format("Info: Wohnung %d added.%n", apartment.getId());
    }

    public void deleteApartment(int id) {
        try {
            hausverwaltungDAO.deleteWohnung(id);
        } catch (Exception ex) {

        }
        System.out.format("Info: Wohnung %d deleted.%n", id);
    }

    public void countNumberOfAllApartments() {
        System.out.format("%d%n", hausverwaltungDAO.getWohnungen().size());
    }

    public void countMietwohnungen() {
        int count = 0;
        for (Wohnung wohnung : hausverwaltungDAO.getWohnungen()) {
            if (wohnung instanceof MietWohnung) {
                count++;
            }
        }
        System.out.format("%d%n", count);
    }

    public void countEigentumsWohnungen() {
//        int count = 0;
//        for (Wohnung wohnung : hausverwaltungDAO.getWohnungen()) {
//            if (wohnung instanceof EigentumsWohnung) {
//                count++;
//            }
//        }
//        System.out.format("%d%n", count);
        System.out.println(hausverwaltungDAO.getWohnungen().stream().filter(w -> w instanceof EigentumsWohnung).count());
    }

    public void getDurchschnittlicheGesamtKostenAllerWohnungen() {
//        double kosten = 0.0;
//        for (Wohnung wohnung : hausverwaltungDAO.getWohnungen()) {
//            kosten += wohnung.gesamtKosten();
//        }

//        double accumulatedGesamtKosten = hausverwaltungDAO.getWohnungen().stream().map(Wohnung::gesamtKosten).reduce(0.0, (w1, w2) -> w1 + w2);
//        double accumulatedGesamtKosten = hausverwaltungDAO.getWohnungen().stream().mapToDouble(w -> w.gesamtKosten()).sum();

        double accumulatedGesamtKosten = hausverwaltungDAO.getWohnungen().stream()
                .filter(w -> w instanceof MietWohnung)
                .mapToDouble(Wohnung::gesamtKosten)
                .average()
                .getAsDouble();
        System.out.println(accumulatedGesamtKosten);

//        hausverwaltungDAO.getWohnungen().stream().map(Wohnung::getStrasse).forEach(w -> System.out.println(w));
//        System.out.println(accumulatedGesamtKosten);

//        double average = kosten / hausverwaltungDAO.getWohnungen().size();
//        String formatted = Wohnung.getDecimalFormat().format(average);
//        System.out.println(formatted);
    }
}