
package password.manager.main;

public class SecurePasswordRule {
// will be use in our psw managerui class   && loginui  to make sure users password is strong
    
    public boolean isPasswordStrong(String password) { //method with argument
    if (password.length() < 8) { //checks password length
        return false; //emmediately returns false and doesnt check for the other rules so it returns it
        
    } //to check:  on default its false
    boolean hasUpper = false;
    boolean hasLower = false;
    boolean hasDigit = false;
    boolean hasSpecial = false;

    //java.lang package (character class) automatically imported (no need to specify)
    //requires user to atleast have one of each (&& and [must have])
    /*
    uppercase
    lowercase
    didgit
    special character
    */ //converts to arrays of charracters
    for (char c : password.toCharArray()) { //for each loop
        if (Character.isUpperCase(c)) { //if this current character is an uppercase then (true) //samelogic for the other string method
            hasUpper = true;   
        } else if (Character.isLowerCase(c)) {
            hasLower = true;
        } else if (Character.isDigit(c)) {
            hasDigit = true;
        } else if ("!@#$%^&*()_+-=[]{}|;:',.<>?/`~".indexOf(c) >= 0) {  //string of special char
            hasSpecial = true;    //.index of //so it says with this array of string currently on this character is "specific string i stated"
        } //string methods
    }
//must each all return true  for it to return true
//if all requirements were followed it returns true 
//returns false if one of the variable returns false or rather stayed false 
    return hasUpper && hasLower && hasDigit && hasSpecial;
    //return   return types (identifiers)
    // that returns the result and be used in our other class
}
  
    
}
