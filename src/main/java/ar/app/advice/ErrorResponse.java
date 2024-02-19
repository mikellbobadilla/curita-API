package ar.app.advice;

import lombok.Builder;

@Builder
public record ErrorResponse(Integer status, String error) {
}
