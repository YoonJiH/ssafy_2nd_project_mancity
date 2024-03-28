package com.mancity.social.game.presentation;

import com.mancity.social.game.application.GameService;
import com.mancity.social.game.application.dto.request.*;
import com.mancity.social.game.application.dto.response.GameMainResponseDto;
import com.mancity.social.game.application.dto.response.GameResponseDto;
import com.mancity.social.game.application.dto.response.PlayerDataResponseDto;
import com.mancity.social.game.application.dto.response.TeamDataResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/social/game")
@RequiredArgsConstructor
@Slf4j
public class GameController {

    private final GameService gameService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody GameCreateRequestDto dto) {
        gameService.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/upload")
    public ResponseEntity<GameResponseDto> upload(@RequestPart(value = "files", required = false) List<MultipartFile> files,
                                                  @RequestPart(value = "dto") GameVideoUploadDto dto) {
        GameResponseDto response = gameService.upload(files, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDto> findMatchById(@PathVariable Long id) {
        GameResponseDto response = gameService.findMatchById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // team data
    @GetMapping("/data/team/{id}")
    public ResponseEntity<TeamDataResponseDto> findTeamDataByGameId(@PathVariable Long id) {
        TeamDataResponseDto response = gameService.findTeamDataByGameId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // player data
    @GetMapping("/data/player/{id}")
    public ResponseEntity<PlayerDataResponseDto> findPlayerDataByGameId(@PathVariable Long id) {
        PlayerDataResponseDto response = gameService.findPlayerDataByGameId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/allocated/{id}")
        // 회원별 데이터를 할당 받은 매치 목록
    ResponseEntity<List<GameResponseDto>> findAllocatedByUserId(@PathVariable long id) {
        return new ResponseEntity<>(gameService.findAllByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/participated/{id}") // 회원별 참여 상태인 매치 목록
    ResponseEntity<List<GameResponseDto>> findGamesByParticipantUserId(@PathVariable long id) {
        return new ResponseEntity<>(gameService.findGamesByParticipantUserId(id), HttpStatus.OK);
    }

    @PostMapping("/input")
    public ResponseEntity<?> inputDataFromCalc(@RequestBody GameDataInputDto dto) {
        log.info("CALC SERVER : INPUT DATA {}", dto);
        gameService.inputData(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/allocate")
    public ResponseEntity<?> allocateData(@RequestBody GameDataAllocateDto dto) {
        gameService.allocateData(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/check/{game}/{user}")
    public ResponseEntity<Boolean> checkManager(@PathVariable(name = "game") long gameId,
                                                @PathVariable(name = "user") long userId) {
        return new ResponseEntity<>(gameService.checkManager(CheckManagerDto.of(gameId, userId)), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<GameResponseDto>> findAllGamesByFilters(@RequestParam(required = false) Integer gender,
                                                                       @RequestParam(required = false) Integer region,
                                                                       @RequestParam(required = false) Integer playernumber,
                                                                       @RequestParam(required = false) String level) {
        List<GameResponseDto> dtos = gameService.findAllByFilters(gender, region, playernumber, level);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/main/{id}")
    public ResponseEntity<List<GameMainResponseDto>> findMyGameOver(@PathVariable("id") long id){
        log.info("MAIN PAGE - 분석 완료된 게임 리스트 조회 : {}", id);
        return new ResponseEntity<>(gameService.findMyGameOver(id), HttpStatus.OK);
    }

    @PutMapping("/calcOver/{id}")
    public ResponseEntity<?> calcOver(@PathVariable("id") long id){
        gameService.updateCalcStatus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
