package com.library.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Annotate as Service class
@Service
public class MatchService {

    // Specify amount of search results to show per page
    public static final int SEARCH_RESULT_PER_PAGE = 10;

    // Inject instance of BookRepository as repo
    @Autowired
    private MatchRepository repo;

    // Define pagination to be used in controllers
    public Page<Match> search(String keyword, int pageNum) {
        // Interface for pagination, use PageRequest
        Pageable pageable = PageRequest.of(pageNum - 1, SEARCH_RESULT_PER_PAGE);
        return repo.search(keyword, pageable);
    }

    // Define list of student to be used in controllers
    public List<Match> listAll() {
        // Return all entries as List, use findAll() from CrudRepository
        return (List<Match>) repo.findAll();
    }

    // Define student saving to be used in controllers
    public void save(Match match) {
        // Save book
        repo.save(match);
    }

    // Define getting book by id to be used in controller
    // Throws BookNotFoundException if book is not found...
    // so we can do something about it later
    public Match get(Integer id) throws MatchNotFoundException {
        // Declare container object and attempt to retrieve entity Book by id
        Optional<Match> result = repo.findById(id);
        // Book found
        if (result.isPresent()) {
            // Get the book
            return result.get();
        }
        // Book not found, throw BookNotFoundException
        throw new MatchNotFoundException();
    }

    // Define deleting book by id to be used in controller
    // Like previously, we throw BookNotFoundException if book is not found
    public void delete(Integer id) throws MatchNotFoundException {
        // Use countById, as declared in BookRepository class
        // We find out if a book exists for that id
        Long count = repo.countById(id);
        // Book does not exist
        if (count == null || count == 0) {
            // Throw BookNotFoundException
            throw new MatchNotFoundException();
        }
        // Exception not thrown, book must exist...
        // delete it by its id using deleteById by CrudRepository
        repo.deleteById(id);
    }
}
