package com.zhangyan.webflux.controller;

import com.zhangyan.webflux.domain.City;
import com.zhangyan.webflux.domain.User;
import com.zhangyan.webflux.fegin.UserService;
import com.zhangyan.webflux.handler.CityHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/city")
public class UserController {

    @Autowired
    private CityHandler cityHandler;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/{id}")
    public Mono<City> findCityById(@PathVariable("id") Long id) {
        return cityHandler.findCityById(id);
    }

    @GetMapping()
    public Mono<List<User>> findAllCity() {
        return userService.getAllUser().map(t -> {
            System.out.println("anbc.......");
            return t;
        });
    }

    @PostMapping()
    public Mono<Long> saveCity(@RequestBody City city) {
        return cityHandler.save(city);
    }

    @PutMapping()
    public Mono<Long> modifyCity(@RequestBody City city) {
        return cityHandler.modifyCity(city);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        return cityHandler.deleteCity(id);
    }
}
