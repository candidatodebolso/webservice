package br.com.candidatodebolso.webservice.endpoint.voter;

import br.com.candidatodebolso.webservice.persistence.model.news.Portal;
import br.com.candidatodebolso.webservice.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("voter/portal")
public class PortalVoterEndpoint {

    private final CrudService<Portal> portalCrudService;

    @Autowired
    public PortalVoterEndpoint(CrudService<Portal> portalCrudService) {
        this.portalCrudService = portalCrudService;
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
