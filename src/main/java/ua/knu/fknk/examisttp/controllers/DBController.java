package ua.knu.fknk.examisttp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.knu.fknk.examisttp.models.Place;
import ua.knu.fknk.examisttp.models.TopDish;
import ua.knu.fknk.examisttp.services.DBService;
import java.util.List;

@RestController
public class DBController {

    private final DBService service;

    public DBController(@Autowired DBService service) {
        this.service = service;
    }

    @GetMapping("/data/get/10")
    public String getTop10Dishes() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        List<TopDish> topDishList = service.getTopDish();
        for (TopDish topDish : topDishList) {
            builder.append("\"").append(topDish.getName()).append("\"")
                    .append(":").append(topDish.getCount()).append(",");
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append("}");
        return builder.toString();
    }

    @PostMapping("/data/add")
    public ResponseEntity<String> postMapping() {
        service.addData();
        return new ResponseEntity<>("OK", HttpStatusCode.valueOf(201));
    }
}
