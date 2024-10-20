package com.HospitalManagementSystem.HospitalManagementSystem.Auth.controllers;

import java.io.ByteArrayOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.Doctor;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.ERole;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.Role;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.User;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.payload.request.LoginRequest;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.payload.request.SignupRequest;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.payload.response.JwtResponse;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.payload.response.MessageResponse;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.payload.response.ValidationResponse;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.repository.RoleRepository;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.repository.UserRepository;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.security.jwt.JwtUtils;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.security.services.UserDetailsImpl;
import com.HospitalManagementSystem.HospitalManagementSystem.util.EmailUtil;
import com.HospitalManagementSystem.HospitalManagementSystem.util.PasswordUtil;
import com.HospitalManagementSystem.HospitalManagementSystem.util.QRCodeGenerator;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	private final EmailUtil emailUtil;
	@Autowired
	JavaMailSender mailSender;
	public AuthController(JavaMailSender mailSender) {
		this.mailSender = mailSender;
		this.emailUtil = EmailUtil.getInstance(mailSender);
	}
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles));
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable String id, @Valid @RequestBody SignupRequest updateRequest)
			throws IOException, MessagingException {
		// Check if user exists
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Error: User not found."));

		// Store the old email and password for comparison
		String oldEmail = user.getEmail();
		String oldPassword = user.getPassword();

		// Update user details
		user.setUsername(updateRequest.getUsername());
		QRCodeGenerator qrCodeGenerator = QRCodeGenerator.getInstance();

		if (!updateRequest.getEmail().equals(oldEmail)) {
			user.setEmail(updateRequest.getEmail());
			user.setPassword(encoder.encode(updateRequest.getPassword())); // Only update if password is changed
			emailUtil.sendSignUpEmail(user.getEmail(), updateRequest.getPassword(), qrCodeGenerator.generateQRCode(user.getId()));
		}

		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable String id) {
		if (!userRepository.existsById(id)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found."));
		}

		userRepository.deleteById(id);
		return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
	}

	@GetMapping("/validate/{id}")
	public ResponseEntity<?> validateObjectId(@PathVariable String id) {
		boolean exists = userRepository.existsById(id);
		return ResponseEntity.ok(new ValidationResponse(exists));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws IOException, MessagingException {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		String rawPassword = signUpRequest.getPassword(); // Save raw password to send in email
		User user = new User(signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(rawPassword));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);
						break;
					case "mod":
						Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(modRole);
						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		User saveduser =  userRepository.save(user);

		QRCodeGenerator qrCodeGenerator = QRCodeGenerator.getInstance();
		ByteArrayOutputStream qrCodeOutputStream = qrCodeGenerator.generateQRCode(saveduser.getId());

		// Send confirmation email with password and QR code
		emailUtil.sendSignUpEmail(user.getEmail(), rawPassword, qrCodeOutputStream);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/addpatient")
	public ResponseEntity<?> addPatient(@Valid @RequestBody SignupRequest signUpRequest) throws IOException, MessagingException {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		PasswordUtil passwordUtil = PasswordUtil.getInstance();
		String rawPassword = passwordUtil.generateRandomPassword();



		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		User user;
		if (strRoles.contains("mod")) {
			 user = new Doctor(signUpRequest.getUsername(),
					signUpRequest.getEmail(),
					encoder.encode(rawPassword), signUpRequest.getDepartment());
		}
		else {
			 user = new User(signUpRequest.getUsername(),
					signUpRequest.getEmail(),
					encoder.encode(rawPassword));
		}

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);
						break;
					case "mod":
						Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(modRole);
						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		User savedUser = userRepository.save(user);
		QRCodeGenerator qrCodeGenerator = QRCodeGenerator.getInstance();
		ByteArrayOutputStream qrCodeOutputStream = qrCodeGenerator.generateQRCode(savedUser.getId());
		emailUtil.sendSignUpEmail(savedUser.getEmail(), rawPassword, qrCodeOutputStream);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}


	@PutMapping("/updatepatient/{id}")
	public ResponseEntity<?> updatePatient(@PathVariable String id, @Valid @RequestBody SignupRequest signUpRequest) throws IOException, MessagingException {
		// Find the user by ID
		System.out.println(signUpRequest);
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Error: User not found."));

		// Check if the username is being updated and is already taken by another user
		if (!user.getUsername().equals(signUpRequest.getUsername()) && userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		// Check if the email is being updated and is already in use by another user
		if (!user.getEmail().equals(signUpRequest.getEmail()) && userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Update the user's details (username and email)
		user.setUsername(signUpRequest.getUsername());
		user.setEmail(signUpRequest.getEmail());
		user.setLink(signUpRequest.getLink());
		user.setPassword(user.getPassword());
		// Update roles
		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);
						break;
					case "mod":
						Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(modRole);
						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
				}
			});
		}

		// Set the updated roles


		// If updating doctor details, check for department and update accordingly
		if (user instanceof Doctor && signUpRequest.getDepartment() != null) {
			((Doctor) user).setDepartment(signUpRequest.getDepartment());
		}

		User updatedUser = userRepository.save(user);

		QRCodeGenerator qrCodeGenerator = QRCodeGenerator.getInstance();
		// Generate QR code for the updated user details
		ByteArrayOutputStream qrCodeOutputStream = qrCodeGenerator.generateQRCode(updatedUser.getId());

		// Send an email with the updated user information
		String updatedInfo = "Your profile has been updated successfully.\n";
		emailUtil.sendSignUpEmail(updatedUser.getEmail(), updatedInfo, qrCodeOutputStream);

		return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
	}

}
