package cn.sevenlion.common.mapstruct.controller;


import cn.sevenlion.common.mapstruct.model.bo.BasicBo;
import cn.sevenlion.common.response.model.CommonResult;
import cn.sevenlion.common.response.model.CommonResultPage;
import cn.sevenlion.common.mapstruct.convert.BasicConvert;

import cn.sevenlion.common.mapstruct.model.query.BasicQueryModel;
import cn.sevenlion.common.mapstruct.model.vo.BasicVo;
import cn.sevenlion.common.mapstruct.service.BasicService;
import cn.sevenlion.common.mapstruct.validated.BasicValid;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: qimeiwen
 * @create: 2021-09-30
 */
public abstract class BasicController<Bo extends BasicBo, Vo extends BasicVo, QueryModel extends BasicQueryModel> {

    public abstract BasicService getService();

    public abstract BasicConvert getConvert();


    @ApiOperation("查询列表")
    @GetMapping
    public CommonResultPage<Vo> index(QueryModel queryModel) {
        IPage<Vo> page = getConvert().entityPage2Vo(getService().selectPage(new Page(queryModel.getPn(), queryModel.getPageSize()), queryModel.queryWrapper()));
        return CommonResultPage.success(page);
    }
    @ApiOperation("根据主键查询详情")
    @GetMapping("/{serialCode}")
    public CommonResult<Vo> show(@PathVariable String serialCode) {
        return (CommonResult<Vo>) CommonResult.success(getConvert().entity2Vo(getService().selectById(serialCode)));
    }
    @ApiOperation("新增")
    @PostMapping
    public CommonResult create(@Validated(BasicValid.Create.class) @RequestBody Bo bo) {
        getService().insert(getConvert().bo2Entity(bo));
        return CommonResult.success();
    }
    @ApiOperation("根据主键修改修改")
    @PutMapping("/{serialCode:.+}")
    public CommonResult update(@PathVariable String serialCode, @Validated(BasicValid.Update.class) @RequestBody Bo bo) {
        getService().updateById(serialCode, getConvert().bo2Entity(bo));
        return CommonResult.success();
    }
    @ApiOperation("根据主键删除")
    @DeleteMapping("/{serialCode:.+}")
    public CommonResult delete(@PathVariable String serialCode) {
        getService().deleteById(serialCode);
        return CommonResult.success();
    }
}
