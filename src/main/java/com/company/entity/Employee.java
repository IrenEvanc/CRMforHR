package com.company.entity;
import javax.persistence.*;
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    private Long id;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    private User user;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id")
    private Status status;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
//    @OneToOne(fetch = FetchType.LAZY)
    private EmployeeIT employeeIT;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Employee () {}
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Post getPost() {
        return post;
    }
    public void setPost(Post post) {
        this.post = post;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public EmployeeIT getEmployeeIT() {return employeeIT;}
    public void setEmployeeIT(EmployeeIT employeeIT) {this.employeeIT = employeeIT;}

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + user.getId() +
                "user=" + user +
                ", post=" + post +
                ", status=" + status +
                '}';
    }
}