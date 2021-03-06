package com.leimingtech.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.leimingtech.core.common.ParamsUtils;
import com.leimingtech.core.entity.base.EvaluateGoods;
import com.leimingtech.core.freemarker.BaseFreeMarkerTag;
import com.leimingtech.service.module.trade.service.EvaluateGoodsService;
import com.leimingtech.service.utils.page.Pager;

import freemarker.template.TemplateModelException;

/**
 * 商品评论列表
 * @author cgl
 * 添加时间：2015年08月10日14:27:34
 */
@Component
public class GoodsEvalutebyGoodsIdTag extends BaseFreeMarkerTag {
	 @Resource
	 private EvaluateGoodsService evaluateGoodsService;
		/**
		 * 商品评论列表
		 */
	 @SuppressWarnings("rawtypes")
		protected Object exec(Map params) throws TemplateModelException {
			EvaluateGoods evaluateGoods=new EvaluateGoods();
			// 准备分页pager
			Pager pager = new Pager();
//			pager.setPageSize(2);
			// 页码
			int pageNo = ParamsUtils.getInt(params.get("pageNo"));
			
			// 商品id
			Integer goodsId = ParamsUtils.getInt(params.get("goodsId"));
			
			// 匿名评价 0非匿名 1匿名
		    Integer gevalIsAnonymous = ParamsUtils.getInt(params.get("gevalIsAnonymous"));
			
			//tourl
			String toUrl = ParamsUtils.getString(params.get("toUrl"));
			
			//商家自己登陆
			if(goodsId != 0&&goodsId!=null){
				evaluateGoods.setGevalGoodsId(Integer.valueOf(goodsId));
			}
			
			// 匿名评价
//			if(gevalIsAnonymous !=null){
//				evaluateGoods.setGevalIsAnonymous(gevalIsAnonymous);
//			}
			
			//查分页的list
			if (pageNo != 0) {
				pager.setPageNo(pageNo);
			}
			//查找list
			pager.setCondition(evaluateGoods);
		    List<EvaluateGoods> evaluateGoodsList = evaluateGoodsService.findPageList(pager);
		    int totalRows = evaluateGoodsService.findCount(evaluateGoods);
		    pager.setResult(evaluateGoodsList);
		    pager.setToUrl(toUrl);
		    pager.setTotalRows(totalRows);
			    return pager;
		}

}
