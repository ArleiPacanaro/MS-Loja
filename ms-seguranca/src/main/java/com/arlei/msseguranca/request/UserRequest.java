package com.arlei.msseguranca.request;

// Para criar o usuário

import com.arlei.msseguranca.entity.enumerator.UserRole;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(@NotBlank String login, @NotBlank  String password,UserRole role) {
}