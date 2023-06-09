package com.education.service.filepool.impl;

import com.education.entity.FilePool;
import com.education.model.dto.FilePoolDto;
import com.education.repository.FilePoolRepository;
import com.education.service.filepool.FilePoolService;
import com.education.util.Mapper.impl.FilePoolMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for entity FilePool
 */
@Service
@AllArgsConstructor
public class FilePoolServiceImpl implements FilePoolService {

    /**
     * Repository
     */
    private final FilePoolRepository repository;

    private final FilePoolMapper mapper;

    /**
     * Add in db method
     *
     * @param filePool FilePool
     * @return FilePool
     */
    @Transactional(rollbackFor = Exception.class)
    public FilePool add(FilePool filePool) {
        repository.save(filePool);
        return filePool;
    }

    /**
     * findById in db
     *
     * @param id Long
     * @return FilePoolDto
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public FilePoolDto findById(Long id) {
        FilePool filePool = repository.findById(id).orElse(null);
        return filePool != null ?  mapper.toDto(filePool) : null;
    }

    /**
     * findAllById in db
     *
     * @param ids List<Long>
     * @return List<FilePoolDto>
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<FilePoolDto> findAllById(List<Long> ids) {
        return repository
                .findAllById(ids).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Move to archive in db
     *
     * @param id Long
     */
    @Transactional(rollbackFor = Exception.class)
    public void moveToArchive(Long id) {
        repository.moveToArchive(id);
    }

    /**
     * Предоставляет не заархивированное FilePoolDto номенклатуры из БД по id
     *
     * @param id Long
     * @return FilePoolDto
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public FilePoolDto findByIdNotArchived(Long id) {
        FilePool filePool = repository.findByIdNotArchived(id).orElse(null);
        return filePool != null
                ? mapper.toDto(filePool)
                : null;
    }

    /**
     * Предоставляет список не заархивированных FilePoolDto номенклатур из БД по id
     *
     * @param list List of id
     * @return List of FilePoolDto
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<FilePoolDto> findAllByIdNotArchived(Iterable<Long> list) {
        List<FilePool> filePools = repository.findAllByIdNotArchived(list);

        return filePools != null
                ? filePools.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList())
                : null;
    }


}
