package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{a}/and/{b}")
    @ResponseBody
    public String doMathAdd(@PathVariable int a, @PathVariable int b) {
        return "Addition of two input values = " + (a + b);
    }

    @GetMapping("/subtract/{a}/from/{b}")
    @ResponseBody
    public String doMathSub(@PathVariable int a, @PathVariable int b) {
        return "Subtraction a from b = " + (b - a);
    }

    @GetMapping("/multiply/{a}/and/{b}")
    @ResponseBody
    public String doMathMulti(@PathVariable int a, @PathVariable int b) {
        return "Multiplication a and b = " + (a * b);
    }
    @GetMapping("/divide/{a}/by/{b}")
    @ResponseBody
    public String doMathDiv(@PathVariable int a, @PathVariable int b) {
        return "Division a by b = " + (a / b);
    }
}
