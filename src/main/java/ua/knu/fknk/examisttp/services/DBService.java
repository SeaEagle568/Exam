package ua.knu.fknk.examisttp.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.knu.fknk.examisttp.models.Dish;
import ua.knu.fknk.examisttp.models.Place;
import ua.knu.fknk.examisttp.models.TopDish;
import ua.knu.fknk.examisttp.repositories.DishRepository;
import ua.knu.fknk.examisttp.repositories.PlaceRepository;

import java.util.*;

@Service
public class DBService {

    private final DishRepository dishRepository;
    private final PlaceRepository placeRepository;

    public DBService(@Autowired DishRepository dishRepository,
                     @Autowired PlaceRepository placeRepository) {
        this.dishRepository = dishRepository;
        this.placeRepository = placeRepository;
    }

    public List<TopDish> getTopDish() {
        return dishRepository.getTop10(PageRequest.of(0, 10)).toList();
    }

    public void addData() {
        List<Dish> dishes = generateDishes();
        dishRepository.saveAll(dishes);
        List<Place> places = generatePlaces(dishes);
        placeRepository.saveAll(places);
        dishRepository.saveAll(dishes);
    }

    private List<Place> generatePlaces(List<Dish> dishes) {
        List<Place> ans = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Collections.shuffle(dishes);
            ans.add(
                    new Place(
                            RandomStringUtils.randomAlphabetic(10),
                            RandomStringUtils.randomAlphabetic(25),
                            new HashSet<>(dishes.subList(0, new Random().nextInt(10) + 1)))
            );
        }
        return ans;
    }

    private List<Dish> generateDishes() {
        List<Dish> ans = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ans.add(
                    new Dish(
                            RandomStringUtils.randomAlphabetic(10),
                            RandomStringUtils.randomAlphabetic(25),
                            RandomStringUtils.randomAlphabetic(10))
            );
        }
        return ans;
    }
}
