package br.com.candidatodebolso.webservice.persistence.model.candidate;

import br.com.candidatodebolso.webservice.persistence.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PoliticalParty extends AbstractEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String initials;

    @Column(nullable = false)
    private String imageUrl;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
