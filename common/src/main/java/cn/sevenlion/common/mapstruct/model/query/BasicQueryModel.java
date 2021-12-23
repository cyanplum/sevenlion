package cn.sevenlion.common.mapstruct.model.query;

import cn.sevenlion.common.mapstruct.model.entity.BasicEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author: qimeiwen
 * @create: 2021-09-30
 */
public abstract class BasicQueryModel<Entity extends BasicEntity> {

    private Long pn = 1L;

    private Long pageSize = 10L;

    public Long getPn() {
        return pn;
    }

    public void setPn(Long pn) {
        this.pn = pn;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public QueryWrapper<Entity> queryWrapper() {
        return new QueryWrapper<Entity>();
    }
}
