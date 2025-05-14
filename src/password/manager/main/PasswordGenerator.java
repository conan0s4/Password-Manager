
package password.manager.main;

import java.security.SecureRandom;
// a class that provides a cryptographically strong random number generator.

//function : generates random psw

// so this package random (numbers) we be using the strings indexes here
public class PasswordGenerator { 
    //declare var that we will be using for the the generator
    String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowerCase = "abcdefghijklmnopqrstuvwxyz";
    String digits = "0123456789";
    String specialChars = "!@#$%^&*()-_=+[]{}|;:,.<>?/";
    String allCharacters = upperCase + lowerCase + digits + specialChars; //Combines all char into one string for random selection later

    // class instantiation - to be able to use in this class
    private static final SecureRandom random = new SecureRandom();

public String generate() { //method returns a string    
    int length = 12; //password length 
    StringBuilder password = new StringBuilder(); // string builder object for psw
    //https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html
    //https://www.youtube.com/watch?v=bzZZVXXHHqA
    //>> ((.append(this))add to string buildler object(password) (currently empty)
//    The charAt() method gets the character at the specified index in a string.
//https://metebalci.com/blog/everything-about-javas-securerandom/
    //random.nextint - generates random number   ?number of char in thestring(.length) (pick random number from 0 to .length)             
    password.append(upperCase.charAt(random.nextInt(upperCase.length())));
    password.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
    password.append(digits.charAt(random.nextInt(digits.length())));
    password.append(specialChars.charAt(random.nextInt(specialChars.length())));
// okey this section ensures atleast there is one of each of this to create a secure password -- if just random possible we'd be losing the required secure psw    
// first four char already been added here (|0|1|2|3|)
     //start at four             //increment - add one or move to next after appending
    for (int i = 4; i < length; i++) { // .append to password until reached to length (12)
// same logic from above                   
        password.append(allCharacters.charAt(random.nextInt(allCharacters.length()))); // this just  feels up the lenght required
    }

    return password.toString(); //convert to string  then return it --> results will be used in the pswmangerUI class
    
   }
}