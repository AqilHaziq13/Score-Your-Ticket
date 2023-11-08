/**
 * Controller class for Admin
 */
package com.library.user.auth;

import com.library.match.Match;
import com.library.match.MatchNotFoundException;
import com.library.match.MatchService;
import com.library.ticket.Ticket;
import com.library.ticket.TicketNotFoundException;
import com.library.ticket.TicketService;
import com.library.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// Annotate as controller
@Controller
public class AdminController {

    // Inject instance of UserService
    @Autowired
    private UserService userService;

    // Inject instance of studentService
    @Autowired
    private MatchService matchService;

    @Autowired
    private TicketService ticketService;

    // Map HTTP GET requests for '/admin/student'
    @GetMapping("/admin/matches")
    public String adminMatch(Model model) {
        // Use listAll() from studentService service class...
        // to get a List of student
        List<Match> listMatches = matchService.listAll();
        // Store List of student in liststudent attribute, so we can populate it in Thymeleaf
        model.addAttribute("listMatch", listMatches);
        // Get size of List of student, so that we know how many entries in the entity
        model.addAttribute("totalItems", listMatches.size());


        return "admin/matches";
    }

    // Map HTTP POST requests for "/admin/student/add"
    @PostMapping("/admin/matches/save")
    // This url accepts POST requests to "save" the book
    public String saveMatch(Match match) {
        // Saves the POSTed book entity using instance of studentService
        matchService.save(match);

        // Return a redirect to "/admin/student?add"
        return "redirect:/admin/matches?add";
    }

    // Map HTTP GET requests for '/admin/student/add'
    @GetMapping("/admin/matches/add")
    public String adminAddMatches(Model model) {
        // Return template file with add book form, that's it.

        return "admin/addMatches";
    }

    // Map HTTP GET requests for '/admin/student/edit/{id}'
    @GetMapping("/admin/matches/edit/{id}")
    public String adminEditMatches(@PathVariable("id") Integer id, Model model) {
        // Wrap in try-catch block, because we might not find a book for the id
        try {
            // Get the book by its id
            Match match = matchService.get(id);
            // Store the book entity in attribute to be used in Thymeleaf
            model.addAttribute("matches", match);
            // Customise a page title, so that we can dynamically display...
            // book ID in Thymeleaf
            model.addAttribute("pageTitle", "Edit match (ID: " + id + ")");


            return "admin/edit_matches";
            // Book not found
        } catch (MatchNotFoundException e) {
            // Return redirect to list of student with parameter to show error
            return "redirect:/admin/matches?notExist";
        }
    }
    // Map HTTP GET requests for '/admin/student/delete/{id}'
    @GetMapping("/admin/matches/delete/{id}")
    public String adminDeleteMatches(@PathVariable("id") Integer id, Model model) {
        // Wrap in try-catch block, because we might not find a book for the id
        try {
            // Delete the book by its id
            matchService.delete(id);

            // Success, redirect to list of student with a param for message
            return "redirect:/admin/matches?deleted={id}";
            // Book not found
        } catch (MatchNotFoundException e) {
            // Redirect to list of student with a param for message
            return "redirect:/admin/matches?notExist";
        }
    }

    // Map HTTP GET requests for '/admin/student/search'
    @GetMapping("/admin/matches/search")
    // User will request page with keyword parameter, so we accept keyword as parameter
    public String search(String keyword, Model model) {
        // Call searchByPage with search keyword, model container, and result page 1
        // We assume if user arrives to this url, they are on result page 1
        return searchByPage(keyword, model, 1);
    }

    // Map HTTP GET requests for '/admin/student/search/page/{pageNum}'
    @GetMapping("/admin/matches/search/page/{pageNum}")
    public String searchByPage(String keyword, Model model,
                               @PathVariable(name = "pageNum") int pageNum) {

        // Get current page search results
        Page<Match> result = matchService.search(keyword, pageNum);

        // Store contents of current page results in List
        List<Match> listResult = result.getContent();

        // Add attributes to model container so that we can use it in Thymeleaf
        // Get pages total amount of search results
        model.addAttribute("totalPages", result.getTotalPages());
        // Get total amount of results from search
        model.addAttribute("totalItems", result.getTotalElements());
        // Get the current page number
        model.addAttribute("currentPage", pageNum);

        // Start counter to ensure only specified amount of results are displayed per page
        long startCount = (pageNum - 1) * MatchService.SEARCH_RESULT_PER_PAGE + 1;
        // Get current start counter value
        model.addAttribute("startCount", startCount);

        // Determine when to stop displaying results.
        long endCount = startCount + MatchService.SEARCH_RESULT_PER_PAGE - 1;
        // We are probably on last page of search, but...
        // endCount cannot exceed total amount of results from search
        if (endCount > result.getTotalElements()) {
            // set counter to end at total amount of results
            endCount = result.getTotalElements();
        }

        // Store as attribute to be used in Thymeleaf
        // Get endCount value as attribute
        model.addAttribute("endCount", endCount);
        // Get List of search results for current page
        model.addAttribute("listResult", listResult);
        // Get keyword used for search
        model.addAttribute("keyword", keyword);


        return "admin/searchMatches";
    }
    //________________________________________________________________________________________________________________________________________________________________
    // Map HTTP GET requests for '/admin/student'
    @GetMapping("/admin/ticket")
    public String adminTicket(Model model) {
        // Use listAll() from studentService service class...
        // to get a List of student
        List<Ticket> listResultSub= ticketService.listAll();
        // Store List of student in liststudent attribute, so we can populate it in Thymeleaf
        model.addAttribute("listTicket", listResultSub);
        // Get size of List of student, so that we know how many entries in the entity
        model.addAttribute("totalItems", listResultSub.size());


        return "admin/ticket";
    }

