package com.dsn.Theater.API.repository;

import com.dsn.Theater.API.entity.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TicketRepo extends CrudRepository<Ticket, Long> {
    List<Ticket> findAll();

    List<Ticket> findAllById(Iterable<Long> ids);

    @Query(value = "SELECT * FROM Ticket WHERE event_id = :eventId AND seat_id IN (:seatIds)", nativeQuery = true)
    List<Ticket> findBySeatsAndEvent(@Param("eventId") Long eventId, @Param("seatIds") List<Long> seatIds);

    @Modifying
    @Query(value = "UPDATE Ticket SET purchase_id = :purchaseId WHERE id IN :ticketIds AND purchase_id IS NULL", nativeQuery = true)
    @Transactional
    int updatePurchaseId(@Param("ticketIds") List<Long> ticketIds, @Param("purchaseId") UUID purchaseId);

    List<Ticket> findByEventId(Long eventId);

    @Query(value = "SELECT * FROM Ticket WHERE purchase_id IS NULL AND event_id = :eventId", nativeQuery = true)
    List<Ticket> findAvailableByEventId(@Param("eventId") Long eventId);

    @Query(value = "SELECT * FROM Ticket WHERE purchase_id IS NULL AND event_id = :eventId AND seat_id IN (:seatIds)", nativeQuery = true)
    List<Ticket> findAvailableBySeatsAndEvent(@Param("eventId") Long eventId, @Param("seatIds") List<Long> seatIds);
}
