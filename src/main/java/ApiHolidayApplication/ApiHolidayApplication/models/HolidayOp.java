package ApiHolidayApplication.ApiHolidayApplication.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HolidayOp {

    private String holiday;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public HolidayOp() {
    }

    public HolidayOp(String holiday, Date date) {
        this.holiday = holiday;
        this.date = date;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}