    // Map HTTP POST requests for "/admin/student/add"
    @PostMapping("/admin/ticket/save")
    // This url accepts POST requests to "save" the book
    public String saveTicket(Ticket ticket) {
        // Saves the POSTed book entity using instance of studentService
        ticketService.save(ticket);

        // Return a redirect to "/admin/student?add"
        return "redirect:/admin/ticket?add";
    }

    // Map HTTP GET requests for '/admin/student/add'
    @GetMapping("/admin/ticket/add")
    public String adminAddTicket(Model model) {
        // Return template file with add book form, that's it.

        return "admin/addTicket";
    }

    // Map HTTP GET requests for '/admin/subject/edit/{id}'
    @GetMapping("/admin/ticket/edit/{id}")
    public String adminEditTicket(@PathVariable("id") Integer id, Model model) {
        // Wrap in try-catch block, because we might not find a book for the id
        try {
            // Get the book by its id
            Ticket ticket = ticketService.get(id);
            // Store the book entity in attribute to be used in Thymeleaf
            model.addAttribute("ticket", ticket);
            // Customise a page title, so that we can dynamically display...
            // book ID in Thymeleaf
            model.addAttribute("pageTitle", "Edit ticket (ID: " + id + ")");


            return "admin/editTicket";
            // Book not found
        } catch (TicketNotFoundException e) {
            // Return redirect to list of student with parameter to show error
            return "redirect:/admin/ticket?notExist";
        }
    }
    // Map HTTP GET requests for '/admin/student/delete/{id}'

    @GetMapping("/admin/ticket/delete/{id}")
    public String adminDeleteTicket(@PathVariable("id") Integer id, Model model) {
        // Wrap in try-catch block, because we might not find a book for the id
        try {
            // Delete the book by its id
            ticketService.delete(id);

            // Success, redirect to list of student with a param for message
            return "redirect:/admin/ticket?deleted={id}";
            // Book not found
        } catch (TicketNotFoundException e) {
            // Redirect to list of student with a param for message
            return "redirect:/admin/ticket?notExist";
        }
    }



    // Map HTTP GET requests for '/admin/student/search'
    @GetMapping("/admin/ticket/ticketSearch")
    // User will request page with keyword parameter, so we accept keyword as parameter
    public String searchsub(String keyword, Model models) {
        // Call searchByPage with search keyword, model container, and result page 1
        // We assume if user arrives to this url, they are on result page 1
        return searchByPage2(keyword, models, 1);
    }

    // Map HTTP GET requests for '/admin/student/search/page/{pageNum}'
    @GetMapping("/admin/ticket/ticketSearch/page/{pageNum}")
    public String searchByPage2(String keyword, Model model,
                                @PathVariable(name = "pageNum") int pageNum) {

        // Get current page search results
        Page<Ticket> result = ticketService.search(keyword, pageNum);

        // Store contents of current page results in List
        List<Ticket> listResult = result.getContent();

        // Add attributes to model container so that we can use it in Thymeleaf
        // Get pages total amount of search results
        model.addAttribute("totalPages", result.getTotalPages());
        // Get total amount of results from search
        model.addAttribute("totalItems", result.getTotalElements());
        // Get the current page number
        model.addAttribute("currentPage", pageNum);

        // Start counter to ensure only specified amount of results are displayed per page
        long startCount = (pageNum - 1) * TicketService.SEARCH_RESULT_PER_PAGE + 1;
        // Get current start counter value
        model.addAttribute("startCount", startCount);

        // Determine when to stop displaying results.
        long endCount = startCount + TicketService.SEARCH_RESULT_PER_PAGE - 1;
        // We are probably on last page of search, but...
        // endCount cannot exceed total amount of results from search
        if (endCount > result.getTotalElements()) {
            // set counter to end at total amount of results
            endCount = result.getTotalElements();
        }

        // Store as attribute to be used in Thymeleaf
        // Get endCount value as attribute
        model.addAttribute("endCount", endCount);
        // Get List of search results for current page
        model.addAttribute("listResult", listResult);
        // Get keyword used for search
        model.addAttribute("keyword", keyword);


        return "admin/searchTicket";
    }


}
