/**
 * @author: qiusheng.wu
 * @version: 1.0
 * @since 2017-01-19
 */
package com.elven.demo.springboot1.test.grid.service.impl;

import com.elven.demo.springboot1.test.grid.dao.AseDicAreaDao;
import com.elven.demo.springboot1.test.grid.domain.AseDicArea;
import com.elven.demo.springboot1.test.grid.service.AseDicAreaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("aseDicAreaService")
public class AseDicAreaServiceImpl   implements AseDicAreaService {
    @Resource(name="aseDicAreaDao")
   private AseDicAreaDao aseDicAreaDao;

	@Transactional
   public void saveAseDicArea (AseDicArea aseDicArea){
 		aseDicAreaDao.save(aseDicArea);
   }

	@Transactional
   public void deleteAseDicArea (AseDicArea aseDicArea){
   		aseDicAreaDao.delete(aseDicArea);
   }
}