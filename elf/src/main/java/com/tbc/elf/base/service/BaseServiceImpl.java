package com.tbc.elf.base.service;

import com.tbc.elf.base.util.ReflectUtil;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 这个基础类，抽象了Service接口的一些通常实现，简化普通服务类的开发。
 * <p/>
 *
 * @author ELF@TEAM
 * @since 2016年2月24日11:13:27
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Resource
    protected HibernateBaseService baseService;

    protected final Class<T> modelClass;

    public BaseServiceImpl() {
        modelClass = ReflectUtil.getGenericParamClass(this.getClass());
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public String save(T model) {
        return baseService.save(model);
    }

    @Override
    @Transactional(readOnly = true)
    public T load(String modelId) {
        return baseService.load(modelClass, modelId);
    }

    @Override
    @Transactional(readOnly = true)
    public T get(String modelId) {
        return baseService.get(modelClass, modelId);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void delete(String modelId) {
        baseService.delete(modelClass, modelId);
    }

    @Override
    public String update(T model) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> listByHQL(String hql, Object[] value) {
        return baseService.find(hql, value);
    }
}
