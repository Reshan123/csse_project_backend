package com.HospitalManagementSystem.HospitalManagementSystem.Auth.controllers;

import java.io.ByteArrayOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

	@Autowired
	JavaMailSender mailSender; // Autowire JavaMailSender

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

		if (!updateRequest.getEmail().equals(oldEmail)) {
			user.setEmail(updateRequest.getEmail());
			user.setPassword(encoder.encode(updateRequest.getPassword())); // Only update if password is changed
			sendSignUpEmail(user.getEmail(), updateRequest.getPassword(), generateQRCode(user.getId()));
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

		// Generate QR code
		ByteArrayOutputStream qrCodeOutputStream = generateQRCode(saveduser.getId());

		// Send confirmation email with password and QR code
		sendSignUpEmail(user.getEmail(), rawPassword, qrCodeOutputStream);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	private ByteArrayOutputStream generateQRCode(String text) throws IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		int width = 250;
		int height = 250;
		BufferedImage bufferedImage;
		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();

		try {
			com.google.zxing.common.BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
			bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
			ImageIO.write(bufferedImage, "PNG", pngOutputStream);
		} catch (Exception e) {
			throw new RuntimeException("Error occurred while generating QR code");
		}

		return pngOutputStream;
	}

	private void sendSignUpEmail(String toEmail, String rawPassword, ByteArrayOutputStream qrCodeOutputStream) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom("mithilathilochana@gmail.com");
		helper.setTo(toEmail);
		helper.setSubject("Welcome to the Hospital Management System");
		helper.setText("Dear user,\n\nWelcome! Your account has been created successfully. " +
				"Please find your login credentials and a QR code below:\n\n" +
				"Password: " + rawPassword + "\n\n" +
				"Scan this QR code to view your details.\n\n" +
				"Thank you!");

		// Attach QR Code
		helper.addAttachment("QRCode.png", new ByteArrayResource(qrCodeOutputStream.toByteArray()));

		mailSender.send(message);
	}
}
