package org.example;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String birthDay;
    private String birthMonth;
    private String birthYear;
    private String deathDay;
    private String deathMonth;
    private String deathYear;

}
