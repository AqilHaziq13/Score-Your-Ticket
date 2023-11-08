package com.library.ticket;

import javax.persistence.*;

// Annotate POJO as entity to represent a database entity/object.
@Entity()
// Specify primary table for entity
@Table(name = "ticket")

public class Ticket {
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

    @Column(nullable = false, length = 20)
    private String gate;

    @Column(length = 100, nullable = false)
    private String stadium;

    @Column(length = 60, nullable = false)
    private String vs;

    @Column(length = 40, nullable = false)
    private int price;

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
     * Column subject_name
     */
    // Getter for subject_name
    public String getGate() {
        return gate;
    }

    // Setter for subject_name
    public void setGate(String gate) {
        this.gate = gate;
    }

    /*
     * Column name
     */
    // Getter for date
    public String getStadium() {
        return stadium;
    }
    // Setter for date
    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    /*
     * Column time
     */
    // Getter for time
    public String getVs() {
        return vs;
    }
    // Setter for time
    public void setVs(String vs) {
        this.vs = vs;
    }

    public Integer getPrice() {
        return price;
    }
    // Setter for total_stud
    public void setPrice(Integer price) {
        this.price = price;
    }
}
