package proekt.etest.models.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameUser;
    private int user_score;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTest", orphanRemoval = true)
    private List<UserRespond> userRespond;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Test testT;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User userT;
}
