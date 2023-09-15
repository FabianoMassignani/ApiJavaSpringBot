package br.edu.utfpr.commerceapi.controllers;

import br.edu.utfpr.commerceapi.dto.AuthDTO;
import br.edu.utfpr.commerceapi.security.JwtUtil;
import jakarta.validation.Valid;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping
  public ResponseEntity<Object> auth(@Valid @RequestBody AuthDTO authDTO) {
    System.out.println(authDTO);

    //dados do usuario (PayLoad)
    var payload = new HashMap<String, Object>();
    payload.put("username", authDTO.username);

    var now = Instant.now();

    var jwt = jwtUtil.generateToken(payload, "ThiagoGay", 3600);

    var res = new HashMap<String, Object>();
    res.put("token", jwt);
    res.put("expiresIn", now.plus(3600, ChronoUnit.SECONDS));

    return ResponseEntity.ok().body(res);
  }
}
