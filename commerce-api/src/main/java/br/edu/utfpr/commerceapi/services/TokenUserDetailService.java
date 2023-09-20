package br.edu.utfpr.commerceapi.services;

import br.edu.utfpr.commerceapi.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class TokenUserDetailService implements UserDetailsService {

  @Autowired
  private PessoaRepository personService;

  @Override
  public UserDetails loadUserByUsername(String email)
    throws UsernameNotFoundException {
    return personService
      .findByEmail(email)
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
