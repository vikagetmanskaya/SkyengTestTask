package com.getmanskaya.skyeng.controller;

import com.getmanskaya.skyeng.entity.PostalItem;
import com.getmanskaya.skyeng.service.PostalItemService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/postal-items")
@Api(value = "Postal Item REST API")
@SwaggerDefinition(tags = {
        @Tag(name = "Postal Item Controller", description = "Operations with Postal Item")
})
public class PostalItemController {
    @Autowired
    private PostalItemService postalItemService;

    @PostMapping("/{id}")
    @ApiOperation(value = "Register Postal Item")
    @ApiImplicitParam(name = "id", value = "Post Office ID")
    @ApiResponses(value = {

            @ApiResponse(code = 201, message = "Postal item id"),
            @ApiResponse(code = 400, message = "Postal item not created")
    })
    public ResponseEntity<String> registerPostalItem(@RequestBody PostalItem postalItem,
                                                     @PathVariable("id") long id) {
        try {
            postalItemService.save(postalItem, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Postal item id: " + postalItem.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Postal item not created");
        }

    }
}
