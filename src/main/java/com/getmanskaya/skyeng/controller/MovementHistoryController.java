package com.getmanskaya.skyeng.controller;

import com.getmanskaya.skyeng.entity.MovementHistory;
import com.getmanskaya.skyeng.service.MovementHistoryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movement-histories")
@Api(value = "Movement History REST API")
@SwaggerDefinition(tags = {
        @Tag(name = "Movement History Controller", description = "Operations with Movement History")
})
public class MovementHistoryController {
    @Autowired
    private MovementHistoryService movementHistoryService;

    @PostMapping()
    @ApiOperation(value = "Creating a History entry")
    @ApiResponses(value = {

            @ApiResponse(code = 201, message = "Movement history id"),
            @ApiResponse(code = 400, message = "Movement history not created")
    })
    public ResponseEntity<String> createHistory(@RequestBody MovementHistory movementHistory) {
        try {
            movementHistoryService.save(movementHistory);
            return ResponseEntity.status(HttpStatus.CREATED).body("Movement history id: " + movementHistory.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movement history not created");
        }

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get History of Movement of Postal Item")
    @ApiImplicitParam(name = "id", value = "Postal Item ID")
    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "Mailing history records"),
            @ApiResponse(code = 400, message = "Request failed")
    })
    public ResponseEntity<String> getHistoryByIdPostalItem(@PathVariable("id") long id) {
        try {
            List<MovementHistory> movementHistories = movementHistoryService.getByPostalItem(id);
            return ResponseEntity.ok(movementHistories.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request failed");
        }
    }
}
