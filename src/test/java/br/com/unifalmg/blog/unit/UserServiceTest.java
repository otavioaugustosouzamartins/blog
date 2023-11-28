package br.com.unifalmg.blog.unit;

import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.exception.UserNotFoundException;
import br.com.unifalmg.blog.repository.UserRepository;
import br.com.unifalmg.blog.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Test
    @DisplayName("#findById > When the id is null > Throw an exception")
    void findByIdWhenTheIdIsNullThrowAnException() {
        assertThrows(IllegalArgumentException.class, () ->
                service.findById(null));
    }

    @Test
    @DisplayName("#findById > When the id is not null > When a user is found > Return the user")
    void findByIdWhenTheIdIsNotNullWhenAUserIsFoundReturnTheUser() {
        when(repository.findById(1)).thenReturn(Optional.of(User.builder()
                        .id(1)
                        .name("Fellipe")
                        .username("felliperey")
                .build()));
        User response = service.findById(1);
        assertAll(
                () -> assertEquals(1, response.getId()),
                () -> assertEquals("Fellipe", response.getName()),
                () -> assertEquals("felliperey", response.getUsername())
        );
    }

    @Test
    @DisplayName("#findById > When the id is not null > When no user is found > Throw an exception")
    void findByIdWhenTheIdIsNotNullWhenNoUserIsFoundThrowAnException() {
        when(repository.findById(2)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () ->
                service.findById(2));
    }

    // TODO: Implement test cases for getAllUsers
    @Test
    @DisplayName("#getAllUsers > When don`t have any users > return an Empty List")
    void getAllUserswhendonthaveanyusersreturnanEmptyList(){
        List<User> have = service.getAllUsers();
        Assertions.assertTrue(have.isEmpty());
    }

    @Test
    @DisplayName("#getAllUsers > When have some users > return all Users")
    void getAllUsersWhenhavesomeusersReturnallUsers(){
        when(service.getAllUsers().thenReturn(List.of(User.builder()
                                .id(1)
                                .name("Otávio")
                                .username("otah")
                                .build())));
    List<User> have = service.getAllUsers();
    Assertions.assertAll(
           () -> Assertions.assertEquals(1,have.get(0).getId()),
           () -> Assertions.assertEquals("Otávio",have.get(0).getName()),
           () -> Assertions.assertEquals("otah",have.get(0).getUsername())
    );
    }
}



