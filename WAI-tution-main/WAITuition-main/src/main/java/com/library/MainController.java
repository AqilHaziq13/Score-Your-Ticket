/**
 * Controller class for Main
 */
package com.library;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Annotate as controller
@Controller
public class MainController {

    // Map HTTP GET requests for '/'
    @GetMapping
    public String showHomePage(){

        // Return templates/index.html
        return "index";
    }

}