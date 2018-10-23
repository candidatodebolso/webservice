package br.com.candidatodebolso.webservice.persistence.repository;

import br.com.candidatodebolso.webservice.persistence.model.user.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findByUsername(String username);
}
