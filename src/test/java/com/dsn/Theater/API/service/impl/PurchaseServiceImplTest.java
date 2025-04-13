package com.dsn.Theater.API.service.impl;

import com.dsn.Theater.API.dto.in.Contact;
import com.dsn.Theater.API.dto.in.PurchaseRequestByTickets;
import com.dsn.Theater.API.dto.out.PurchaseDto;
import com.dsn.Theater.API.entity.Purchase;
import com.dsn.Theater.API.exception.TicketsNotAvailableException;
import com.dsn.Theater.API.repository.PurchaseRepo;
import com.dsn.Theater.API.repository.TicketRepo;
import com.dsn.Theater.API.service.interfaces.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(value = true)
class PurchaseServiceImplTest {

    @Autowired
    private PurchaseServiceImpl purchaseService;

    @Autowired
    private TicketRepo ticketRepo;

    //private List<PurchaseDto> dtoList = new ArrayList<>();
    private ConcurrentLinkedQueue<PurchaseDto> dtoQueue = new ConcurrentLinkedQueue<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Test
    @Transactional
    @Rollback
    void buyTicketByTickets() throws InterruptedException {
        Integer expectedVersion = 2;

        PurchaseRequestByTickets request = new PurchaseRequestByTickets();
        Contact contact = new Contact();
        contact.setEmail("dsn@mail.ru");
        contact.setPhone("888888888888");
        contact.setFirstName("lfdlsdf");
        contact.setLastName("fdfsdf");
        request.setContact(contact);
        request.setTickets(List.of(1L));

        int numberOfThreads = 1;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        // Запускаем несколько потоков для симуляции конкурентного доступа
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    dtoQueue.add(purchaseService.buyTicketByTickets(request));
                } catch (TicketsNotAvailableException e) {
                    counter.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); // Ждем завершения всех потоков
        executorService.shutdown();
        assertEquals(1, dtoQueue.size());
        assertEquals(numberOfThreads - request.getTickets().size(), counter.get());
        assertEquals(expectedVersion, ticketRepo.findById(1L).orElseThrow().getVersion());
    }
}