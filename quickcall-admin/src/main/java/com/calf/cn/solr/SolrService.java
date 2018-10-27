
package com.calf.cn.solr;

import java.util.Map;

import org.apache.solr.common.SolrDocumentList;


public interface SolrService  {
	/**  
     * 简单查询  
     * @param queryStr 
     * @param sortfield 
     * @param sortFlag 
     * @param rows 
     * @return SolrDocumentList  
     */  
	  public SolrDocumentList query(String searchStr,String[] sortfield,String[] sortFlag,Integer rows);
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
	  public SolrDocumentList queryPage(String queryStr,Integer start,Integer rows,String[] sortfield,String[] sortFlag );
	  /**  
	     * 添加记录到索引库  
	     *  
	     * @param map  
	     */  
	  public void addOrUpdateDoc(Map<String, Object> map);
	  /**  
	     * 删除索引  
	     *  
	     * @param id  
	     */ 
	  public void deleteById(String id);
	  /**
	   * 根据查询删除
	   */
	  public void deleteByQuery(String query);
}
