package com.example.test_project.util.validators;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class UserValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

   public boolean isValideEmail(String email){
       return EMAIL_PATTERN.matcher(email).matches();
   }

   public String convertToCamelCase(String name){
       return name == null ? "" :
               List.of(name.trim().split("\\s+")).stream()
                       .map(word -> Character.toUpperCase(word.charAt(0))+ word.substring(1).toLowerCase())
                       .collect(Collectors.joining());
   }
}
