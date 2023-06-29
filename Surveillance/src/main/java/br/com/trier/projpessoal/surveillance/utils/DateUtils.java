package br.com.trier.projpessoal.surveillance.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static ZonedDateTime strToZonedDateTime(String dateInitial) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateInitial, formatter);
        return ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
    }

    public static String zonedDateTimeToStr(ZonedDateTime dateTime) {
        return formatter.format(dateTime);
    }

    public static ZonedDateTime[] strToZonedDateTime(String dateInitial, String dateFinal) {
        LocalDateTime localDateTimeInitial = LocalDateTime.parse(dateInitial, formatter);
        LocalDateTime localDateTimeFinal = LocalDateTime.parse(dateFinal, formatter);

        ZonedDateTime zonedDateTimeInitial = ZonedDateTime.of(localDateTimeInitial, ZoneId.systemDefault());
        ZonedDateTime zonedDateTimeFinal = ZonedDateTime.of(localDateTimeFinal, ZoneId.systemDefault());

        return new ZonedDateTime[]{zonedDateTimeInitial, zonedDateTimeFinal};
    }
}
