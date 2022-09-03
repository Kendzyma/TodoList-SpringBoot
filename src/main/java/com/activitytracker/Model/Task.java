package com.activitytracker.Model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Task")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Task_pk")
    private int id;

    private String title;

    private String description;

    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAtDate;
    private String createdTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateDate;
    private String updateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate completedAtDate;
    private String completedAtTime;

    @ManyToOne()
    private User user;
}
