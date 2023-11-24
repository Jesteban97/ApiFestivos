package ApiHolidayApplication.ApiHolidayApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ApiHolidayApplication.ApiHolidayApplication.models.Holiday;

@Repository
public interface IHolidayRepository extends JpaRepository<Holiday, Integer> {

}