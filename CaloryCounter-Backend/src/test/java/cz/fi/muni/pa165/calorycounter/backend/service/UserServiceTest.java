/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.service;

import cz.fi.muni.pa165.calorycounter.backend.dao.impl.UserDaoImplJPA;
import cz.fi.muni.pa165.calorycounter.backend.dto.AuthUserDto;
import cz.fi.muni.pa165.calorycounter.backend.model.AuthUser;
import cz.fi.muni.pa165.calorycounter.backend.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit Tests using Mockito to mock DAO layer and thus avoid real DB operations.
 *
 * @author Jan Kucera (Greld)
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService = new UserServiceImpl();
    @Mock
    private UserDaoImplJPA userDaoImplJPA;
    private AuthUser user;
    private AuthUserDto userDto;
    private final Long USER_ID = 237L;
    private final String USERNAME = "edita";
    private final String PASSWORD = "heslo";

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        Long time = new java.util.Date().getTime();

        user = new AuthUser();
        user.setId(USER_ID);
        user.setName("Edita Papeky");
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);

        userDto = new AuthUserDto();
        userDto.setUserId(USER_ID);
        userDto.setName("Edita Papeky");

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetByUsername() {
        Mockito.stub(userDaoImplJPA.getByUsername(USERNAME)).toReturn(user);
        AuthUserDto uDto = userService.getByUsername(USERNAME);
        assertNotNull("User was not found by username.", uDto);
        assertEquals("Wrong user was found by username.", userDto, uDto);

        uDto = userService.getByUsername("nonexists");
        assertNull("Should have return null when username does not exist.", uDto);

    }

    @Test
    public void testLogin() {
        Mockito.stub(userDaoImplJPA.login(USERNAME, PASSWORD)).toReturn(user);
        AuthUserDto uDto = userService.login(USERNAME, PASSWORD);
        assertNotNull("User was not login.", uDto);
        assertEquals("Wrong user was login.", userDto, uDto);

        uDto = userService.login(USERNAME, "wrongPassword");
        assertNull("Should have return null when password is wrong.", uDto);
    }

    @Test
    public void testRegister() {
        Long nextId = new Long(USER_ID + 1);
        Mockito.stub(userDaoImplJPA.create(user)).toReturn(nextId);
        Mockito.stub(userDaoImplJPA.getByUsername(USERNAME)).toReturn(user);
        Long id = userService.register(userDto, "nonexist", PASSWORD);
        assertNotNull("User was not registered.", id);
        assertEquals("Wrong id was returned.", nextId, id);

        try {
            id = userService.register(userDto, USERNAME, PASSWORD);
            fail("should throw IllegalArgumentException if username is already used.");
        } catch (IllegalArgumentException e) {
            // OK
        }
    }

}
