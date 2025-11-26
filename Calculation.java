/************************************************
*
*	Berechnet die Laufraten anhand Energiegehaltes
*	und Kaloriebedarfes.
*
*************************************************/
/*
Beispielberechnung der LR ab d2: Patient 83 kg mit 50% VKOF
24 kcal x 80 kgKG = 2000 kcal/d
Davon 75% = 2000 x 0,75 -> 1500 kcal/d
Fresubin HPenergy hat 1,5 kcal/ml -> 1500 : 1,5 = 1000 ml/d
1000ml : 24h -> Start-LR der enteralen Ernährung von 42ml/h kontinuierlich über 24h
*/
public class Calculation {
	/**
	* berechnet tägliche Laufraten der Ernaehrung. Formel: Energiebedarf * prozentuale Steigerung / (Kaloriegehalt der Ernaehrung * Dauer der Application in Stunden)
	* @param kaloriegehalt Kaloriegehalt der enteralen Enraehrung (1,08 , 1,5 ...)
	* @param energiebedarf anhand des (idealen) Koerpergewichtes oder der Kaloriemetiemessung ermittelte Energiebedarf
	* @param prozentuale Steigerung der Ernaehrung (75%, 25%/3) für den ersten, zweiten, dritten und den vierten Tag nach Ankunft.
	* @return ausgerechnete Laufrate der enteralen Ernaehrung, abgerundet bis auf zweite Nachkommastelle.
	*/
	protected static double fluidCalc(double kaloriegehalt, int energiebedarf, double steigerung, int hours){
		return (int)((energiebedarf * steigerung) / (kaloriegehalt * hours)*100)/100;
	}

	/**
	* Laesst die Laufraten berechnen und gibt diese aus
	* @param kaloriegehalt Kaloriegehalt der enteralen Enraehrung (1,08 , 1,5 ...)
	* @param gewicht (idealer) Gewicht des Patienten
	* @param kcal_kg_d Energiedeckung kcal pro Kilogramm Koerpergewicht pro Tag
	*/
	protected static String calculateEnteral( double kaloriegehalt, int energiebedarf, int hours){
		//int energiebedarf = kcal_kg_d * gewicht;
		double result = fluidCalc(kaloriegehalt, energiebedarf, 0.75, hours);
		String printResult = "Laufrate Tag 1: " + result;
		for ( int i = 0; i < 3; i++){
			result += fluidCalc(kaloriegehalt, energiebedarf, 0.083, hours);
			printResult += "Laufrate Tag " + (i+2) + ": " + result + '\n';
		}
		System.out.print(printResult);
		return printResult;
	}
	
	public static void main (String[] args){
		double kaloriegehalt = 1.5;
		int gewicht = 83;
		//int energiebedarf = 2000;
		int kcal_kg_d = 24;
		calculateEnteral(kaloriegehalt, gewicht, kcal_kg_d);
	}
}
