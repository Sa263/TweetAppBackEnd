package com.tweetapp.api.servicetest;

import com.tweetapp.api.exception.InvalidUsernameOrPasswordException;
import com.tweetapp.api.exception.UsernameAlreadyExists;
import net.bytebuddy.dynamic.DynamicType;
import org.assertj.core.api.Assertions;
import org.mockito.InjectMocks;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.internal.matchers.NotNull;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tweetapp.api.model.User;

import com.tweetapp.api.model.UserResponse;

import com.tweetapp.api.repository.TweetRepository;
import com.tweetapp.api.repository.UserRepository;
import com.tweetapp.api.service.UserServiceImpl;

public class UserServiceTest {
	private MockMvc mockMvc;

	@Mock
	private UserRepository userrepo;

	@Mock
	private TweetRepository tweetRepo;

	@InjectMocks
	private UserServiceImpl userServiceMock = new UserServiceImpl();

	@BeforeEach
	public void setup() {

		MockitoAnnotations.initMocks(this);
	}





	@Test
	public void registerPositiveTest() throws Exception {
		User registerDTO = new User();
		registerDTO.setId("id");
		registerDTO.setEmail("fse@gmail.com");
		registerDTO.setFirstName("admin");
		registerDTO.setLastName("admin");
		registerDTO.setPassword("admin");
		registerDTO.setUsername("admin");

		when(userServiceMock.createUser(registerDTO)).thenReturn(registerDTO);
		User actualresp = userServiceMock.createUser(registerDTO);

		assertEquals(registerDTO, actualresp);
	}

	@Test
	public void updateUserTest()
	{
		User registerDTO = new User();
		registerDTO.setId("id");
		registerDTO.setEmail("fse@gmail.com");
		registerDTO.setFirstName("admin");
		registerDTO.setLastName("admin");
		registerDTO.setPassword("admin");
		registerDTO.setUsername("admin");

		when(userrepo.save(registerDTO)).thenReturn(registerDTO);
		User actualresp = userServiceMock.updateUser(registerDTO);
		assertEquals(registerDTO,actualresp);



	}

//	@Test
//	public void createUserNegativeTest() throws UsernameAlreadyExists
//	{
//		User user = new User();
//		user.setId("id");
//		user.setEmail("fse@gmail.com");
//		user.setFirstName("admin");
//		user.setLastName("admin");
//		user.setPassword("admin");
//		user.setUsername("admin");
//		when(userrepo.findByUsername(user.getFirstName())).thenReturn(notNull());
////		assertThrows(UsernameAlreadyExists.class ,()-> userServiceMock.createUser(user),"Username already exists");
//
//	}

	@Test
	public void signUpPostiveTest() throws Exception {
		User user = new User();
		user.setId("id");
		user.setEmail("fse@gmail.com");
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setPassword("admin");
		user.setUsername("admin");
		UserResponse loginRequestDTO = new UserResponse();
		loginRequestDTO.setUser(user);
		loginRequestDTO.setLoginStatus("success");

		when(userrepo.findByUsername(user.getUsername())).thenReturn(user);

		UserResponse actual = userServiceMock.loginUser(user.getUsername(), user.getPassword());
		assertEquals("success", actual.getLoginStatus());
	}

	@Test
	public void getAllUsersPositiveTest() throws Exception {

		List<User> registerList = new ArrayList<>();
		User register = new User();
		register.setId("id");
		register.setEmail("fse@gmail.com");
		register.setFirstName("admin");
		register.setLastName("admin");
		register.setPassword("admin");
		register.setUsername("admin");

		registerList.add(register);

		List<User> expectedList = new ArrayList<>();

		User userDTO = new User();
		userDTO.setId("id");
		userDTO.setEmail("fse@gmail.com");
		userDTO.setFirstName("admin");
		userDTO.setLastName("admin");
		userDTO.setPassword("admin");
		userDTO.setUsername("admin");

		expectedList.add(userDTO);

		when(userrepo.findAll()).thenReturn(registerList);

		List<User> actual = userServiceMock.getAllUsers();
		assertEquals(registerList, actual);
	}

