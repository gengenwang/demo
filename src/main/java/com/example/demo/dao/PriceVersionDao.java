package com.example.demo.dao;

import com.example.demo.domain.PriceVersion;
import org.apache.ibatis.annotations.Mapper;

/**
 CREATE TABLE `price_version` (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 `total` decimal(12,2) DEFAULT '0.00' COMMENT '总值',
 `front` decimal(12,2) DEFAULT '0.00' COMMENT '消费前',
 `end` decimal(12,2) DEFAULT '0.00' COMMENT '消费后',
 `version` int(11) DEFAULT '0' COMMENT '并发版本控制',
 PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1268 DEFAULT CHARSET=utf8;
 INSERT INTO `demo`.`price_version`(`id`, `total`, `front`, `end`, `version`) VALUES (1, 100.00, 0.00, 0.00, 0);
 */

@Mapper
public interface PriceVersionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PriceVersion record);

    int insertSelective(PriceVersion record);

    PriceVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PriceVersion record);

    int updateByPrimaryKey(PriceVersion record);

    /**
     * 乐观锁
     *
     * @param record
     * @return
     */
    int updateByVersion(PriceVersion record);
}