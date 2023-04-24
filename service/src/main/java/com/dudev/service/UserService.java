package com.dudev.service;

import com.dudev.dto.UserCreateEditDto;
import com.dudev.dto.UserFilter;
import com.dudev.dto.UserReadDto;
import com.dudev.entity.User;
import com.dudev.mapper.UserCreateEditMapper;
import com.dudev.mapper.UserReadMapper;
import com.dudev.repository.QPredicate;
import com.dudev.repository.UserRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.dudev.entity.QUser.user;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;
    private final ImageService imageService;

    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
        Predicate predicate = QPredicate.builder()
                .add(filter.username(), user.username::containsIgnoreCase)
                .add(filter.fullName(), user.fullName::containsIgnoreCase)
                .buildAnd();

        return userRepository.findAll(predicate, pageable)
                .map(userReadMapper::map);
    }

    public Optional<byte[]> findAvatar(Integer id) {
        return userRepository.findById(id)
                .map(User::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
    }

    public List<UserReadDto> findAll(UserFilter userFilter) {
        return userRepository.findAll(userFilter)
                .stream()
                .map(userReadMapper::map)
                .toList();
    }

    public List<UserReadDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findById(Integer id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userCreateEditDto) {
        return Optional.of(userCreateEditDto)
                .map(dto -> {
                    uploadImage(dto.getImage());
                    return userCreateEditMapper.map(dto);
                })
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @SneakyThrows
    private void uploadImage(MultipartFile file) {
        if (!file.isEmpty()) {
            imageService.upload(file.getOriginalFilename(), file.getInputStream());
        }
    }

    @Transactional
    public Optional<UserReadDto> update(Integer id, UserCreateEditDto userCreateEditDto) {
        return userRepository.findById(id)
                .map(entity ->  {
                    uploadImage(userCreateEditDto.getImage());
                    return userCreateEditMapper.map(userCreateEditDto, entity);
                })
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user" + username));
    }
}
