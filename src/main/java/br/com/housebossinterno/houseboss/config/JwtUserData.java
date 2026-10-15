package br.com.housebossinterno.houseboss.config;

import lombok.Builder;

@Builder
public record JwtUserData (Long id, String nome, String email){
}
