package com.education.service.filepool.impl;

import com.education.entity.FilePool;
import com.education.model.dto.FilePoolDto;
import com.education.repository.FilePoolRepository;
import com.education.service.filepool.FilePoolService;
import com.education.util.DtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for entity FilePool
 */
@Service
@AllArgsConstructor
public class FilePoolServiceImpl implements FilePoolService {

    /**
     * Repository
     */
    private final FilePoolRepository FILE_POOL_REPOSITORY;

    /**
     * Add in db method
     *
     * @param filePool FilePoolDto
     * @return FilePoolDto
     */
    @Transactional(rollbackFor = Exception.class)
    public FilePoolDto add(FilePoolDto filePool) {
        FILE_POOL_REPOSITORY.save(DtoConverter.convertFromDto(filePool));
        return filePool;
    }

    /**
     * findById in db
     *
     * @param id Long
     * @return FilePoolDto
     */
    @Transactional(rollbackFor = Exception.class)
    public FilePoolDto findById(Long id) {
        FilePool filePool = FILE_POOL_REPOSITORY.findById(id).orElse(null);
        return filePool != null ? DtoConverter.convertToDto(filePool) : null;
    }

    /**
     * findAllById in db
     *
     * @param ids List<Long>
     * @return List<FilePoolDto>
     */
    @Transactional(rollbackFor = Exception.class)
    public List<FilePool> findAllById(List<Long> ids) {
        return FILE_POOL_REPOSITORY.findAllById(ids);
    }

    /**
     * Move to archive in db
     *
     * @param id Long
     */
    @Transactional(rollbackFor = Exception.class)
    public void moveToArchive(Long id) {
        FILE_POOL_REPOSITORY.moveToArchive(id);
    }


}
