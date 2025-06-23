package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerDto> getAllPlayers(boolean isAdmin) {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(player -> convertToDto(player, isAdmin))
                .collect(Collectors.toList());
    }

    public Optional<PlayerDto> getPlayerById(String id, boolean isAdmin) {
        Optional<Player> player = playerRepository.findById(Integer.valueOf(id));
        return player.map(p -> convertToDto(p, isAdmin));
    }

    public Integer getAge(Player player) {
        if (player.getBirthYear() == null || player.getBirthYear().isEmpty()) {
            return null;
        }
        int birthYear = Integer.parseInt(player.getBirthYear());
        int birthMonth = player.getBirthMonth() != null && !player.getBirthMonth().isEmpty()
                ? Integer.parseInt(player.getBirthMonth()) : 1;
        int birthDay = player.getBirthDay() != null && !player.getBirthDay().isEmpty()
                ? Integer.parseInt(player.getBirthDay()) : 1;

        LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
        LocalDate deathDate;
        if (player.getDeathYear() != null && !player.getDeathYear().isEmpty()) {
            int deathYear = Integer.parseInt(player.getDeathYear());
            int deathMonth = player.getDeathMonth() != null && !player.getDeathMonth().isEmpty()
                    ? Integer.parseInt(player.getDeathMonth()) : 1;
            int deathDay = player.getDeathDay() != null && !player.getDeathDay().isEmpty()
                    ? Integer.parseInt(player.getDeathDay()) : 1;

            deathDate = LocalDate.of(deathYear, deathMonth, deathDay);
        } else {
            deathDate = LocalDate.now();
        }
        return Period.between(birthDate, deathDate).getYears();
    }

    private PlayerDto convertToDto(Player player, boolean isAdmin) {
        Integer age = getAge(player);

        if (isAdmin) {
            return new PlayerDto(player.getId(), player.getFirstName(),
                    player.getLastName(), age);
        } else {
            return new PlayerDto(player.getId(), player.getFirstName(), age);
        }
    }
}
