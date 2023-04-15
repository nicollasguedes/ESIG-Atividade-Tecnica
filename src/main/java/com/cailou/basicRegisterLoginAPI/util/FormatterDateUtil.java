package com.cailou.basicRegisterLoginAPI.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatterDateUtil {

    public String formatterDate(){
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formatted = date.format(formatter);
        return formatted;
    }
}
