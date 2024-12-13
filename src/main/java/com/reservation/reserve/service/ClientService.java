package com.reservation.reserve.service;

import com.reservation.reserve.entite.Client;
import com.reservation.reserve.exception.ResourceNotFoundException;
import com.reservation.reserve.repositorie.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    }

    public Client updateClient(Long id, Client clientDetails) {
        Client client = getClientById(id);
        client.setNom(clientDetails.getNom());
        client.setPrenom(clientDetails.getPrenom());
        client.setEmail(clientDetails.getEmail());
        client.setTelephone(clientDetails.getTelephone());
        return clientRepository.save(client);
    }
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}

