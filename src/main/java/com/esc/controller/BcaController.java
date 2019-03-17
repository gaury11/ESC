package com.esc.controller;

import com.esc.dto.BcaReqDto;
import com.esc.dto.BcaResDto;
import com.esc.service.BcaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("bca")
public class BcaController {

    private final BcaService bcaService;

    public BcaController(BcaService bcaService){
        this.bcaService = bcaService;
    }

    @GetMapping("")
    public ResponseEntity<List<BcaResDto>> getBcaList(){

        ResponseEntity<List<BcaResDto>> response = null;

        try{
            response = new ResponseEntity<>(bcaService.findAllDesc(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return response;
    }


    @PostMapping("/save")
    public Long saveBca(@RequestBody BcaReqDto dto){
        return bcaService.save(dto);
    }
}
