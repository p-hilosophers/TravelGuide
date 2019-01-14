package com.hilosophers.p.travelguide.Authentication;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataValidationTest {

    @Test
    public void checkIfPasswordsMatch() {
        String textPassword = "123456a", textConPassword = "123456a";
        DataValidation service = new DataValidation();

        assertTrue(service.checkIfPasswordsMatch(textPassword, textConPassword));
    }

    @Test
    public void checkIfPasswordIsStrong() {
       String textPassword = "1245a";
       DataValidation service = new DataValidation();

       assertFalse(service.checkIfPasswordIsStrong(textPassword));
    }

    @Test
    public void checkIfPasswordIsNull() {
        String textPassword = "";
        DataValidation service = new DataValidation();

        assertTrue(service.checkIfPasswordIsNull(textPassword));
    }

    @Test
    public void checkIfNameIsNull() {
        String textName = "Petros";
        DataValidation service = new DataValidation();

        assertFalse(service.checkIfNameIsNull(textName));
    }

    @Test
    public void checkIfSurnameIsNull() {
        String textSurname = "Xasapidis";
        DataValidation service = new DataValidation();

        assertFalse(service.checkIfSurnameIsNull(textSurname));
    }

    @Test
    public void checkIfEmailIsValid() {
        String textEmail = "mns4@gmailcom";
        DataValidation service = new DataValidation();

        assertFalse(service.checkIfEmailIsValid(textEmail));
    }

}