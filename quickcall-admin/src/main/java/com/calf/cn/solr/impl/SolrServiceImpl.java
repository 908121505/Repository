
package com.calf.cn.solr.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.calf.cn.solr.SolrService;
import com.calf.cn.utils.SolrUtils;


@Service("solrService")
public class SolrServiceImpl implements SolrService {
	private final static Logger logger = LoggerFactory.getLogger(SolrServiceImpl.class);
	
	 /**  
     * 简单查询  
     * @param queryStr 
     * @param sortfield 
     * @param sortFlag 
     * @param rows 
     * @return SolrDocumentList  
     */  
	@Override
    public SolrDocumentList query(String queryStr,String[] sortfield,String[] sortFlag,Integer rows) {  
  
        try {  
            HttpSolrClient httpSolrClient = SolrUtils.connect();  
            SolrQuery query = new SolrQuery();  
            //设定查询字段  
            query.setQuery(StringUtils.isNotBlank(queryStr)?queryStr:"*:*");  
            //设定返回记录数
            if(rows>0){
               query.setRows(rows);  
            }
            // 设置排序  
            if(sortfield.length > 0){
	            for (int i = 0; i < sortfield.length; i++) {  
	                if (sortFlag[i].toLowerCase().equals("asc")) {  
	                   query.addSort(sortfield[i], SolrQuery.ORDER.asc);  
	                } else {  
	                    query.addSort(sortfield[i], SolrQuery.ORDER.desc);  
	                }  
	            }  
            }
            QueryResponse response = httpSolrClient.query(query);  
            SolrDocumentList list = response.getResults();  
            return  list;  
        } catch (SolrServerException e) { 
        	logger.error("solr query is error!!!");
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    /**  
     * 分页查询  
     * @param queryStr  
     * @param start  
     * @param rows   
     * @param queryStr 
     * @param sortfield 
     * @param sortFlag 
     * @return SolrDocumentList  
     */  
    @Override
    public SolrDocumentList queryPage(String queryStr,Integer start,Integer rows ,String[] sortfield,String[] sortFlag){  
        try {  
            HttpSolrClient httpSolrClient = SolrUtils.connect();  
            SolrQuery query = new SolrQuery();  
            //设定查询字段  
            query.setQuery(StringUtils.isNotBlank(queryStr)?queryStr:"*:*");  
            //指定返回结果字段  
            query.setIncludeScore(true);  
            //指定结果集返回的字段
            // query.set("fl","id,productName");  
            //覆盖schema.xml的defaultOperator（有空格时用"AND"还是用"OR"操作逻辑），一般默认指定。必须大写  
           // query.set("q.op","AND");  
            //分页开始数   1代表从第1条记录开始非第1页，索引从0开始
            query.setStart(start);  
            //设定返回记录数
            query.setRows(rows);  
            //设置排序  
            for (int i = 0; i < sortfield.length; i++) {  
                if (sortFlag[i].toLowerCase().equals("asc")) {  
                   query.addSort(sortfield[i], SolrQuery.ORDER.asc);  
                } else {  
                    query.addSort(sortfield[i], SolrQuery.ORDER.desc);  
                }  
            }  
            QueryResponse response = httpSolrClient.query(query);  
           //获取bean  
           //  List<Object> bean = response.getBeans(Object.class);  
            SolrDocumentList list = response.getResults();  
            return  list;  
        } catch (SolrServerException e) {  
        	logger.error("solr queryPage is error!!!");
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return null;  
  
    }  
  
    /**  
     * 添加一个实体  
     *  
     * @param object  
     */  
//    public void addBean(Object object) {  
//        try {  
//            HttpSolrClient httpSolrClient = SolrUtils.connect();  
//            httpSolrClient.addBean(object);  
//            httpSolrClient.commit();  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        } catch (SolrServerException e) {  
//        	logger.error("solr addBean is error!!!");
//            e.printStackTrace();  
//        }  
//  
//    }  
  
    /**  
     * 添加记录到索引库  
     *  
     * @param map  
     */  
    @Override
    public void addOrUpdateDoc(Map<String, Object> map) {  
        try { 
        	logger.info(">>>>>>>>>>>>>>addOrUpdateDoc参数："+map.toString());
            HttpSolrClient httpSolrClient = SolrUtils.connect();  
            SolrInputDocument document = new SolrInputDocument();  
            document = SolrUtils.addFileds(map,document);  
            UpdateResponse response = httpSolrClient.add(document);  
            logger.info("addOrUpdateDoc response="+response);
            httpSolrClient.commit();  
        } catch (SolrServerException e) {  
        	logger.error("solr addDoc is error!!!");
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    /**  
     * 删除索引  
     *  
     * @param id  
     */  
    @Override
    public void deleteById(String id) {  
        try {  
            HttpSolrClient httpSolrClient = SolrUtils.connect();  
            httpSolrClient.deleteById(id);  
            httpSolrClient.commit();  
        } catch (SolrServerException e) {  
        	logger.error("solr deleteById is error!!!");
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
    }
    /**  
     * 删除索引  
     *  
     * @param id  
     */  
    @Override
    public void deleteByQuery(String query) {  
    	try {  
    		HttpSolrClient httpSolrClient = SolrUtils.connect();  
    		httpSolrClient.deleteByQuery(query);
    		httpSolrClient.commit();  
    	} catch (SolrServerException e) {  
    		logger.error("solr deleteByQuery is error!!!");
    		e.printStackTrace();  
    	} catch (IOException e) {  
    		e.printStackTrace();  
    	}  
    	
    }



}
