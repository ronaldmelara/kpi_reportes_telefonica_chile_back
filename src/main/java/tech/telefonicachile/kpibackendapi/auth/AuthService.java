package tech.telefonicachile.kpibackendapi.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.telefonicachile.kpibackendapi.jwt.JwtService;
import tech.telefonicachile.kpibackendapi.repository.UserRepository;
import tech.telefonicachile.kpibackendapi.user.Role;
import tech.telefonicachile.kpibackendapi.user.User;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login (LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=usuarioRepository.findByUsername(request.getUsername()).orElseThrow();

        Integer userid = null;
        Object obj = user;
        if(obj instanceof User){
            userid = ((User) obj).getId();
        }
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .userid(userid)
                .build();
    }

    public AuthResponse register(RegisterRequest request){
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .nombreCompleto(request.getNombreCompleto())
                .habilitado(request.getHabilitado())
                .role(Role.USER)
                .build();

        usuarioRepository.save(user);

        return  AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
