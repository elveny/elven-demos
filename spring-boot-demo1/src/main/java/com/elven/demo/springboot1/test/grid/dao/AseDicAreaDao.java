/**
 * @author: qiusheng.wu
 * @version: 1.0
 * @since 2017-01-19
 */
package com.elven.demo.springboot1.test.grid.dao;

import com.elven.demo.springboot1.test.grid.domain.AseDicArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AseDicAreaDao extends JpaRepository<AseDicArea,
 Long
   > {


}

