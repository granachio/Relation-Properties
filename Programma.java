import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Programma{
	//Pattern pattern = Pattern.compile("([<]+[a-zA-Z0-9]+,[a-zA-Z0-9]+[>])");
	//Pattern pattern = Pattern.compile("^[<]([a-zA-Z0-9]+,[a-zA-Z0-9]+)[>]$");
	//Pattern pattern = Pattern.compile("<([^<]+)>");
	public static void main(String[] args){
		
		Scanner tastiera = new Scanner(System.in);
		
		System.out.println("Inserisci l'insieme di partenza A nella forma: el1 el2 el3");
		String[] insiemeA = tastiera.nextLine().replaceAll("\\s{2,}", " ").trim().split(" ");
		
		
		System.out.println("Inserisci l'insieme di arrivo B nella forma: el1 el2 el3");
		String[] insiemeB = tastiera.nextLine().replaceAll("\\s{2,}", " ").trim().split(" ");
		
		System.out.println("Inserisci la relazione AxB nella forma: <el1,el2><el1,el3>...");
		String s = tastiera.nextLine();
		s = s.replaceAll("\\s+","");
		
		Pattern pattern = Pattern.compile("<([a-zA-Z0-9]+,[a-zA-Z0-9]+)>");
		
		String[] first = new String[(Metodi.getNumberSeparators(",", s))];
		String[] second = new String[(Metodi.getNumberSeparators(",", s))];
		
		int index = 0;
		Matcher matcher = pattern.matcher(s);
		while(matcher.find()) {
			//System.out.println("Sottosequenza : "+matcher.group());
			//System.out.println("Sottogruppo 1 : "+matcher.group(1));
			String group = matcher.group(1);
			String[] parts = group.split(",");
			first[index] = parts[0];
			second[index] = parts[1];
			index++;
		}
		
		if(!Metodi.checkInsieme(insiemeA, first) || !Metodi.checkInsieme(insiemeB, second))
		{
			System.out.println("Relazione incompatibile con Insiemi di partenza e arrivo.");
			System.exit(-1);
		}
		boolean riflessiva = Metodi.isRiflessiva(first, second);
		boolean simmetrica = Metodi.isSimmetrica(first, second);
		boolean transitiva = Metodi.isTransitiva(first, second);
		boolean antisimmetrica = Metodi.isAntisimmetrica(first, second);
		System.out.println("Riflessiva: " + riflessiva);
		System.out.println("Simmetrica: " + simmetrica);
		System.out.println("Transitiva: " + transitiva);
		System.out.println("Antisimmetrica: " + antisimmetrica);
		if(riflessiva && simmetrica && transitiva)
			System.out.println("Relazione di equivalenza: true");
		else
			System.out.println("Relazione di equivalenza: false");
		
		if(riflessiva && antisimmetrica && transitiva){
			System.out.println("Insieme parzialmente ordinato: true");
			String[] eleMax = Metodi.elementiMassimali(first, second);
			Metodi.stampaArray(eleMax, "Elementi massimali:");
			
			String[] eleMin = Metodi.elementiMinimali(first, second);
			Metodi.stampaArray(eleMin, "Elementi minimali:");
			
			boolean reticolo = Metodi.isReticolo(first, second);
			System.out.println("Reticolo: " + reticolo);
			
		}else
			System.out.println("Insieme parzialmente ordinato: false");
	}
}