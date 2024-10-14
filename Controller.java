package com.example.multifileserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    private final Queries queries = new Queries();

    @PostMapping("/vulnerable-query")
    public List<String> vulnerableQuery(@RequestBody String userInput) {
        try {
            return queries.handleQueries("vulnerable", userInput);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/safe-query-int")
    public List<String> safeQueryInt(@RequestParam int id) {
        try {
            return queries.handleQueries("safe-int", String.valueOf(id));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/safe-query-constant")
    public List<String> safeQueryConstant() {
        try {
            return queries.handleQueries("safe-constant", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
