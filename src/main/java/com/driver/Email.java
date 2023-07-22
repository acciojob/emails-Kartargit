package com.driver;

public class Email {

    private String emailId;
    private String password;
    public Email(){
    }
    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        String currPassword = getPassword();
        if(currPassword.equals(oldPassword)){
            int n = newPassword.length();
            if(n<8){
                System.out.println("Entered Password length is less than 8");
                return;
            } else if (n>=8) {
                if(isValid(newPassword)){
                    setPassword(newPassword);
                    System.out.println("Password has been changed successfully");
                    return;
                }

                System.out.println("Entered Password is inValid");
                return;

            }
        }
            System.out.println("Entered Password does not match with current password");
            return;

    }
    public static boolean isValid(String password){
        boolean upCase = false, lowCase = false, digit = false, specChar = false;
        for(int i=0;i<password.length();i++){
            int asc = (int)password.charAt(i);
            if(!specChar){
                if((asc<=47&&asc>=33)||(asc<=64&&asc>=58)||(asc<=96&&asc>=91)||(asc<=126&&asc>=123))specChar = true;
            }
            if(!digit){
                if(asc>=48&&asc<=57)digit = true;
            }
            if(!upCase){
                if(asc>=65&&asc<=90)upCase = true;
            }
            if(!lowCase){
                if(asc>=97&&asc<=122)lowCase = true;
            }
            if(specChar&&digit&&lowCase&&upCase)return true;
        }
        return false;
    }
}
