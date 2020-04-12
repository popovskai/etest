package proekt.etest.models.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "asdasd")
    private String answerContent;
    private boolean isRight = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(updatable = false, nullable = false)
    @JsonIgnore
    private Question question;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "rightAnswer")
    @JsonIgnore
    private Question rightQuestion;

    @ManyToMany(mappedBy = "userAnswers")
    private List<UserRespond> userResponds;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updatedAt;


    @PrePersist protected  void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
