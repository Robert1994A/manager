package ro.inf.ucv.admitere.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"answerOption", "question"}))
public class AnswerOption implements Serializable {

    private static final long serialVersionUID = 4035675111256179374L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = false)
    private String answerOption;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question", nullable = false)
    private Question question;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "selected_answers", joinColumns = {
            @JoinColumn(name = "options", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "answer", referencedColumnName = "id")})
    private Set<Answer> answers = new HashSet<Answer>();

    public AnswerOption() {

    }

    public AnswerOption(String answerOption, Question question) {
        this.answerOption = answerOption;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(String answerOption) {
        this.answerOption = answerOption;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
}
