package com.poc.diospringboot.service.implementacao;

import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.domain.user.UserBuilder;
import com.poc.diospringboot.gateway.CreateUserGateway;
import com.poc.diospringboot.gateway.exception.CreateUserGatewayException;
import com.poc.diospringboot.service.ExistsByEmailService;
import com.poc.diospringboot.service.exception.ServiceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Serviço de criação de usuário")
public class CreateUserServiceImpTest {

    @Mock
    private CreateUserGateway createUserGateway;
    @Mock
    private ExistsByEmailService existsByEmailService;
    @InjectMocks
    private CreateUserServiceImp createUserServiceImp;

    @Test
    @DisplayName("Deve criar um usuário valido")
    public void createValidUser() throws ServiceException, CreateUserGatewayException {
        User validUser = UserBuilder.anUser().build();

        when(existsByEmailService.execute(validUser.getEmail())).thenReturn(false);
        createUserServiceImp.execute(validUser);
        verify(createUserGateway, times(1)).execute(validUser);
    }

}
