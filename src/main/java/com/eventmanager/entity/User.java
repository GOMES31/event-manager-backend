package com.eventmanager.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Ticket> tickets = new HashSet<>();

    @OneToMany(mappedBy = "organizer")
    private Set<Event> events = new HashSet<>();

    private void addEvent(Event event) {
        if(event != null && !events.contains(event)){
            events.add(event);
            event.setOrganizer(this);
        }
    }

    private void addTicket(Ticket ticket) {
        if(ticket != null && !tickets.contains(ticket)){
            tickets.add(ticket);
            ticket.setUser(this);
        }
    }
}
