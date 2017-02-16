/**
 * @author: qiusheng.wu
 * @version: 1.0
 * @since 2017-01-19
 */

package com.elven.demo.springboot1.test.grid.mvc;

import com.elven.demo.springboot1.common.util.POIUtil;
import com.elven.demo.springboot1.test.grid.dao.AseDicAreaDao;
import com.elven.demo.springboot1.test.grid.domain.AnkInfo;
import com.elven.demo.springboot1.test.grid.domain.AseDicArea;
import com.elven.demo.springboot1.test.grid.service.AnkInfoService;
import com.elven.demo.springboot1.test.grid.service.AseDicAreaService;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/paycore/test/grid")
public class AseDicAreaController {

	private static final Logger logger = Logger.getLogger(AseDicAreaController.class);


	@Resource(name = "aseDicAreaService")
	private AseDicAreaService aseDicAreaService ;

	@Resource(name="aseDicAreaDao")
	private AseDicAreaDao aseDicAreaDao;

	@Resource(name="ankInfoService")
	private AnkInfoService ankInfoService;


	@RequestMapping(value = "/saveAseDicArea" )
	 public  @ResponseBody
    String  saveAseDicArea (AseDicArea aseDicArea){
		aseDicAreaService.saveAseDicArea(aseDicArea );
		return	"1";
	 }


	@RequestMapping(value = "/deleteAseDicArea" )
   public  @ResponseBody
    String  deleteAseDicArea (AseDicArea aseDicArea){
   		aseDicAreaService.deleteAseDicArea(aseDicArea );
		return	"1";
   }

   	@RequestMapping(value = "/aseDicAreaAdd" )
	public  String aseDicAreaAdd(@RequestParam Map reqMap , HttpServletRequest request  ) {

		return "/grid/aseDicAreaAdd";
	}

	@RequestMapping(value = "findAll" )
	public List<AseDicArea> findAll(){
		List<AseDicArea> list = aseDicAreaDao.findAll();

		return list;
	}

	@RequestMapping(value = "testPoi" )
	public String testPoi() throws IOException {
		long t1 = System.nanoTime();

		//Excel文件
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:20170118.xls")));
		//Excel工作表
		HSSFSheet sheet = wb.getSheetAt(0);

		Map<String, List<String[]>> data = POIUtil.parse(ResourceUtils.getFile("classpath:20170118.xlsx"));

		for(String key : data.keySet()){

            if("SQL Results".equals(key)){
                List<String[]> list = data.get(key);
				list.remove(0);
                for(String[] strs : list){

                    int i = 1;
                    String unitedBankNo = strs[i++];
                    String subBankName = strs[i++];
                    String clearBankNo = strs[i++];
                    String areaCode = strs[i++];
                    String areaName = strs[i++];
                    String parentAreaCode = strs[i++];

					String bankName = "";
					if(subBankName.indexOf("银行") != -1){
						bankName = subBankName.substring(0, subBankName.indexOf("银行")+2);
					}


//					AnkInfo ankInfo = new AnkInfo(subBankName, parentAreaCode, areaCode, unitedBankNo, clearBankNo, areaName) ;

					AnkInfo ankInfo = new AnkInfo("", bankName, unitedBankNo, subBankName, parentAreaCode, areaCode, unitedBankNo, clearBankNo, "0", areaName, "1", new Date());

					ankInfoService.saveAnkInfo(ankInfo);

					System.out.println("::::::::::::ankInfo.getId()::"+ankInfo.getId());
                }

            }
            else {
                continue;
            }

        }

		long t2 = System.nanoTime();

		return "SUCCESS::"+(t2-t1)/1000000000.0f +" s";

	}

}



