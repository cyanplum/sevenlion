package cn.sevenlion.common.mapstruct.service.impl;

import cn.sevenlion.common.mapstruct.model.entity.BasicEntity;
import cn.sevenlion.common.mapstruct.service.BasicService;
import cn.sevenlion.common.utils.BasicUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: qimeiwen
 * @create: 2021-09-30
 */
public abstract class BasicServiceImpl<Entity extends BasicEntity, Mapper extends BaseMapper<Entity>> extends ServiceImpl<Mapper, Entity> implements BasicService<Entity> {

    @Autowired
    Mapper mapper;

    @Override
    public Entity selectById(Serializable serialCode) {
        return mapper.selectById(serialCode);
    }

    @Override
    public IPage<Entity> selectPage(IPage<Entity> page, Wrapper<Entity> wrapper) {
        return mapper.selectPage(page, wrapper);
    }

    @Override
    public int insert(Entity entity) {
        entity.setCreateTime(LocalDateTime.now());
        entity.setSerialCode(BasicUtils.getSerialCode());
        return mapper.insert(entity);
    }

    @Override
    public int updateById(String serialCode,Entity entity) {
        entity.setSerialCode(serialCode);
        entity.setUpdateTime(LocalDateTime.now());
        return mapper.updateById(entity);
    }

    @Override
    public int deleteById(Serializable serializable) {
        return mapper.deleteById(serializable);
    }
}
