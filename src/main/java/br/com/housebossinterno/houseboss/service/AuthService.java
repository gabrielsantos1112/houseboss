package br.com.housebossinterno.houseboss.service;

import br.com.housebossinterno.houseboss.config.TokenService;
import br.com.housebossinterno.houseboss.dtos.funcionariodtos.FuncionarioRequest;
import br.com.housebossinterno.houseboss.dtos.funcionariodtos.FuncionarioResponse;
import br.com.housebossinterno.houseboss.dtos.funcionariodtos.LoginRequest;
import br.com.housebossinterno.houseboss.dtos.funcionariodtos.LoginResponse;
import br.com.housebossinterno.houseboss.entity.FuncionarioEntity;
import br.com.housebossinterno.houseboss.exception.BadRequestException;
import br.com.housebossinterno.houseboss.mapper.FuncionarioMapper;
import br.com.housebossinterno.houseboss.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public FuncionarioResponse salvarFuncionario(FuncionarioRequest funcionarioRequest) {

        if (funcionarioRepository.findUserByEmail(funcionarioRequest.email()).isPresent()) {
            throw new BadRequestException("Email já cadastrado! ");
        }

        FuncionarioEntity funcionarioEntity = FuncionarioMapper.paraFuncionarioEntity(funcionarioRequest);
        String senha = funcionarioRequest.senha();
        funcionarioEntity.setSenha(passwordEncoder.encode(senha));

        FuncionarioEntity funcionarioSalvo = funcionarioRepository.save(funcionarioEntity);

        return FuncionarioMapper.paraFuncionarioResponse(funcionarioSalvo);

    }

    public LoginResponse login(LoginRequest loginRequest) {

        try {
            UsernamePasswordAuthenticationToken userAndPass =
                    new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha());

            Authentication authenticate = authenticationManager.authenticate(userAndPass);

            FuncionarioEntity funcionarioEntity = (FuncionarioEntity) authenticate.getPrincipal();

            LoginResponse loginResponse = new LoginResponse(tokenService.generateToken(funcionarioEntity));

            return loginResponse;

        } catch (BadCredentialsException ex) {
            throw new BadRequestException("Usuário ou senha inválidos! ");
        } catch (Exception ex) {
            throw new RuntimeException("Erro inesperado! ");
        }

    }
}
