package cn.mycz.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 木已成舟
 * @date 2020/2/4
 */
@Data
public class PaginationDto {
    private List<QuestionDto> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();

    /**
     * 分页方法
     * @param totalCount
     * @param page
     * @param size
     */
    public void setPagination(Integer totalCount, Integer page, Integer size) {
        pages.clear();

        int totalPages = (int) Math.ceil((double)totalCount / size);

        if (page < 1 ) {
            page = 1;
        } else if (page > totalPages) {
            page = totalCount;
        }

        this.page = page;

        //是否展示上一页
        showPrevious = (page != 1);

        //是否展示下一页
        showNext = (page != totalPages);

        //是否展示第一页
        showFirstPage = !pages.contains(1);

        //是否展示最后一页
        showEndPage = pages.contains(totalPages);

        //如果总页数小于等于7
        if (totalPages <= 7) {
            for (int i = 1; i <= totalPages; i++)
                pages.add(i);
        } else {
            if (page <= 4) {
                for (int i = 1; i <= 7; i++)
                    pages.add(i);
            }  else if (page + 3 >= totalPages) {
                for (int i = totalPages - 7 + 1; i <= totalPages; i++)
                    pages.add(i);
            } else {
                for (int i = page - 3; i <= page + 3; i++)
                    pages.add(i);
            }
        }
        System.out.println(pages);
    }
}
