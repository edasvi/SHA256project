package cs210proj2;
import java.util.*;
import java.security.*;
import java.util.Random;
public class CS210Project   {

	public static void main(String[] args) {
		
		Scanner scan= new Scanner(System.in); //sets the size of the array
		int m=scan.nextInt();
		
		String input[] = new String [m]; //blank array to load in
        int topscore=0;		//highest match
        String sco1="";
        String sco2="";		// saves highest matching Strings
        
    		   
       for(int i=0;i<input.length;i++)
       {
    	   input[i]=wordgenerator();//fills with random sentences 
    	   
       }
       scan.close();
     for(int i=0;i<input.length;i++) //one element 
      {
   	  
   	   for(int j=i+1;j<input.length;j++) // to compare i element with all following elements 
   	   {
   		  
   		   int comparison=compare2(input[i],input[j]);		//obtains score
   		   if(comparison>topscore&&input[i].equals(input[j])==false)		//check for highest matches and prevents duplicates
   		   {
   			   topscore=comparison;
   			   sco1=input[i];
   			   sco2=input[j];
   		   }
   	   }
      }
	   System.out.println(sco1); 
	   System.out.println(sco2);
	   System.out.println(topscore);
	}
	
	public static String sha256(String input){
        try{
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] salt = "CS210+".getBytes("UTF-8");
            mDigest.update(salt);
            byte[] data = mDigest.digest(input.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i=0;i<data.length;i++){
                sb.append(Integer.toString((data[i]&0xff)+0x100,16).substring(1));
            }
            return sb.toString();
        }catch(Exception e){
            return(e.toString());
        }
}
	public static int compare2(String c,String d)		// takes  in two strings to compare sha-256 hashes 
	{
        String a=sha256(c); //call sha method to temporarily save 
        String b=sha256(d);
        int count = 0;
     
                for(int i = 0; i< 64; i++)		//64 is sha length 
                {
              if(a.charAt(i)==b.charAt(i)){			// checks for matches 
                  count++;
              }   
          }    
return count; //returns number of matches 
}
	public static String wordgenerator()
	{
		Random rand= new Random();
		String[] noun= {"cat ","dog ","baby ","family ","student ","idol ","teacher ","kid ","child ", "pet ","doctor ","soldier ","relative ","friend ","favourite ","beloved ","colleague ","lord ","priest ","neighbour "};//list of nouns
		String[] verb= {"jumps","runs","plays","fights","sits","sleeps","visits","rests","stays","shares","speaks","interacts","messes","jokes"};//list of verbs
		String[] possessive= {"his ","her ","your ","their ","our ","the "};//list of  pronouns
		String[] time= {"", "frequently ","rarely ","never ","always ","sometimes ","barely ",};//list of adverbs

		String[] place= {"","at home","at school","outside","in the garden","at work","inside","at the party","at the meeting",};//list of places 

		String e= possessive[rand.nextInt(possessive.length)];
// start of sentence , created to be capitalised instead of calling possessive array again
		e=e.substring(0, 1).toUpperCase() + e.substring(1);
// capitalises 

		return e+noun[rand.nextInt(noun.length)]+ time[rand.nextInt(time.length)]+verb[rand.nextInt(verb.length)]+" with " + possessive[rand.nextInt(possessive.length)] +noun[rand.nextInt(noun.length)]+place[rand.nextInt(place.length)]+ ".";
		//uses rand.nextInt() to ensure randomness and uses length to prevent array out of bound error 
	}
}

