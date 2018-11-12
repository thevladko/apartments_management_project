/**
 * @author <Vladislav Kobyakov>
 * Matrikelnummer: 01500366
 */


import java.util.List;

public interface HausverwaltungDAO {

	public List<Wohnung> getWohnungen();

	public Wohnung getWohnungbyId(int id);

	public void saveWohnung(Wohnung wohnung);

	public void deleteWohnung(int id);
}