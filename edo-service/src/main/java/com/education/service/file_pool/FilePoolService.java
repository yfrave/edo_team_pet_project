package com.education.service.file_pool;

import com.education.model.dto.FilePoolDto;

import java.util.List;

public interface FilePoolService {


    /**
     * Add in db method
     *
     * @param filePool FilePoolDto
     * @return FilePoolDto
     */
    FilePoolDto add(FilePoolDto filePool);

    /**
     * findById in db
     *
     * @param id Long
     * @return FilePoolDto
     */
    FilePoolDto findById(Long id);

    /**
     * findAllById in db
     *
     * @param ids List<Long>
     * @return List<FilePoolDto>
     */
    List<FilePoolDto> findAllById(List<Long> ids);

    /**
     * Move to archive in db
     *
     * @param id Long
     */
    void moveToArchive(Long id);

    /**
     * Предоставляет не заархивированное FilePoolDto номенклатуры из БД по id
     *
     * @param id Long
     * @return FilePoolDto
     */
    FilePoolDto findByIdNotArchived(Long id);

    /**
     * Предоставляет список не заархивированных FilePoolDto номенклатур из БД по id
     *
     * @param list List of id
     * @return List of FilePoolDto
     */
    List<FilePoolDto> findAllByIdNotArchived(Iterable<Long> list);

}
