package com.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.ZonedDateTime;
import java.util.UUID;
/**
 * Представляет хранилище файла
 * @author Рамазан Гаджиев
 */

@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "file_pool")
public class FilePool extends BaseEntity{
    /**
     * ID хранилища файла
     */
    @Column(name = "storage_file_id")
    private UUID storageFileId = UUID.randomUUID();
    /**
     * Имя хранилища файла
     */
    @Getter
    @Setter
    @Column(name = "file_name")
    private String name;
    /**
     * Расширение файла
     */
    @Column(name = "extension")
    @Getter
    @Setter
    private String extension;
    /**
     * Размер файла
     */
    @Column(name = "file_size")
    @Getter
    @Setter
    private Integer size;
    /**
     * Количество страниц файла
     */
    @Column(name = "page_count")
    @Getter
    @Setter
    private Integer pageCount;
    /**
     * Дата загрузки файла
     */
    @Column(name = "upload_date")
    @Getter
    @Setter
    private ZonedDateTime uploadDate;
    /**
     * Дата архивирования файла
     */
    @Column(name = "archived_date")
    @Getter
    @Setter
    private ZonedDateTime archivedDate;
    /**
     * Автор файла
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    @Getter
    @Setter
    private Employee creator;

}
