package com.mindstroid.mindstroidgameapplication.games.findindexsortedarray.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/remember-the-value-index")
public class FindIndexSortedArrayGameController {


    @PostMapping("/create")
    public void createGame() {

    }
}
