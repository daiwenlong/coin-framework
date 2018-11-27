package com.me.coin.framework.dao;

/**
 * 分页
 * @author dwl
 *
 */
public class Pager {
	
	/**
	 * 当前页数
	 */
	private int pageNum = 1;     
	/**
	 * 页面条数
	 */
	private int pageSize = 10;      
	/**
	 * 数据总数
	 */
	private long totalRecord;
	/**
	 * 页面总数
	 */
	private long totalPage;
	
	public Pager() {}
	
	
	
	public Pager(int pageNum, int pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize:(totalRecord / pageSize) + 1;
	}
	public long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	} 
	
	
	
	

}
