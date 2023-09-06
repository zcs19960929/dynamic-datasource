package com.example.dynamicdatasource.base.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.Page;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description:
 * Create DateTime: 2021/4/9 17:03
 *
 * @author zcs19960929
 */
@Data
@NoArgsConstructor
public class BasePageVO<T> {

    private Long totalElements;

    private Integer pages;

    private Integer number;

    private Integer pageSize;

    private List<T> content;

    private String cursorId;

    public BasePageVO(Page page, List<T> list) {
        if (page != null) {
            this.totalElements = page.getTotal();
            this.content = list;
            this.pages = page.getPages();
            this.number = page.getPageNum();
            this.pageSize = page.getPageSize();
        }
    }

    public BasePageVO(Page page) {
        if (page != null) {
            this.totalElements = page.getTotal();
            this.content = page.getResult();
            this.pages = page.getPages();
            this.number = page.getPageNum();
            this.pageSize = page.getPageSize();
        }
    }

    public BasePageVO(long number, long pageSize, long pages, long totalElements, List<T> content) {
        this.totalElements = totalElements;
        this.pages = Long.valueOf(pages).intValue();
        this.number = Long.valueOf(number).intValue();
        this.pageSize = Long.valueOf(pageSize).intValue();
        this.content = content;
    }


    public BasePageVO(IPage page, List<T> list) {
        if (page != null) {
            this.totalElements = page.getTotal();
            this.content = list;
            this.pages = Long.valueOf(page.getPages()).intValue();
            this.number = Long.valueOf(page.getCurrent()).intValue();
            this.pageSize = Long.valueOf(page.getSize()).intValue();
        }
    }

    public BasePageVO(IPage page) {
        if (page != null) {
            this.totalElements = page.getTotal();
            this.pages = Long.valueOf(page.getPages()).intValue();
            this.number = Long.valueOf(page.getCurrent()).intValue();
            this.pageSize = Long.valueOf(page.getSize()).intValue();
        }
    }

    public BasePageVO(long number, long pageSize, List<T> content, String cursorId) {
        this.number = Long.valueOf(number).intValue();
        this.pageSize = Long.valueOf(pageSize).intValue();
        this.content = content;
        this.cursorId = cursorId;
    }
}
