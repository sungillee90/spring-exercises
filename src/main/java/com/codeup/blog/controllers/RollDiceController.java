package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDice(@PathVariable int n, Model model){
        // StackOverFlow https://stackoverflow.com/questions/5887709/getting-random-numbers-in-java
        int randomNumberGenerator = (int) (Math.random() * 6 + 1);

        boolean correct = randomNumberGenerator == n;

        model.addAttribute("numberGuess", n);
        model.addAttribute("randomNumber",randomNumberGenerator);
        model.addAttribute("correct", correct);
        return "roll-dice";
    }


}
