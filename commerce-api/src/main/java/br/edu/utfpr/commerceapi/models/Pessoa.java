package br.edu.utfpr.commerceapi.models;

import java.time.LocalDate;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//poderia ter usado @Data

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name="tb_pessoa")
public class Pessoa extends BaseEntity implements UserDetails{
    
    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @Column(name = "cpf")
    private String cpf;
    
    @Column(name = "telefone", length = 20, nullable = false)
    private String telefone;

    @Column(name = "dataAniversario", nullable = true)
    private LocalDate dataAniversario;

    @Column(name = "identificacao", length = 20, nullable = false)
    private String identificacao;

    @Column(name = "senha", nullable = false)
    @JsonIgnore
    private String senha;

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.senha;
    }
}
