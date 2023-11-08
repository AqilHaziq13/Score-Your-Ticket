package com.library.match;

import javax.persistence.*;

// Annotate POJO as entity to represent a database entity/object.
@Entity()
// Specify primary table for entity
@Table(name = "matches")

public class Match {

    // Specify id as primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
     * @Column annotation
     * nullable = specify whether column can be empty/null
     * unique = specify if column content must be unique
     * length = specify column's amount of characters
     */

    @Column(nullable = false, length = 100)
    private String teamO;

    @Column(length = 100, nullable = false)
    private String teamT;

    @Column(length = 40, nullable = false)
    private String dateTime;

    /*
     * Column Id
     */
    // Getter for id
    public Integer getId() {
        return id;
    }

    // Setter for id
    public void setId(Integer id) {
        this.id = id;
    }

    /*
     * Column email
     */
    // Getter for email
    public String getTeamO() {
        return teamO;
    }

    // Setter for email
    public void setTeamO(String teamO) {
        this.teamO = teamO;
    }

    /*
     * Column name
     */
    // Getter for name
    public String getTeamT() {
        return teamT;
    }
    // Setter for name
    public void setTeamT(String teamT) {
        this.teamT = teamT;
    }

    /*
     * Column gender
     */
    // Getter for gender
    public String getDateTime() {
        return dateTime;
    }
    // Setter for genre
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
