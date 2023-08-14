package com.altomni.apn.controller;

import com.altomni.apn.model.Client;
import com.altomni.apn.model.ClientJob;
import com.altomni.apn.service.ClientJobService;
import com.altomni.apn.service.ClientService;
import com.altomni.apn.service.WithIncludes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

/**
 * Created by JIALIN on 5/11/2017.
 */
@Slf4j
@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('read')")
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WithIncludes<Client>> getClient (@PathVariable("id") Long id) {
        WithIncludes<Client> client = clientService.findById(id);
        if(client == null) {
            log.debug("Client with id" + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Client> createClient (@RequestBody Client client) {
        log.debug("Creating Client " + client.getTenantId() +client.getCompanyId());

        WithIncludes<Client> savedClient=clientService.saveClient(client);
        if (savedClient == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Client createdClient=savedClient.getData();
        createdClient.setCreatedAt(createdClient.getCreatedAt());
        createdClient.setCreatedBy(createdClient.getCreatedBy());

        URI location = new UriTemplate("/clients/{id}").expand(createdClient.getId());
        return ResponseEntity.created(location).body(createdClient);
    }

    @PreAuthorize("#oauth2.hasScope('user') and #oauth2.hasScope('write')")
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    public ResponseEntity<WithIncludes<Client>> updateClient(@PathVariable("id")Long id, @RequestBody Client client){
        log.debug("Updating client " + id);
        WithIncludes<Client> currentClientWithIncludes = clientService.findById(id);

        if (currentClientWithIncludes == null) {
            log.debug("Client with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Client currentClient = currentClientWithIncludes.getData();

        currentClient.setName(currentClient.getName());
        currentClient.setName2(currentClient.getName2());
        currentClient.setContacts(currentClient.getContacts());
        currentClient.setUpdatedAt(currentClient.getUpdatedAt());
        currentClient.setUpdatedBy((currentClient.getUpdatedBy()));

        WithIncludes<Client> updatedClientWithIncludes = clientService.saveClient(client);
        return new ResponseEntity<>(updatedClientWithIncludes, HttpStatus.OK);
    }
}
