package com.xxx;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController1 {
	
	@GetMapping
	public String myMethod0() {
		return "index"; // src\main\resources\templates\index.html  -->p264 p137(p265)
	}
	@GetMapping("form1") //ch2-p65 ch3-p77  ch8-p139
	public String myMethod1() {
		return "form1"; // src\main\resources\templates\form1.html  -->p264 p137(p265)
	}
	@GetMapping("form2") //ch2-p65 ch3-p77  ch8-p139
	@ResponseBody
	public String myMethod2() {
		return "<b><font color = red>form2<b>"; // src\main\resources\templates\form1.html  -->p264 p137(p265)
	}
}