	@Test
	public void deleteUserTest() throws Exception
	{
		UserServiceImpl myList = mock(UserServiceImpl.class);

		List<User> registerList = new ArrayList<>();
		User register = new User();
		register.setId("id");
		register.setEmail("fse@gmail.com");
		register.setFirstName("admin");
		register.setLastName("admin");
		register.setPassword("admin");
		register.setUsername("admin");

		registerList.add(register);
		//when(userrepo.delete(register)).thenReturn(1);
		doNothing().when(userrepo).delete(register);
		userrepo.delete(register);
		verify(userrepo, times(1)).delete(register);
		when(myList.deleteUser(register)).thenReturn(1);


	}
	@Test
	public void getUserByUsernameTest() throws Exception
	{
		List<User> registerList = new ArrayList<>();
		User register = new User();
		register.setId("id");
		register.setEmail("fse@gmail.com");
		register.setFirstName("admin");
		register.setLastName("admin");
		register.setPassword("admin");
		register.setUsername("admin");

		registerList.add(register);
		List<User> expectedList = new ArrayList<>();

		User userDTO = new User();
		userDTO.setId("id");
		userDTO.setEmail("fse@gmail.com");
		userDTO.setFirstName("admin");
		userDTO.setLastName("admin");
		userDTO.setPassword("admin");
		userDTO.setUsername("admin");

		expectedList.add(userDTO);


		when(userrepo.findByUsernameContaining("admin")).thenReturn(registerList);
		List<User> actual = userServiceMock.getUserByUsername("admin");

		assertEquals(registerList,actual);
	}
	@Test
	public void getUserByUsernameNegativeCase()
	{
		when(userrepo.findByUsernameContaining("abc")).thenReturn(null);
		assertThrows(InvalidUsernameOrPasswordException.class ,()-> userServiceMock.getUserByUsername("abc"),"Please enter a valid username");

	}

	@Test
	public void getUserById()
	{
		User register = new User();
		Optional<User> registerlist = Optional.of(register);

		register.setId("1");
		register.setEmail("fse@gmail.com");
		register.setFirstName("admin");
		register.setLastName("admin");
		register.setPassword("admin");
		register.setUsername("admin");

		User obj1 = registerlist.get();


		Optional<User> expectedList = Optional.of(register);

		User userDTO = new User();
		userDTO.setId("1");
		userDTO.setEmail("fse@gmail.com");
		userDTO.setFirstName("admin");
		userDTO.setLastName("admin");
		userDTO.setPassword("admin");
		userDTO.setUsername("admin");

		User obj = expectedList.get();


		when(userrepo.findById("1")).thenReturn(Optional.of(userDTO));
		Optional<User> actual = userServiceMock.getUserById("1");
		assertNotEquals(registerlist,actual);



	}

	@Test
	public void forgotPasswordTest()
	{
		Map<String, String> map = new HashMap<String, String>();

		List<User> registerList = new ArrayList<>();
		User register = new User();
		register.setId("id");
		register.setEmail("fse@gmail.com");
		register.setFirstName("admin");
		register.setLastName("admin");
		register.setPassword(UUID.randomUUID().toString());
		register.setUsername("admin");

		registerList.add(register);

		when(userrepo.findByUsername("admin")).thenReturn(register);
		when(userrepo.save(register)).thenReturn(register);
		map.put("newPassword",UUID.randomUUID().toString());
		map.put("resetStatus","success");
		Map<String,String> actual = userServiceMock.forgotPassword("admin");
		assertNotEquals(map,actual);

	}
	@Test
	public void resetPasswordTest()
	{
		Map<String, String> map = new HashMap<String, String>();

		List<User> registerList = new ArrayList<>();
		User register = new User();
		register.setId("id");
		register.setEmail("fse@gmail.com");
		register.setFirstName("admin");
		register.setLastName("admin");
		register.setPassword("admin");
		register.setUsername("admin");

		registerList.add(register);

		when(userrepo.findByUsername("admin")).thenReturn(register);
		when(userrepo.save(register)).thenReturn(register);
		map.put("newPassword","admin");
		map.put("resetStatus","success");
		Map<String,String> actual = userServiceMock.resetPassword("admin","admin");
		assertEquals(map,actual);


	}

}