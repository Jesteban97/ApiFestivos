package ApiHolidayApplication.ApiHolidayApplication.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import ApiHolidayApplication.ApiHolidayApplication.interfaces.IHolidayService;
import ApiHolidayApplication.ApiHolidayApplication.models.HolidayOp;

@RestController
@RequestMapping("/holidays")
@CrossOrigin(origins = "*")
public class HolidayController {

    @Autowired
    private IHolidayService service;

    @CrossOrigin(origins = "*")
    @GetMapping("/verificar/{year}/{month}/{day}")
    public ResponseEntity<String> verificarFestivo(@PathVariable int year, @PathVariable int month,
            @PathVariable int day) {
        String dateString = String.format("%d-%02d-%02d", year, month, day);

        if (service.isVerifyHoliday(dateString)) {
            Date date = Date.valueOf(LocalDate.of(year, month, day));

            String response = service.isHoliday(date) ? "Es Festivo" : "No es festivo";
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fecha No valida");
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/obtener/{year}")
    public ResponseEntity<List<HolidayOp>> obtener(@PathVariable int year) {
        List<HolidayOp> holidays = service.getHoliday(year);
        return ResponseEntity.ok(holidays);
    }
}
