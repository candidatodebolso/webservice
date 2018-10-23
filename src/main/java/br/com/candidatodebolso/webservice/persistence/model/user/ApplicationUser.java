package br.com.candidatodebolso.webservice.persistence.model.user;

import br.com.candidatodebolso.webservice.persistence.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class ApplicationUser extends AbstractEntity {

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String username;

    @NotEmpty
    @Column(nullable = false)
    private String password;

    @OneToOne
    private Admin admin;

    @OneToOne
    private Voter voter;

    public ApplicationUser() {
    }

    public ApplicationUser(ApplicationUser applicationUser) {
        this.username = applicationUser.username;
        this.password = applicationUser.password;
        this.admin = applicationUser.admin;
        this.voter = applicationUser.voter;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }
}
