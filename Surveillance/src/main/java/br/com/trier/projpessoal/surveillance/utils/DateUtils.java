package br.com.trier.projpessoal.surveillance.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate strToLocalDate(String dateInicial) {
        return LocalDate.parse(dateInicial, formatter);
    }
    
    public static LocalDate strToLocalDateFinal(String dateFinal) {
        return LocalDate.parse(dateFinal, formatter);
    }

    public static String localDateToStr(LocalDate date) {
        return date.format(formatter);
    }
}
