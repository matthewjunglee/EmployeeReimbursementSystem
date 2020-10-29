//package com.ers.repo;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertTrue;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import com.ers.model.User;
//import com.ers.model.UserRole;
//
//public class UserDaoTest {
//
//	private UserDao ud;
//
//	@Before
//	public void setup() {
//		this.ud = new UserDao();
//	}
//
//	@Test
//	public void loginTest() {
//		assertEquals("jane", ud.login("janedoe", "janedoe").getFirstName());
//	}
//	
//	@Test
//	public void invalidLoginTest() {
//		assertNotEquals("jane", ud.login("invalidLogin", "invalidLogin").getFirstName());
//	}
//
//	@Test
//	public void createTest() {
//		User u = new User(0, "test", "test", "test", "test", "test", new UserRole(1));
//		assertEquals(6, ud.create(u));
//	}
//	
//	@Test
//	public void invalidCreateTest() {
//		User u = new User(0, "janedoe", "test", "test", "test", "test", new UserRole(1));
//		assertEquals(0, ud.create(u));
//	}
//
//	@Test
//	public void findAllTest() {
//		assertTrue(ud.findAll().size() > 0);
//	}
//
//	@Test
//	public void findByIdTest() {
////		assertEquals("jane", ud.findById(2).getFirstName());
//		assertEquals(null, ud.findById(2));
//	}
//
//	@Test
//	public void updateTest() {
//		assertEquals(0, ud.update(null));
//	}
//
//	@Test
//	public void deleteTest() {
//		assertEquals(1, ud.delete(1));
//	}
//}