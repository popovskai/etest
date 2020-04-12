package proekt.etest.models.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Прашањето е задолжително")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(updatable = false, nullable = false)
    @JsonIgnore
    private Test test;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Answer> answers = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Answer rightAnswer;

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
