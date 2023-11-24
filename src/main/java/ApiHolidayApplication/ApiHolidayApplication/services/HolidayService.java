package ApiHolidayApplication.ApiHolidayApplication.services;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ApiHolidayApplication.ApiHolidayApplication.interfaces.IHolidayService;
import ApiHolidayApplication.ApiHolidayApplication.models.Holiday;
import ApiHolidayApplication.ApiHolidayApplication.models.HolidayOp;
import ApiHolidayApplication.ApiHolidayApplication.repository.IHolidayRepository;

@Service
public class HolidayService implements IHolidayService {

    @Autowired
    IHolidayRepository repository;

    private Date getSundayEaster(int year) {
        int month, day, A, B, C, D, E, M, N;
        M = 0;
        N = 0;
        if (year >= 1583 && year <= 1699) {
            M = 22;
            N = 2;
        } else if (year >= 1700 && year <= 1799) {
            M = 23;
            N = 3;
        } else if (year >= 1800 && year <= 1899) {
            M = 23;
            N = 4;
        } else if (year >= 1900 && year <= 2099) {
            M = 24;
            N = 5;
        } else if (year >= 2100 && year <= 2199) {
            M = 24;
            N = 6;
        } else if (year >= 2200 && year <= 2299) {
            M = 25;
            N = 0;
        }

        A = year % 19;
        B = year % 4;
        C = year % 7;
        D = ((19 * A) + M) % 30;
        E = ((2 * B) + (4 * C) + (6 * D) + N) % 7;

        // Decidir entre los 2 casos
        if (D + E < 10) {
            day = D + E + 22;
            month = 3; // Marzo
        } else {
            day = D + E - 9;
            month = 4; // Abril
        }

        // Excepciones especiales
        if (day == 26 && month == 4)
            day = 19;
        if (day == 25 && month == 4 && D == 28 && E == 6 && A > 10)
            day = 18;
        return new Date(year - 1900, month - 1, day);
    }

    private Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); // minus number would decrement the days
        return cal.getTime();
    }

    private Date nextMonday(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        if (c.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY)
            fecha = addDays(fecha, 9 - c.get(Calendar.DAY_OF_WEEK));
        else if (c.get(Calendar.DAY_OF_WEEK) < Calendar.MONDAY)
            fecha = addDays(fecha, 1);
        return fecha;
    }

    private List<Holiday> calculateHolidays(List<Holiday> holidays, int year) {
        if (holidays != null) {
            Date easter = getSundayEaster(year);
            int i = 0;
            for (final Holiday holiday : holidays) {
                switch (holiday.getType().getId()) {
                    case 1:
                        holiday.setDate((new Date(year - 1900, holiday.getMonth() - 1, holiday.getDay())));
                        break;
                    case 2:
                        holiday.setDate(nextMonday(new Date(year - 1900, holiday.getMonth() - 1, holiday.getDay())));
                        break;
                    case 3:
                        holiday.setDate(addDays(easter, holiday.getEasterDay()));
                        break;
                    case 4:
                        holiday.setDate(nextMonday(addDays(easter, holiday.getEasterDay())));
                        break;
                }
                holidays.set(i, holiday);
                i++;
            }
        }
        return holidays;
    }

    @Override
    public List<HolidayOp> getHoliday(int year) {
        List<Holiday> holidays = repository.findAll();
        holidays = calculateHolidays(holidays, year);
        List<HolidayOp> dates = new ArrayList<HolidayOp>();
        for (final Holiday holiday : holidays) {
            dates.add(new HolidayOp(holiday.getName(), holiday.getDate()));
        }
        return dates;
    }

    private boolean equalDates(Date date1, Date date2) {
        return date1.equals(date2);
    }

    private boolean isHoliday(List<Holiday> holidays, Date date) {
        if (holidays != null) {
            holidays = calculateHolidays(holidays, date.getYear() + 1900);
            for (final Holiday holiday : holidays) {
                if (equalDates(holiday.getDate(), date))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean isHoliday(Date date) {
        List<Holiday> holidays = repository.findAll();
        return isHoliday(holidays, date);
    }

    @Override
    public boolean isVerifyHoliday(String strFecha) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            df.parse(strFecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
