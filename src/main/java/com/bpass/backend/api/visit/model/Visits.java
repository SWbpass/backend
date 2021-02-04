package com.bpass.backend.api.visit.model;

import com.bpass.backend.api.user.model.Store;
import com.bpass.backend.api.user.model.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Visits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "visitor_id")
    private Users visitor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id")
    private Store store;

    private LocalDateTime entryTime;
    @Setter
    private LocalDateTime exitTime;

    public Visits(Users visitor, Store store, LocalDateTime entryTime){
        this.visitor = visitor;
        this.store = store;
        this.entryTime = entryTime;
    }
}
