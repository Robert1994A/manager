package ro.inf.ucv.admitere.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"answerOption", "question_template"}))
public class AnswerOptionTemplate implements Serializable {

    private static final long serialVersionUID = 2139031187662532970L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = false)
    private String answerOption;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_template", nullable = false)
    private QuestionTemplate question;

    public AnswerOptionTemplate() {

    }

    public AnswerOptionTemplate(String answerOption, QuestionTemplate question) {
        this.answerOption = answerOption;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionTemplate getQuestion() {
        return question;
    }

    public void setQuestion(QuestionTemplate question) {
        this.question = question;
    }

    public String getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(String answerOption) {
        this.answerOption = answerOption;
    }
}
