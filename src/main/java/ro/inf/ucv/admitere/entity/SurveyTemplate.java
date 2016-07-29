package ro.inf.ucv.admitere.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"title", "author"}))
public class SurveyTemplate implements Serializable {

    private static final long serialVersionUID = 8690683426217230054L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private Date createDate;

    private Date lastPublishDate;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Boolean modified;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "author", unique = false, nullable = false)
    private User author;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "survey", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<QuestionTemplate> questions = new HashSet<QuestionTemplate>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentTemplate", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Survey> publishHistory = new HashSet<Survey>();

    public SurveyTemplate() {

    }

    public SurveyTemplate(String title, Date createDate, Boolean modified, User author,
                          List<QuestionTemplate> questions) {
        this.title = title;
        this.createDate = createDate;
        this.modified = modified;
        this.questions = new HashSet<QuestionTemplate>(questions);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<QuestionTemplate> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionTemplate> questions) {
        this.questions = questions;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getModified() {
        return modified;
    }

    public void setModified(Boolean modified) {
        this.modified = modified;
    }

    public Set<Survey> getPublishHistory() {
        return publishHistory;
    }

    public void setPublishHistory(Set<Survey> publishHistory) {
        this.publishHistory = publishHistory;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getLastPublishDate() {
        return lastPublishDate;
    }

    public void setLastPublishDate(Date lastPublishDate) {
        this.lastPublishDate = lastPublishDate;
    }
}
