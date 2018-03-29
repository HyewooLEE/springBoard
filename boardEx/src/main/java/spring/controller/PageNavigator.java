package spring.controller;

import org.springframework.stereotype.Component;

@Component
public class PageNavigator {
	private int countPerPage;
	private int pagePerGroup;
	
	private int pageNum;
	private int totalRecordCount;
	private int totalPageCount;
	private int curGroup;
	
	private int startPageGroup;
	private int endPageGroup;
	private int startRecord;
	
	public int getCountPerPage() {
		return countPerPage;
	}

	public int getPagePerGroup() {
		return pagePerGroup;
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public int getCurGroup() {
		return curGroup;
	}

	public int getStartPageGroup() {
		return startPageGroup;
	}

	public int getEndPageGroup() {
		return endPageGroup;
	}

	public int getStartRecord() {
		return startRecord;
	}
	public PageNavigator() {}
	public PageNavigator(int countPerPage, int pagePerGroup, int pageNum, int totalRecordCount) {
				
		this.countPerPage = countPerPage;
		this.pagePerGroup = pagePerGroup;
		this.totalRecordCount = totalRecordCount;
		
		totalPageCount = (totalRecordCount + pageNum - 1) / countPerPage;
		
		if(pageNum < 1) pageNum = 1;
		
		if(pageNum > totalPageCount) pageNum = totalPageCount;
		
		curGroup = (pageNum - 1) / pagePerGroup;
		startPageGroup = curGroup * pagePerGroup + 1;
		startPageGroup = startPageGroup < 1 ? 1 : startPageGroup;
		endPageGroup = startPageGroup + pagePerGroup - 1;
		endPageGroup = endPageGroup < totalPageCount ? endPageGroup : totalPageCount;
		startRecord = (pageNum - 1) * countPerPage;
		
		System.out.println("curPage : " + pageNum);
		System.out.println("curGroup : " + curGroup);
		System.out.println("pagePerGroup : "+ pagePerGroup);
		System.out.println("startPageGroup : " + startPageGroup);
		System.out.println("endPageGroup : " + endPageGroup);
		System.out.println("startRecord : " + startRecord);
		
		this.pageNum = pageNum;
		
		
	}
	@Override
	public String toString() {
		return "PageNavigator [countPerPage=" + countPerPage + ", pagePerGroup=" + pagePerGroup + ", pageNum=" + pageNum
				+ ", totalRecordCount=" + totalRecordCount + ", totalPageCount=" + totalPageCount + ", curGroup="
				+ curGroup + ", startPageGroup=" + startPageGroup + ", endPageGroup=" + endPageGroup + ", startRecord="
				+ startRecord + "]";
	}
}
