package com.poc.diospringboot.service.implementacao;

import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.domain.user.UserBuilder;
import com.poc.diospringboot.gateway.CreateUserGateway;
import com.poc.diospringboot.gateway.exception.CreateUserGatewayException;
import com.poc.diospringboot.service.ExistsByEmailService;
import com.poc.diospringboot.service.ExistsByLoginService;
import com.poc.diospringboot.service.exception.ExistsByEmailServiceException;
import com.poc.diospringboot.service.exception.ExistsByLoginServiceException;
import com.poc.diospringboot.service.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
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
    @Mock
    private ExistsByLoginService existsByLoginService;
    @InjectMocks
    private CreateUserServiceImp createUserServiceImp;

    @Test
    @DisplayName("Deve criar um usuário valido")
    public void createValidUser() throws ServiceException, CreateUserGatewayException {
        User validUser = UserBuilder.anUser().build();

        when(existsByEmailService.execute(validUser.getEmail())).thenReturn(false);
        when(existsByLoginService.execute(validUser.getLogin())).thenReturn(false);
        createUserServiceImp.execute(validUser);
        verify(createUserGateway, times(1)).execute(validUser);
    }

    @Test
    @DisplayName("Deve verificar se E-mail já existe")
    public void checkIfEmailExists() throws ServiceException {
        User validUser = UserBuilder.anUser().build();
        when(existsByEmailService.execute(validUser.getEmail())).thenReturn(true);
        Throwable exception = Assertions.assertThrows(ExistsByEmailServiceException.class, () -> createUserServiceImp.execute(validUser));
        Assertions.assertTrue(exception.getMessage().contains("O e-mail " + validUser.getEmail() + " já existe, tente outro."));
        verify(existsByEmailService, times(1)).execute(any());
        verifyNoInteractions(createUserGateway);

    }

    @Test
    @DisplayName("Deve verificar se Login já existe")
    public void checkIfLoginExists() throws ServiceException {
        User validUser = UserBuilder.anUser().build();
        when(existsByLoginService.execute(validUser.getLogin())).thenReturn(true);
        Throwable exception = Assertions.assertThrows(ExistsByLoginServiceException.class, () -> createUserServiceImp.execute(validUser));
        Assertions.assertTrue(exception.getMessage().contains("O login " + validUser.getLogin() + " já existe"));
    }
}
