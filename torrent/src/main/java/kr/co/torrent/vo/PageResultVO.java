package kr.co.torrent.vo;

public class PageResultVO {
	private int listSize = 10;
	private int tabSize = 10;
	private int pageNo;
	private int count;
	
	private int lastPage;
	private int beginPage;
	private int endPage;
	
	private boolean prev, next;
	
	public PageResultVO(int pageNo, int count){
		this.pageNo = pageNo;
		this.count = count;
		
		this.lastPage = (int)Math.ceil(count / (double)listSize);
		int currentTab = (pageNo - 1) / tabSize + 1;
		this.beginPage = (currentTab - 1) * tabSize + 1;
		this.endPage = (currentTab * tabSize > lastPage) ? lastPage : currentTab * tabSize;
		this.prev = (beginPage != 1);
		this.next = (endPage != lastPage);
	}

	public int getPageNo() {
		return pageNo;
	}
	public int getCount() {
		return count;
	}
	public int getLastPage() {
		return lastPage;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public boolean getPrev() {
		return prev;
	}
	public boolean getNext() {
		return next;
	}
}
