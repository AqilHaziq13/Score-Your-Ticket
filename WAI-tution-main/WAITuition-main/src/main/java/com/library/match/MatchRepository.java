package com.library.match;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Annotate as Repository class
@Repository
// We extend JpaRepository because we require...
// PagingAndSortingRepository for pagination
// CrudRepository for CRUD
public interface MatchRepository extends JpaRepository<Match, Integer>{

    // Annotate as query
    // We create a custom query to implement full text searches for columns...
    // isbn, title, genre
    // Here, nativeQuery = true means it is a native database query (MySQL)
    @Query(value = "SELECT * FROM matches WHERE MATCH(teamO, teamT, date_time) "
            + "AGAINST (?1)", nativeQuery = true)

    // Declare to used in service class
    public Page<Match> search(String keyword, Pageable pageable);
    public Long countById(Integer id);

    Optional<Match> findById(Integer id);

    void deleteById(Integer id);
}
