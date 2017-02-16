/**
 * @author: qiusheng.wu
 * @version: 1.0
 * @since 2017-01-19
 */
package com.elven.demo.springboot1.test.grid.service.impl;
import com.elven.demo.springboot1.test.grid.dao.AnkInfoDao;
import com.elven.demo.springboot1.test.grid.domain.AnkInfo;
import com.elven.demo.springboot1.test.grid.service.AnkInfoService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

@Service("ankInfoService")
public class AnkInfoServiceImpl   implements AnkInfoService {
    @Resource(name="ankInfoDao")
   private AnkInfoDao ankInfoDao;

	@Transactional
   public void saveAnkInfo (AnkInfo ankInfo){
 		ankInfoDao.save(ankInfo);
   }

	@Transactional
   public void deleteAnkInfo (AnkInfo ankInfo){
   		ankInfoDao.delete(ankInfo);
   }
}