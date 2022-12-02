package ua.knu.fknk.examisttp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.knu.fknk.examisttp.models.Dish;
import ua.knu.fknk.examisttp.models.TopDish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    @Query("select new ua.knu.fknk.examisttp.models.TopDish(d.name, count(d)) from Dish d left join d.place p group by d.dishId order by count(d) desc")
    Page<TopDish> getTop10(Pageable pageable);
}
