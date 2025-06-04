package org.dromara.system.service;

import org.dromara.system.domain.vo.PermissionPolicyVo;
import org.dromara.system.domain.bo.PermissionPolicyBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 设备权限策略Service接口
 *
 * @author Lion Li
 * @date 2025-06-04
 */
public interface IPermissionPolicyService {

    /**
     * 查询设备权限策略
     *
     * @param policyId 主键
     * @return 设备权限策略
     */
    PermissionPolicyVo queryById(String policyId);

    /**
     * 分页查询设备权限策略列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备权限策略分页列表
     */
    TableDataInfo<PermissionPolicyVo> queryPageList(PermissionPolicyBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的设备权限策略列表
     *
     * @param bo 查询条件
     * @return 设备权限策略列表
     */
    List<PermissionPolicyVo> queryList(PermissionPolicyBo bo);

    /**
     * 新增设备权限策略
     *
     * @param bo 设备权限策略
     * @return 是否新增成功
     */
    Boolean insertByBo(PermissionPolicyBo bo);

    /**
     * 修改设备权限策略
     *
     * @param bo 设备权限策略
     * @return 是否修改成功
     */
    Boolean updateByBo(PermissionPolicyBo bo);

    /**
     * 校验并批量删除设备权限策略信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
