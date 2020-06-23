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
        //StackOverFlow https://stackoverflow.com/questions/5887709/getting-random-numbers-in-java
        //Compare the guess # to the random number.
        //Store if they guessed correctly in and attribute.
        int randomNumberGenerator = (int) (Math.random() * 6 + 1);

        model.addAttribute("isCorrectGuess", randomNumberGenerator == n);
        model.addAttribute("myNumberGuess", n);
        model.addAttribute("randomNumber",randomNumberGenerator);

        return "roll-dice";
    }


}
