package entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name= "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
}
