package org.example;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    private String id;
    private String firstName;
    private String lastName;
    private Integer age;

    public PlayerDto(String id, String firstName, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.age = age;
    }
}
