package com.arlei.msseguranca.request;

// Para recuperar o Token.

import jakarta.validation.constraints.NotBlank;

public record UserAuthRequest(@NotBlank String login, @NotBlank  String password) {
}
