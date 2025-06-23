package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDto>> getAllPlayers(
            @RequestParam(value = "isAdmin", defaultValue = "false") boolean isAdmin) {
        List<PlayerDto> players = playerService.getAllPlayers(isAdmin);
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getPlayerById(
            @PathVariable String id,
            @RequestParam(value = "isAdmin", defaultValue = "false") boolean isAdmin) {
        Optional<PlayerDto> player = playerService.getPlayerById(id, isAdmin);
        return player.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
