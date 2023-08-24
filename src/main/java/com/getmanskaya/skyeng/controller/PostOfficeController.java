package com.getmanskaya.skyeng.controller;

import com.getmanskaya.skyeng.entity.PostOffice;
import com.getmanskaya.skyeng.service.PostOfficeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post-offices")
@Api(value = "Post Office REST API")
@SwaggerDefinition(tags = {
        @Tag(name = "Post Office Controller", description = "Operations with Post Office")
})
public class PostOfficeController {
    @Autowired
    private PostOfficeService postOfficeService;

    @PostMapping()
    @ApiOperation(value = "Create Post Office")
    @ApiResponses(value = {

            @ApiResponse(code = 201, message = "Post office id"),
            @ApiResponse(code = 400, message = "Post office not created")
    })
    public ResponseEntity<String> createPostOffice(@RequestBody PostOffice postOffice) {
        try {
            postOfficeService.save(postOffice);
            return ResponseEntity.status(HttpStatus.CREATED).body("Post office id: " + postOffice.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post office not created");
        }

    }
}
