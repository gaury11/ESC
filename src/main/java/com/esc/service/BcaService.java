package com.esc.service;

import com.esc.dto.BcaReqDto;
import com.esc.dto.BcaResDto;
import com.esc.repository.BcaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
public class BcaService {

    private final BcaRepository bcaRepository;

    public BcaService(BcaRepository bcaRepository){
        this.bcaRepository = bcaRepository;
    }

    @Transactional(readOnly = true)
    public List<BcaResDto> findAllDesc(){

        Sort sort = new Sort(Sort.Direction.DESC, "id");

        return bcaRepository.findAll(sort)
                .stream()
                .map(BcaResDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long save(BcaReqDto dto){
        return bcaRepository.save(dto.toEntity()).getId();
    }
}
