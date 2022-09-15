package model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Sondage implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String title;

    private String description;

    private String questions;



}
