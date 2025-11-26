public class Nutrition{

	private String name, manufacturer, application;
	private int volume;
	private double kcallMl;
	
	public Nutrition(String manufacturer, String name, double kcallMl, int volume, String application){
		this.manufacturer = manufacturer;
		this.name = name;
		this.kcallMl = kcallMl;
		this.volume = volume;
		this.application = application;
	}
	
	protected String getManufacturer(){
		return manufacturer;
	}
	
	protected String getName(){
		return name;
	}
	
	protected double getKcallMl(){
		return kcallMl;
	}
	
	protected int getVolume(){
		return volume;
	}
	
	protected String getApplication(){
		return application;
	}
	
	protected void setManufacturer(String newManufacturer){
		manufacturer = newManufacturer;
	}
	
	protected void setName(String newName){
		name = newName;
	}
	
	protected void setKcallMl(double newKcallMl){
		kcallMl = newKcallMl;
	}
	
	protected void setVolume(int newVolume){
		volume = newVolume;
	}
	
	protected void setApplication(String newApplication){
		application = newApplication;
	}

	/*@Override public String toString(){
		return "Hersteller: " + manufacturer + ",\n Handelsname: " + name + ",\n Energiegehalt: " + kcallMl 
				+ " kcall/ml,\n Inhalt: " + volume + " ml\n" + "Applicationsart: " + application + '\n';
	}*/
	
	@Override public String toString(){
		if (name == "--") return "--";
		return name + " " + kcallMl + " kcall/ml";
	}
	
	// Zu entfernen, wenn Class fertig ist:
	public static void main(String[] args){
		var feinkost = new Nutrition("Abbot", "Fresubin", 1.5, 500, "oral");
		System.out.println(feinkost);
		System.out.println("Getter: " + feinkost.getManufacturer() + ", " + feinkost.getKcallMl());
		feinkost.setManufacturer("BASF");
		feinkost.setName("Fresubin Drink");
		feinkost.setKcallMl(2.0);
		feinkost.setVolume(500);
		System.out.println(feinkost);
	}
}
