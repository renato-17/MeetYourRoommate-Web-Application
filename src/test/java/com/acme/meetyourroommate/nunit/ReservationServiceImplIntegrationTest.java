package com.acme.meetyourroommate.unit;

import com.acme.meetyourroommate.domain.model.Reservation;
import com.acme.meetyourroommate.domain.repository.ReservationRepository;
import com.acme.meetyourroommate.domain.service.ReservationService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import com.acme.meetyourroommate.service.ReservationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ReservationServiceImplIntegrationTest {
    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;
    @TestConfiguration
    static class ReservationServiceImplTestConfiguration {
        @Bean
        public ReservationService reservationService(){
            return new ReservationServiceImpl();
        }
    }
    @Test
    @DisplayName("When createReservation With DateStart is after the DateEnd Then Returns Resource Not Found Exception")
    public void whenCreateReservationWithDateStartAfterDateEndThenReturnsResourceNotFoundException() {
        // Arrange
        Date dateStart = new Date(2020,10,05);
        Date dateEnd = new Date(2020,9,05);
        Reservation reservation = new Reservation();
        reservation.setDateStart(dateStart);
        reservation.setDateEnd(dateEnd);
        String expectedMessage = "dateStart can't be after dateEnd";
        
        // Act
        Throwable exception = catchThrowable(()->{
            Reservation reservation1 = reservationService.createReservation(reservation);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }


}
