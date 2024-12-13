package com.reservation.reserve.repositorie;

import com.reservation.reserve.entite.Chambre;
import com.reservation.reserve.entite.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
