package dev.guarmo.crmstat.service;

import dev.guarmo.crmstat.util.UtilFactory;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DateService {
    @Named("mapFromLocalDateToString")
    public String mapFromLocalDateToString(LocalDateTime localDateTime) {
        return localDateTime.format(UtilFactory.WITHOUT_HOURS_FORMATTER);
    }

    @Named("mapFromStringToLocalDate")
    public LocalDateTime mapFromStringToLocalDate(String date) {
        return LocalDateTime.parse(date, UtilFactory.DEFAULT_FORMATTER);
    }
}
