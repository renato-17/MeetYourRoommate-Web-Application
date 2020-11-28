package com.acme.meetyourroommate.nunit;

import com.acme.meetyourroommate.domain.model.Ad;
import com.acme.meetyourroommate.domain.repository.AdRepository;
import com.acme.meetyourroommate.domain.repository.PropertyRepository;
import com.acme.meetyourroommate.domain.service.AdService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import com.acme.meetyourroommate.service.AdServiceImpl;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AdServiceImplIntegrationTest {
    @MockBean
    private AdRepository adRepository;

    @MockBean
    private PropertyRepository propertyRepository;

    @Autowired
    private AdService adService;

    @TestConfiguration
    static class AdServiceImplTestConfiguration {
        @Bean
        public AdService adService() {
            return new AdServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetAdByTitle With Valid Title Then Returns Ad")
    public void whenGetAdByTitleWithValidTitleThenReturnsAd() {
        //Arrange
        String title = "Alquiler de Departamento";
        Ad ad = new Ad();
        ad.setId(1L);
        ad.setTitle(title);
        //given(adRepository.findByTitle(ad.getTitle()))
        //       .willReturn(Optional.of(ad));
        when(adRepository.findByTitle(title))
                .thenReturn(Optional.of(ad));
        //Act
        Ad foundAd = adService.getAdByTitle(title);

        //Assert

        assertThat(foundAd.getTitle()).isEqualTo(title);
    }

    @Test
    @DisplayName("When GetAdByTitle With Invalid Title Then Returns Resource Not Found Exception")
    public void whenGetAdByTitleWithInvalidTitleThenReturnsResourceNotFoundException() {
        //Arrange
        String title = "Alquiler de Departamento";
        String template = "Resource %s not found for %s with value %s";
        when(adRepository.findByTitle(title))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Ad", "Title", title);

        //Act
        Throwable exception = catchThrowable(() -> {
            Ad foundAd = adService.getAdByTitle(title);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }
}
