package br.com.trier.projpessoal.surveillance.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate strToLocalDate(String date) {
        return LocalDate.parse(date, formatter);
    }

    public static String localDateToStr(LocalDate date) {
        return formatter.format(date);
    }
}
