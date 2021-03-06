package config;

/**
 * Cette enumeration liste les plateau de jeux disponible aux utilisateurs.
 * @author PHPierre
 *
 */
public enum Map
{
	SQUARE("Plateau rectangulaire"),
	CIRCULAR("Plateau circulaire");
	
	private String name;
	
	private Map(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public static Map getMap(String name)
	{
		for (Map map : Map.values()) {
            if (name.equals(map.getName())) {
                return map;
            }
        }
        return null;
	}
}
