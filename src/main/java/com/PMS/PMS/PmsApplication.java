package com.PMS.PMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsApplication.class, args);
		System.out.println("Server is Running");
	}

	//$10$5PiyN0MsG0y886d8xWXtwuLXK0Y7zZwcN5xm82b4oDSVr7yF0O6em
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private LocationRepository locationRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		Locations locations = new Locations();
//		locations.setPlace("Colombo");
//		locations.setDiscription("My city");
//		locations.setLongitude(40.5);
//		locations.setLatitude(30.6);
//		locationRepository.save(locations);
//
//		User user1 = new User();
//		user1.setEmail("lakshitha@gmail.com");
//		user1.setFirstName("Lakshitha");
//		user1.setLastName("Fdo");
//		user1.setPassword("lakshitha12");
//		user1.setLocations(locations);
//		userRepository.save(user1);
//
//		User user2 = new User();
//		user2.setEmail("ashen@gmail.com");
//		user2.setFirstName("Ashen");
//		user2.setLastName("Perera");
//		user2.setPassword("ashen12");
//		user2.setLocations(locations);
//		userRepository.save(user2);
//	}

	//$10$mZQgXuFYgrPqGu7Jqw6gVOpqk2L/TyILgz3OauN1vnesNwO6r0T5i ashen ROLE_ADMIN_MANAGER
}
