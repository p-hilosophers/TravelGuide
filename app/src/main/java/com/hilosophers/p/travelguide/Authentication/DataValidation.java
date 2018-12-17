package com.hilosophers.p.travelguide.Authentication;

public class DataValidation {
    private String message="";

    private boolean checkIfPasswordsMatch(String pass , String conPass){
        boolean match = false;
        if(pass.equals(conPass)) {
            match = true;
        }
        return match;
    }

    private boolean checkIfPasswordIsStrong(String pass){
        boolean strongPass = false;
        if(pass.length() >= 6) {
            strongPass = true;
        }
        return strongPass;
    }

    private boolean checkIfPasswordIsNull(String pass){
        boolean nullPass = false;
        if(pass.isEmpty()) {
            nullPass = true;
        }
        return  nullPass;
    }

    private boolean checkIfNameIsNull(String name){
        boolean nullName = false;
        if(name.isEmpty()) {
            nullName = true;
        }
        return  nullName;
    }

    private boolean checkIfSurnameIsNull(String surname){
        boolean nullName = false;
        if(surname.isEmpty()) {
            nullName = true;
        }
        return  nullName;
    }

    private boolean checkIfEmailIsValid(String email){
        boolean validEmail = false;
        if(email.contains("@") && email.contains(".com")) {
            validEmail = true;
        }
        return  validEmail;
    }

    public boolean checkValidation(String textName, String textSurname, String textEmail, String textPassword,String textConPassword){
        boolean allClear = true;
        int passCode = 0;
        int matchCode = 0;

        DataValidation service = new DataValidation();

        if(service.checkIfNameIsNull(textName)){
            allClear = false;
            checkWarningStatus("The name field is empty !");
        }

        if(service.checkIfSurnameIsNull(textSurname)){
            allClear = false;
            checkWarningStatus("The surname field is empty !");
        }

        if(service.checkIfPasswordIsNull(textPassword)) {
            allClear = false;
            passCode = 1;
            checkWarningStatus("You must set a password !");
        }

        if(!service.checkIfPasswordsMatch(textPassword,textConPassword)) {
            allClear = false;
            matchCode = 1;
            checkWarningStatus("Password fields do not match !");
        }

        if(!service.checkIfPasswordIsStrong(textPassword)){
            allClear = false;
            if(passCode != 1 && matchCode !=1){
                checkWarningStatus("The inserted password must be at least 6 characters long");}
        }

        if(!service.checkIfEmailIsValid(textEmail)){
            allClear = false;
            checkWarningStatus("The email you entered is invalid !");
        }

        return allClear;
    }


    public String checkWarningStatus(String alert){
        message+=alert+"\n";
        return  message;
    }

    public String clearMessage(){
        message="";
        return message;
    }

}
