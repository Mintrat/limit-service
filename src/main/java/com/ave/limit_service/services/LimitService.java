package com.ave.limit_service.services;

import com.ave.limit_service.configuration.ApplicationConfiguration;
import com.ave.limit_service.entities.UserEntity;
import com.ave.limit_service.exception.LimitExceededException;
import com.ave.limit_service.repositories.UserRepository;
import com.ave.limit_service.requests.WithdrawRequestDto;
import com.ave.limit_service.response.UserDto;
import org.springframework.stereotype.Service;

@Service
public class LimitService {

    private final UserRepository userRepository;

    private final ApplicationConfiguration applicationConfiguration;

    public LimitService(UserRepository userRepository, ApplicationConfiguration applicationConfiguration) {
        this.userRepository = userRepository;
        this.applicationConfiguration = applicationConfiguration;
    }

    public UserDto findUser(String userId) {
        return userRepository
                .findById(userId)
                .map(user -> new UserDto(
                        user.getUserId(),
                        user.getCurrentLimit(),
                        user.getReservedAmount()
                )).orElseGet(() -> createUser(userId));
    }

    public UserDto createUser(String userID) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userID);
        userEntity.setCurrentLimit(applicationConfiguration.getDefaultLimit());
        userEntity.setReservedAmount(0.00);

        userRepository.save(userEntity);

        return new UserDto(userEntity.getUserId(), userEntity.getCurrentLimit(), userEntity.getReservedAmount());
    }

    public void withdraw(WithdrawRequestDto withdrawRequestDto) {
        UserDto userDto = findUser(withdrawRequestDto.userId());

        if (withdrawRequestDto.amount() > userDto.currentLimit()) {
            throw new LimitExceededException();
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(withdrawRequestDto.userId());
        userEntity.setCurrentLimit(userDto.currentLimit() - withdrawRequestDto.amount());
        userEntity.setReservedAmount(userDto.reservedAmount());

        userRepository.save(userEntity);
    }
}
