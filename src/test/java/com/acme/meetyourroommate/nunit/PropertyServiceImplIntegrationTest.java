package com.acme.meetyourroommate.nunit;

import com.acme.meetyourroommate.domain.model.Ad;
import com.acme.meetyourroommate.domain.model.Property;
import com.acme.meetyourroommate.domain.repository.AdRepository;
import com.acme.meetyourroommate.domain.repository.LessorRepository;
import com.acme.meetyourroommate.domain.repository.PropertyRepository;
import com.acme.meetyourroommate.domain.service.PropertyService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import com.acme.meetyourroommate.service.PropertyServiceImpl;
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
public class PropertyServiceImplIntegrationTest {
    @MockBean
    private PropertyRepository propertyRepository;

    @MockBean
    private LessorRepository lessorRepository;

    @Autowired
    private PropertyService propertyService;

    @TestConfiguration
    static class PropertyServiceImplTestConfiguration {
        @Bean
        public PropertyService propertyService() {
            return new PropertyServiceImpl();
        }
    }


    @Test
    @DisplayName("When GetPropertyByAddress With Valid Address Then Returns Property")
    public void whenGetPropertyByAddressWithValidAddressThenReturnsProperty() {
        //Arrange
        String address = "Av. Ica 123";
        Property property = new Property();
        property.setId(1L);
        property.setAddress(address);
        when(propertyRepository.findByAddress(address))
                .thenReturn(Optional.of(property));
        //Act
        Property foundProperty = propertyService.getPropertyByAddress(address);

        //Assert

        assertThat(foundProperty.getAddress()).isEqualTo(address);
    }

    @Test
    @DisplayName("When GetPropertyByAddress With Invalid Address Then Returns Resource Not Found Exception")
    public void whenGetPropertyByAddressWithInvalidAddressThenReturnsResourceNotFoundException() {
        //Arrange
        String address = "Av. Ica 123";
        String template = "Resource %s not found for %s with value %s";
        when(propertyRepository.findByAddress(address))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Property", "Address", address);

        //Act
        Throwable exception = catchThrowable(() -> {
            Property foundProperty = propertyService.getPropertyByAddress(address);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }
}
