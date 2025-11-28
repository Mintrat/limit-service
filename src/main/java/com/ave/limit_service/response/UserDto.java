package com.ave.limit_service.response;

public record UserDto(String userId, double currentLimit, double reservedAmount) {
}
