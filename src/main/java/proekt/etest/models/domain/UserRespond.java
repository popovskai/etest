package proekt.etest.models.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserRespond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "user_answers",
            joinColumns = @JoinColumn(name = "respond_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private List<Answer> userAnswers;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userRespond")
    private UserTest userTest;


}
