package com.bokecc.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * JWT
 * </p>
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("user_jwt_key")
public class UserJwtKey extends Model<UserJwtKey> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户key
     */
    @TableField("app_key")
    private String appKey;
    /**
     * 密钥
     */
    @TableField("app_secret")
    private String appSecret;
    /**
     * 部门
     */
    @TableField("department")
    private String department;
    /**
     * 服务名
     */
    @TableField("service_name")
    private String serviceName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
