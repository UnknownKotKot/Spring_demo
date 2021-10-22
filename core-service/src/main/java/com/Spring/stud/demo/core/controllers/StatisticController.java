package com.Spring.stud.demo.core.controllers;

import com.Spring.stud.demo.core.utils.AOP.UtilService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StatisticController {
    private final UtilService utilService;

    @GetMapping("/statistic")
    public void getStatistic() {
        System.out.println(utilService.getMap());
    }
}
