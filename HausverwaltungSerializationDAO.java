/**
 * @author <Vladislav Kobyakov>
 * Matrikelnummer: 01500366
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class HausverwaltungSerializationDAO implements HausverwaltungDAO {

    private static HausverwaltungSerializationDAO instance;
    private List<Wohnung> wohnungen;
    private String fileName;

    private HausverwaltungSerializationDAO(String fileName) {
        File file = new File(fileName);
        this.fileName = fileName;
        try {
            if (!file.createNewFile() && file.length() != 0) { wohnungen = deserializeWohnungen(); }
            else { wohnungen = new ArrayList<>(); }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error: parameter ungueltig");
        }
    }

    public static HausverwaltungSerializationDAO getInstance(String fileName) {
        if (instance == null) {
            instance = new HausverwaltungSerializationDAO(fileName);
        }
        return instance;
    }

    void serializeWohnungen() {
        File file = new File(fileName);
        if (file.exists()) file.delete();
        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName, true));
            writer.writeObject(wohnungen);
            writer.close();
        } catch (Exception e) {
            System.err.format("Fehler bei Serialisierung: %s", e.getMessage());
            System.exit(1);
        }
    }

    @SuppressWarnings("unchecked")
    List<Wohnung> deserializeWohnungen() {
        try {
            ObjectInputStream reader;
            reader = new ObjectInputStream(new FileInputStream(fileName));
            wohnungen = (List<Wohnung>)reader.readObject();
            reader.close();
        } catch (Exception e) {
            System.err.format("Fehler bei Deserialisierung: %s", e.getMessage());
            System.exit(1);
        }
        return wohnungen;
    }


    public List<Wohnung> getWohnungen() {
        return wohnungen;
    }

    public Wohnung getWohnungbyId(int id) {
        Wohnung wohnung = null;

        for (Wohnung w : wohnungen) {
            if (w.getId() == id) {
                wohnung = w;
            }
        }

        return wohnung;
    }

    public void saveWohnung(Wohnung wohnung) {
        for (Wohnung w : wohnungen) {
            if (w.getId() == wohnung.getId()) {
                String formattedErrorMessage = String.format("Error: Wohnung bereits vorhanden. (id=%d)", wohnung.getId());
                throw new IllegalArgumentException(formattedErrorMessage);
            }
        }
        wohnungen.add(wohnung);
        serializeWohnungen();
    }

    public void deleteWohnung(int id) {
        boolean isObjectFound = false;
        for (Wohnung w : wohnungen) {
            if (w.getId() == id) {
                wohnungen.remove(w);
                serializeWohnungen();
                isObjectFound = true;
            }
        }
        if (!isObjectFound) {
            String formattedErrorMessage = String.format("Error: Wohnung nicht vorhanden. (id=%d)", id);
            throw new IllegalArgumentException(formattedErrorMessage);
        }
    }
}