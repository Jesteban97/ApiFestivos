package ApiHolidayApplication.ApiHolidayApplication.interfaces;

import java.util.Date;
import java.util.List;

import ApiHolidayApplication.ApiHolidayApplication.models.HolidayOp;

public interface IHolidayService {

    public boolean isHoliday(Date date);

    public List<HolidayOp> getHoliday(int year);

    public boolean isVerifyHoliday(String holiday);

}