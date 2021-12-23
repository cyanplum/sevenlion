package cn.sevenlion.common.mapstruct.convert;

import cn.sevenlion.common.mapstruct.model.bo.BasicBo;
import cn.sevenlion.common.mapstruct.model.entity.BasicEntity;
import cn.sevenlion.common.mapstruct.model.vo.BasicVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author: qimeiwen
 * @create: 2021-11-18
 */
public interface BasicConvert<Entity extends BasicEntity, Bo extends BasicBo, Vo extends BasicVo> {

    public Entity bo2Entity(Bo bo);
    public List<Entity> bo2Entity(List<Bo> boList);
    public Vo entity2Vo(Entity entity);
    public List<Vo> entity2Vo(List<Entity> entityList);
    Page<Vo> entityPage2Vo(IPage<Entity> page);

}
