package ro.inf.ucv.admitere.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class SurveyData implements Serializable {

    private static final long serialVersionUID = -473352547185256717L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Date expireDate;
    @Column(nullable = false)
    private Date publishDate;
    @Column(nullable = true)
    private String sessionName;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, unique = false)
    private Survey survey;
    @ManyToMany(mappedBy = "surveys", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<User>();

    public SurveyData() {

    }

    public SurveyData(Date expireDate, Date publishDate, Survey survey, List<User> users) {
        this.expireDate = expireDate;
        this.publishDate = publishDate;
        this.survey = survey;
        this.users = new HashSet<User>(users);
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
