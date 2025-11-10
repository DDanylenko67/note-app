package dev.ddanylenko.noteapp.config;

import java.time.LocalDateTime;

public record ErrorResponseDto(String message, String detailMessage, LocalDateTime errorTime) {
}
