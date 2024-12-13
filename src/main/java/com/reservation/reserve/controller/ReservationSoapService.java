package com.reservation.reserve.controller;

import com.reservation.reserve.entite.Reservation;
import com.reservation.reserve.service.ReservationService;
import com.reservation.ws.CreateReservationRequest;
import com.reservation.ws.CreateReservationResponse;
import com.reservation.ws.GetReservationRequest;
import com.reservation.ws.GetReservationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;

@Endpoint
public class ReservationSoapService {

    private static final String NAMESPACE_URI = "http://reservation.com/ws";

    @Autowired
    private ReservationService reservationService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetReservationRequest")
    @ResponsePayload
    public GetReservationResponse getReservation(@RequestPayload GetReservationRequest request) {
        GetReservationResponse response = new GetReservationResponse();
        Reservation reservationEntity = reservationService.getReservationByCode(String.valueOf(request.getId()));

        com.reservation.ws.Reservation soapReservation = mapToSoapReservation(reservationEntity);
        response.setReservation(soapReservation);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateReservationRequest")
    @ResponsePayload
    public CreateReservationResponse createReservation(@RequestPayload CreateReservationRequest request) throws DatatypeConfigurationException {
        CreateReservationResponse response = new CreateReservationResponse();

        Reservation reservationEntity = new Reservation();
        reservationEntity.setClient(mapToEntityClient(request.getClient()));
        reservationEntity.setChambre(mapToEntityChambre(request.getChambre()));
        reservationEntity.setDateDebut(convertToDate(request.getDateDebut()));
        reservationEntity.setDateFin(convertToDate(request.getDateFin()));
        reservationEntity.setPreferences(request.getPreferences());

        Reservation created = reservationService.saveReservation(reservationEntity);

        com.reservation.ws.Reservation soapReservation = mapToSoapReservation(created);
        response.setReservation(soapReservation);
        return response;
    }

    private com.reservation.ws.Reservation mapToSoapReservation(Reservation entity) {
        com.reservation.ws.Reservation soapReservation = new com.reservation.ws.Reservation();
        soapReservation.setId(entity.getId());
        soapReservation.setClient(mapToSoapClient(entity.getClient()));
        soapReservation.setChambre(mapToSoapChambre(entity.getChambre()));
        soapReservation.setPreferences(entity.getPreferences());
        soapReservation.setDateDebut(convertToXMLGregorianCalendar(entity.getDateDebut()));
        soapReservation.setDateFin(convertToXMLGregorianCalendar(entity.getDateFin()));
        return soapReservation;
    }

    private com.reservation.ws.Client mapToSoapClient(com.reservation.reserve.entite.Client entityClient) {
        com.reservation.ws.Client soapClient = new com.reservation.ws.Client();
        soapClient.setId(entityClient.getId());
        soapClient.setNom(entityClient.getNom());
        soapClient.setPrenom(entityClient.getPrenom());
        soapClient.setEmail(entityClient.getEmail());
        soapClient.setTelephone(entityClient.getTelephone());
        return soapClient;
    }

    private com.reservation.ws.Chambre mapToSoapChambre(com.reservation.reserve.entite.Chambre entityChambre) {
        com.reservation.ws.Chambre soapChambre = new com.reservation.ws.Chambre();
        soapChambre.setId(entityChambre.getId());
        soapChambre.setType(entityChambre.getType());
        soapChambre.setPrix(entityChambre.getPrix());
        soapChambre.setDisponible(entityChambre.isDisponible());
        return soapChambre;
    }

    private com.reservation.reserve.entite.Client mapToEntityClient(com.reservation.ws.Client soapClient) {
        com.reservation.reserve.entite.Client entityClient = new com.reservation.reserve.entite.Client();
        entityClient.setId(soapClient.getId());
        entityClient.setNom(soapClient.getNom());
        entityClient.setPrenom(soapClient.getPrenom());
        entityClient.setEmail(soapClient.getEmail());
        entityClient.setTelephone(soapClient.getTelephone());
        return entityClient;
    }

    private com.reservation.reserve.entite.Chambre mapToEntityChambre(com.reservation.ws.Chambre soapChambre) {
        com.reservation.reserve.entite.Chambre entityChambre = new com.reservation.reserve.entite.Chambre();
        entityChambre.setId(soapChambre.getId());
        entityChambre.setType(soapChambre.getType());
        entityChambre.setPrix(soapChambre.getPrix());
        entityChambre.setDisponible(soapChambre.isDisponible());
        return entityChambre;
    }

    private XMLGregorianCalendar convertToXMLGregorianCalendar(LocalDate date) {
        if (date == null) return null;
        try {
            GregorianCalendar calendar = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private LocalDate convertToDate(XMLGregorianCalendar xmlGregorianCalendar) {
        if (xmlGregorianCalendar == null) return null;
        return xmlGregorianCalendar.toGregorianCalendar().toZonedDateTime().toLocalDate();
    }
}
