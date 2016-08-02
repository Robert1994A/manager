package ro.inf.ucv.admitere.list;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ro.inf.ucv.admitere.entity.User;

import java.util.List;

@XmlRootElement(name = "user-list")
public class UserList {

    private List<User> userList;

    public UserList() {
    }

    public UserList(List<User> userList) {
        this.userList = userList;
    }

    public void setUsers(List<User> userList) {
        this.userList = userList;
    }

    @XmlElement(name = "user")
    public List<User> getUserList() {
        return userList;
    }
}
