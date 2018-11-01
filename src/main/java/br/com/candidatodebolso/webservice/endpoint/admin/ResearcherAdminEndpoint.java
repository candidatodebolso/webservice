package br.com.candidatodebolso.webservice.endpoint.admin;

import br.com.candidatodebolso.webservice.persistence.model.pollintention.PollIntention;
import br.com.candidatodebolso.webservice.persistence.model.pollintention.Researcher;
import br.com.candidatodebolso.webservice.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin/researcher")
public class ResearcherAdminEndpoint {

    private final CrudService<Researcher> researcherCrudService;

    @Autowired
    public ResearcherAdminEndpoint(CrudService<Researcher> researcherCrudService) {
        this.researcherCrudService = researcherCrudService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Validated Researcher researcher) {
        return new ResponseEntity<>(researcherCrudService.create(researcher), HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Validated Researcher researcher) {
        return new ResponseEntity<>(researcherCrudService.update(researcher), HttpStatus.OK);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> delete(@RequestBody Researcher researcher) {
        researcherCrudService.delete(researcher);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Transactional
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(researcherCrudService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<?> listAll() {
        Iterable<Researcher> researchers = researcherCrudService.listAll();
        List<List<PollIntention>> pollIntentions = new ArrayList<>();
        researchers.forEach(researcher -> pollIntentions.add(researcher.getPollIntentions()));
        System.out.println(pollIntentions);
        return new ResponseEntity<>(researchers, HttpStatus.OK);
    }
}
