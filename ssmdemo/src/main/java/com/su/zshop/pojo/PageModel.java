package com.su.zshop.pojo;

public class PageModel {

	//当前页
	private int pageIndex;
	//每页显示多少条记录
	private int pageSize=10;
	//总记录条数
	private int totalRecordSum;
	//总页数
	private int totalPageNum;
	
	public int getPageIndex() {
		//设置pageIndex的值必须在第一页到总页数之间
		this.pageIndex=this.pageIndex<=1?1:this.pageIndex;
		if(getTotalPageNum()!=0)
			this.pageIndex=this.pageIndex>=getTotalPageNum()?getTotalPageNum():this.pageIndex;
		
		return pageIndex;
	}
	
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}

	public int getTotalRecordSum() {
		return totalRecordSum;
	}

	public void setTotalRecordSum(int totalRecordSum) {
		this.totalRecordSum = totalRecordSum;
	}
	public int getTotalPageNum() {
		
		if(getTotalRecordSum()==0)
		{
			return 0;
		}

		//计算获取总页数
		this.totalPageNum=(getTotalRecordSum()+getPageSize()-1)/getPageSize();
		
		return totalPageNum;
	}

	//获取
	public int getStartLimitPos() {
		return (getPageIndex()-1)*getPageSize();
	}
	
}
