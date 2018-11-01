package br.com.candidatodebolso.webservice.endpoint.voter;

import br.com.candidatodebolso.webservice.persistence.model.pollintention.Researcher;
import br.com.candidatodebolso.webservice.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("voter/researcher")
public class ResearcherVoterEndpoint {

    private final CrudService<Researcher> researcherCrudService;

    @Autowired
    public ResearcherVoterEndpoint(CrudService<Researcher> researcherCrudService) {
        this.researcherCrudService = researcherCrudService;
    }

    @GetMapping("{id}")
    @Transactional
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(researcherCrudService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(researcherCrudService.listAll(), HttpStatus.OK);
    }
}
