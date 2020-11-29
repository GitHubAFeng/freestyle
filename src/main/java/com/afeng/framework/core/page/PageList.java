package com.afeng.framework.core.page;



import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author 付为地
 *  分页结果集
 */
public class PageList<T> implements Serializable {
   
	private static final long serialVersionUID = -7384093719998985285L;
	private PageInfo pageInfo;
	private List<T> list;
    private String stat;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

	public List<T> getList() {
        return list;
    }

    public  void setList(List<T> list) {
        this.list = list;
    }

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

}
