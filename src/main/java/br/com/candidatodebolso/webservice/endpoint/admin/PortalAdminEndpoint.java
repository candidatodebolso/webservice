package br.com.candidatodebolso.webservice.endpoint.admin;

import br.com.candidatodebolso.webservice.persistence.model.news.Portal;
import br.com.candidatodebolso.webservice.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/portal")
public class PortalAdminEndpoint {

    private final CrudService<Portal> portalCrudService;

    @Autowired
    public PortalAdminEndpoint(CrudService<Portal> portalCrudService) {
        this.portalCrudService = portalCrudService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Validated Portal politicalParty) {
        return new ResponseEntity<>(portalCrudService.create(politicalParty), HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Validated Portal politicalParty) {
        return new ResponseEntity<>(portalCrudService.update(politicalParty), HttpStatus.OK);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> delete(@RequestBody Portal politicalParty) {
        portalCrudService.delete(politicalParty);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Transactional
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(portalCrudService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(portalCrudService.listAll(), HttpStatus.OK);
    }
}
