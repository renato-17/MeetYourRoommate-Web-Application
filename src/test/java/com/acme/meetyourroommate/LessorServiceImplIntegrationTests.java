package com.acme.meetyourroommate;

import com.acme.meetyourroommate.domain.model.Lessor;
import com.acme.meetyourroommate.domain.repository.LessorRepository;
import com.acme.meetyourroommate.domain.service.LessorService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import com.acme.meetyourroommate.service.LessorServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class LessorServiceImplIntegrationTests {
    @MockBean
    private LessorRepository lessorRepository;
    @Autowired
    private LessorService lessorService;

    @TestConfiguration
    static class LessorServiceImplTestConfiguration {
        @Bean
        public LessorService lessorService(){
            return new LessorServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetLessorByDni With Valid Dni Then Returns Lessor")
    public void whenGetLessorByValidDniThenReturnsLessor(){
        //Arrange
        String dni = "12345678";
        Lessor lessor = new Lessor();
        lessor.setId(1L);
        lessor.setDni(dni);

        when(lessorRepository.findByDni(dni)).thenReturn(Optional.of(lessor));

        //Act
        Lessor foundLessor = lessorService.getLessorByDni(dni);
        //Assert
        assertThat(foundLessor.getDni()).isEqualTo(dni);
    }

    @Test
    @DisplayName("When GetLessorByDni With Invalid Dni Then Returns Resource Not Found Exception")
    public void whenGetLessorByDniWithInvalidDniThenReturnsResourceNotFoundException() {
       //Arrange
        String dni = "12345678";
        String template = "Resource %snot found for %s with value %s";
        when(lessorRepository.findByDni(dni))
               .thenReturn(Optional.empty());
       String expectedMessage = String.format(template, "Lessor", "Dni", dni);

       //Act
       Throwable exception = catchThrowable(()->{
           Lessor foundLessor = lessorService.getLessorByDni(dni);
       });

       //Assert
       assertThat(exception)
               .isInstanceOf(ResourceNotFoundException.class)
               .hasMessage(expectedMessage);
    }

}
