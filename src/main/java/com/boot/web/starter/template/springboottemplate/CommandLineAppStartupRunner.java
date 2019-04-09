package com.boot.web.starter.template.springboottemplate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println(
				"Application started with command-line arguments. \n To kill this application, press Ctrl + C. "
				+ Arrays.toString(args));
	}

}
