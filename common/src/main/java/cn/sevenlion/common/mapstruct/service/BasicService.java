package cn.sevenlion.common.mapstruct.service;


import cn.sevenlion.common.mapstruct.model.entity.BasicEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


import java.io.Serializable;

/**
 * @author: qimeiwen
 * @create: 2021-09-30
 */
public interface BasicService<Entity extends BasicEntity> extends IService<Entity> {

    Entity selectById(Serializable serialCode);

    IPage<Entity> selectPage(IPage<Entity> page, Wrapper<Entity> wrapper);

    int insert(Entity entity);

    int updateById(String serialCode,Entity entity);

    int deleteById(Serializable serializable);

}
