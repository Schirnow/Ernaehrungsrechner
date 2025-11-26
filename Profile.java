/**
*	Erfasst und speichert Einstellungen als
*	ein Object.
*/

public class Profile {
	protected int nutritionHours;
	protected int kcal_kg_d;
	protected String name;
	protected int minWeight, maxWeight;
	protected int minKcallD, maxKcallD;
	
	public Profile ( String name, int kcal_kg_d, int nutritionHours, int minWeight, int maxWeight, int minKcallD, int maxKcallD){
		this.name = name;
		this.kcal_kg_d = kcal_kg_d;
		this.nutritionHours = nutritionHours;
		this.minWeight = minWeight;
		this.maxWeight = maxWeight;
		this.minKcallD = minKcallD;
		this.maxKcallD = maxKcallD;
	}
	
	public Profile (String name, int kcal_kg_d){
		this(name, kcal_kg_d, 24, 10, 300,2000,4000);
	}
	
	public Profile (String name){
		this(name, 24, 24,10,300,2000,4000);
	}
	
	public Profile(){
		this("Default", 24, 24,10,300,2000,4000);
	}
	
	@Override public String toString(){
		return "Name: " + this.name + ", Kalorien pro kg am Tag: " + this.kcal_kg_d + ", Ernaehrungsstunden pro Tag: " + nutritionHours;
	}
	
	protected String getName (){
		return this.name;
	}
	
	protected int getKcalKgD(){
		return this.kcal_kg_d;
	}
	
	protected int getNutritionHours(){
		return this.nutritionHours;
	}
	
	protected int getMinWeight(){
		return this.minWeight;
	}
	
	protected int getMaxWeight(){
		return this.maxWeight;
	}
	
	protected int getMinKcallD(){
		return this.minKcallD;
	}
	
	protected int getMaxKcallD(){
		return this.maxKcallD;
	}
	
	protected void setName(String newName){
		name = newName;
	}
	
	protected void setKcalKgD(int newKcalKgD){
		kcal_kg_d = newKcalKgD;
	}
	
	protected void setNutritionHours(int newNutritionHours){
		nutritionHours = newNutritionHours;
	}
	
	protected void setMinWeight(int newMinWeight){
		minWeight = newMinWeight;
	}
	
	protected void setMaxWeight(int newMaxWeight){
		maxWeight = newMaxWeight;
	}
	
	protected void setMinKcallD(int newKcallD){
		minKcallD = newKcallD;
	}
	
	protected void setMaxKcallD(int newKcallD){
		maxKcallD = newKcallD;
	}
	
	public static void main (String[] args){
		
		/*var v1 = new Profile("V1 Profil", 24, 2410,300,10,300);
		var intensiv = new Profile("Intensiv-Profil", 30, 20,10,300);
		System.out.println(v1);
		System.out.println(intensiv);
		System.out.println(v1.getName());
		System.out.println(new Profile());*/
	}
}
