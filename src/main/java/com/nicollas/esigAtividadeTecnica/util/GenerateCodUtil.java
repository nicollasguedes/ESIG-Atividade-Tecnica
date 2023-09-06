package com.nicollas.esigAtividadeTecnica.util;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateCodUtil {

    public String generateCode() {
        int length = 7;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generated = RandomStringUtils.random(length, useLetters, useNumbers);
        return generated.toUpperCase().trim();

    }

    public  String codePayment() {
        int length = 7;
        boolean useLetters = false;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);

    }
}
