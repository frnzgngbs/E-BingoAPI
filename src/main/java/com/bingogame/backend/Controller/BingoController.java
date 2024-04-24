    package com.bingogame.backend.Controller;

    import com.bingogame.backend.Object.BingoResponse;
    import com.bingogame.backend.Service.BingoService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;


    @RestController
    @RequestMapping("/api")
    public class BingoController {

        private final BingoService bingoService;

        @Autowired
        public BingoController(BingoService bingoService) {
            this.bingoService = bingoService;
        }

        @GetMapping("/getCards")
        public ResponseEntity<BingoResponse> getCards(@RequestParam String lobby_code) {
            BingoResponse bingoResponse = bingoService.getCards(lobby_code);
            if (bingoResponse != null) {
                return ResponseEntity.ok(bingoResponse);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("/winningCard")
        public ResponseEntity<String> winningCard(@RequestParam String player_code) {
            ResponseEntity<String> response = bingoService.checkWinningCard(player_code);
            return response;
        }
    }
