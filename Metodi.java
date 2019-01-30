import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Metodi{
	public static boolean checkParser(String stringa){
		int openB = getNumberSeparators("<", stringa);
		int closeB = getNumberSeparators(">", stringa);
		int comma = getNumberSeparators(",", stringa);
		if(openB != closeB)
			return false;
		
		if(comma != openB)
			return false; 
		
		return true;
	}
	public static int getNumberSeparators(String sep, String stringa){
		Pattern pattern = Pattern.compile("[^" + sep + "]*" + sep);
		Matcher matcher = pattern.matcher(stringa);
		int count = 0;
		while (matcher.find()) {
			count++;
		}
		return count;
	}
	
	public static boolean isRiflessiva(String[] a, String[] b){
		if(a.length != b.length)
			return false;
		
		for(int i = 0; i < a.length; i++){
			boolean simmetrico = false;
			for(int j = 0; j < a.length; j++){
				if(a[j].equals(a[i]))
					if(a[j].equals(b[j]))
						simmetrico = true;
			}
			if(!simmetrico)
				return false;
		}
		
		for(int i = 0; i < b.length; i++){
			boolean simmetrico = false;
			for(int j = 0; j < b.length; j++){
				if(b[j].equals(b[i]))
					if(b[j].equals(a[j]))
						simmetrico = true;
			}
			if(!simmetrico)
				return false;
		}
		return true;
	}
	
	public static boolean isSimmetrica(String[] a, String[] b){
		if(a.length != b.length)
			return false;
		
		for(int i = 0; i < a.length; i++){
			String sx = a[i];
			String dx = b[i];
			boolean simmetrico = false;
			for(int j = 0; j < a.length; j++){
				if(a[j].equals(b[i]) && b[j].equals(a[i]))
					simmetrico = true;
			}
			if(!simmetrico)
				return false;
		}
		return true;
	}
	
	public static boolean isTransitiva(String[] a, String[] b){
		if(a.length != b.length)
			return false;
		
		for(int i = 0; i < a.length; i++){
			String sx = a[i];
			String dx = b[i];
			//System.out.println("Start: " + sx + " " +dx);
			boolean transitivo = false;
			for(int j = 0; j < a.length; j++){
				String dx1 = "";
				transitivo = false;
					if(a[j].equals(dx)){
						dx1 = b[j];
						//System.out.println("Under: " + dx + " " +dx1);
						for(int k = 0; k < a.length; k++){
							if(a[k].equals(sx) && b[k].equals(dx1)){
								transitivo = true;
								//System.out.println(a[k] + " " +b[k]); 
							}			
						}
						if(!transitivo)
							return false;
					}	
			}
			
		}
		return true;
	}
	
	public static boolean isAntisimmetrica(String[] a, String[] b){
		if(a.length != b.length)
			return false;
		
		for(int i = 0; i < a.length; i++){
			String sx = a[i];
			String dx = b[i];
			for(int j = 0; j < a.length; j++){
				if(a[j].equals(dx) && (i != j))
					if(b[j].equals(sx))
						return false;
			}
		}
		return true;
	}
	
	public static String[] elementiMassimali(String[] a, String[] b){
		if(a.length != b.length)
			return null;
		
		String[] array = new String[a.length];
		int index = 0;
		
		for(int i = 0; i < a.length; i++){
			boolean continua = true;
			if(!(a[i].equals(b[i])))
				continua = false;
			
			for(int j = 0; j < a.length && continua; j++){
				if(a[j].equals(a[i]) && i != j){
					if(!(a[j].equals(b[j])))
						continua = false;
				}
			}
			if(continua){
				array[index] = a[i];
				index++;
			}
		}
		if(index == 0)
			return null;
		
		return array;
	}
	
	public static String[] elementiMinimali(String[] a, String[] b){
		if(a.length != b.length)
			return null;
		
		String[] array = new String[a.length];
		int index = 0;
		
		for(int i = 0; i < b.length; i++){
			boolean continua = true;
			if(!(b[i].equals(a[i])))
				continua = false;
			
			for(int j = 0; j < b.length && continua; j++){
				if(b[j].equals(b[i]) && i != j){
					if(!(b[j].equals(a[j])))
						continua = false;
				}
			}
			if(continua){
				array[index] = b[i];
				index++;
			}
		}
		if(index == 0)
			return null;
		
		return array;
	}
	
	public static boolean isReticolo(String[] a, String[] b){
		if(a.length != b.length)
			return false;
		
		for(int i = 0; i < a.length; i++){
			for(int j = i+1; j < b.length; j++){
				if(!(checkJoin(a, b, a[i], b[j]) && checkMeet(a, b, a[i], b[j])))
					return false;
			}
		}
		return true;
	}
	
	public static boolean checkJoin(String[] a, String[] b, String e1, String e2){
		for(int i = 0; i < a.length; i++){
			if(a[i].equals(e1)){
				String dx = b[i];
				for(int j = 0; j < a.length; j++){
					if(a[j].equals(e2))
						if(b[j].equals(dx))
							return true;
				}
			}
		}
		return false;
	}
	
	public static boolean checkMeet(String[] a, String[] b, String e1, String e2){
		for(int i = 0; i < b.length; i++){
			if(b[i].equals(e1)){
				String sx = a[i];
				for(int j = 0; j < b.length; j++){
					if(b[j].equals(e2))
						if(a[j].equals(sx))
							return true;
				}
			}
		}
		return false;
	}
	
	public static boolean checkInsieme(String[] insieme, String[] elementiRelazione){
		for(int i = 0; i < elementiRelazione.length; i++){
			boolean equal = false;
			for(int j = 0; j < insieme.length; j++){
				if(elementiRelazione[i].equals(insieme[j]))
					equal = true;
			}
			if(!equal)
				return false;
		}
		return true;
	}
	
	public static void stampaArray(String[] array, String msg){
		if(array == null)
			return;
		
		System.out.println(msg);
		for(int i = 0; i < array.length; i++){
			if(array[i] != null)
				System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	// Funziona solo se la Relazione è un Insieme parzialmente ordinato (poset), restituisce true se la coppia ordinata deriva dalla transitività, false altrimenti.
	public static boolean coppiaTransitiva(String a1, String b1, String[] a, String[] b){
		if(a.length != b.length)
			return false;
		
		for(int i = 0; i < b.length; i++){
			if(!a[i].equals(b[i])){
				if(b[i].equals(b1) && !a[i].equals(a1)){
					String sx = a[i];
					for(int j = 0; j < a.length; j++){
						if(a[j].equals(a1) && b[j].equals(sx))
							return true;
					}
				}
			}	
		}
		return false;
	}
}