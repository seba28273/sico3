package com.luse.sico.web.rest;
import com.luse.sico.domain.Cliente;
import com.luse.sico.domain.User;
import com.luse.sico.repository.UserRepository;
import com.luse.sico.service.ClienteService;
import com.luse.sico.service.RecaudadorDetalleService;
import com.luse.sico.service.UserService;
import com.luse.sico.service.dto.UserDTO;
import com.luse.sico.web.rest.errors.BadRequestAlertException;
import com.luse.sico.web.rest.errors.DniAlreadyUsedException;
import com.luse.sico.web.rest.errors.EmailNotFoundException;
import com.luse.sico.web.rest.errors.InternalServerErrorException;
import com.luse.sico.web.rest.util.HeaderUtil;
import com.luse.sico.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Cliente.
 */
@RestController
@RequestMapping("/api")
public class ClienteResource {

    private final Logger log = LoggerFactory.getLogger(ClienteResource.class);

    private static final String ENTITY_NAME = "cliente";

    private final ClienteService clienteService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * POST  /clientes : Create a new cliente.
     *
     * @param cliente the cliente to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cliente, or with status 400 (Bad Request) if the cliente has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/clientes")
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente) throws URISyntaxException {
        log.debug("REST request to save Cliente : {}", cliente);
        if (cliente.getId() != null) {
            throw new BadRequestAlertException("A new cliente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Cliente result = clienteService.save(cliente);
        return ResponseEntity.created(new URI("/api/clientes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getFirstName() + ' ' + result.getLastName()))
            .body(result);

    }

    /**
     * PUT  /clientes : Updates an existing cliente.
     *
     * @param cliente the cliente to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cliente,
     * or with status 400 (Bad Request) if the cliente is not valid,
     * or with status 500 (Internal Server Error) if the cliente couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/clientes")
    public ResponseEntity<Cliente> updateCliente(@Valid @RequestBody Cliente cliente) throws URISyntaxException {
        log.debug("REST request to update Cliente : {}", cliente);
        if (cliente.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "Falta Identificador");
        }
        UserDTO oUserDTO;
        oUserDTO = userService.getUserWithAuthorities()
            .map(UserDTO::new)
            .orElseThrow(() -> new InternalServerErrorException("User could not be found"));

        //oUserDTO.getId(),
        userService.updateImage(cliente.getImageUrl() );

        Cliente result = clienteService.save(cliente);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cliente.getFirstName() + ' ' + cliente.getLastName()))
            .body(result);
    }

    /**
     * GET  /clientes : get all the clientes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of clientes in body
     */
    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAllClientes(Pageable pageable) {
        log.debug("REST request to get a page of Clientes");
        Page<Cliente> page = clienteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/clientes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /clientes/:id : get the "id" cliente.
     *
     * @param id the id of the cliente to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cliente, or with status 404 (Not Found)
     */
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id) {
        log.debug("REST request to get Cliente : {}", id);
        Optional<Cliente> cliente = clienteService.findOne(id);



        return ResponseUtil.wrapOrNotFound(cliente);
    }


    @GetMapping("/clientes/{id}{nrocbu}")
    public ResponseEntity<Cliente> getClienteByNroCbu(@PathVariable Long id,@PathVariable String nrocbu) {
        log.debug("REST request to get Cliente : {}", id);
        Optional<Cliente> cliente = clienteService.findOne(id);



        /**
         *  //Grabar Token
         * */
        return ResponseUtil.wrapOrNotFound(cliente);
    }
    /**
     * GET  /clientes/:email : get the "email" cliente.
     *
     * @param email the id of the cliente to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cliente, or with status 404 (Not Found)
     */
    @GetMapping("/ByEmail/{email}")
    public ResponseEntity<Cliente> getClienteByEmail(@PathVariable String email) {
        log.debug("REST request to get Cliente : {}", email);
        Optional<Cliente> cliente = clienteService.findOnebyEmail(email);
        if (!cliente.isPresent())
        {
            throw new EmailNotFoundException();
        }
        return ResponseUtil.wrapOrNotFound(cliente);
    }

    /**
     * GET  /clientes/:dni : get the "dni" cliente.
     *
     * @param dni the id of the cliente to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cliente, or with status 404 (Not Found)
     */
    @GetMapping("/ByDni/{dni}")
    public ResponseEntity<Cliente> getClienteByDni(@PathVariable String dni) {
        log.debug("REST request to get Cliente : {}", dni);
        Optional<Cliente> cliente = clienteService.findBydni(dni);
        if (!cliente.isPresent())
        {
            throw new DniAlreadyUsedException("No existe un Usuario con el DNI solicitado");
        }
        return ResponseUtil.wrapOrNotFound(cliente);
    }

    /**
     * DELETE  /clientes/:id : delete the "id" cliente.
     *
     * @param id the id of the cliente to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        log.debug("REST request to delete Cliente : {}", id);
        clienteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
