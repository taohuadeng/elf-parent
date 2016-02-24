package com.tbc.elf.base.service;


import java.util.List;
import java.util.Set;

/**
 * 这个借口抽象出一些常用服务的通用方法。可以被其它服务接口集成。
 *
 * @param <T> 要操作的实体类。
 * @author ELF@TEAM
 */
public interface BaseService<T> {
    /**
     * 根据传入的实体是否主键被设置来判断是执行插入（主键未设置）
     * 或者更新操作（主键被调用方设置了）
     *
     * @param model 要保存的实体。
     * @return 保存实体的主键。
     */
    String save(T model);

    /**
     * 该方法用于根据实体主键获取实体类
     * <p>load Hibernate load</p>
     *
     * @param modelId 实体主键
     * @return 对应实体T
     */
    T load(String modelId);

    /**
     * 该方法用于根据实体主键获取实体类
     * <p>load Hibernate get</p>
     *
     * @param modelId 实体主键
     * @return 对应实体T
     */
    T get(String modelId);

    /**
     * 该方法用于根据实体主键删除对应实体类
     *
     * @param modelId 实体主键
     */
    void delete(String modelId);

    /**
     * 根据主键更新当前实体，如果实体主键未设置，则会抛出错误。
     * 未设置的值，会保留数据库原有值，设置的值为被更新成新值。
     *
     * @param model 要更新的实体。
     * @return 实体主键
     */
    String update(T model);

    /**
     * 根据hql获取对应实体集合
     *
     * @param hql   hql语句
     * @param value 查询参数
     * @return 查询实体集合
     */
    List<T> listByHQL(String hql, Object[] value);

//    /**
//     * 根据各个实体的主键更新所有当前实体，如果实体主键未设置，则会抛出错误。
//     * 未设置的值，会保留数据库原有值，设置的值为被更新成新值。
//     *
//     * @param models 要更新的实体。
//     * @return 受影响的行数。。
//     */
//    int update(List<T> models);
//
//    /**
//     * 这个方法用于更新指定实体的指定字段。更新指定的字段。
//     *
//     * @param entities 需要被更新的实体。
//     * @param fields   需要更新的数据库字段。
//     * @return 更新的条数。
//     */
//    int update(List<T> entities, Set<String> fields);
//
//
//    /**
//     * 批量保存实体到数据库，依据实体是否设置了主键来判断是调用Insert还是Update。
//     * 如果设置了主键，则执行更新操作，否则执行插入操作。
//     *
//     * @param entities 需要保存的实体。
//     * @return 所有实体的主键。
//     */
//    List<String> save(List<T> entities);
//
//    /**
//     * 根据主键删除指定的实体。
//     *
//     * @param id 实体的主键
//     * @return 删除的数量。0或者1
//     */
//    int delete(String id);
//
//    /**
//     * 批量删除主键对应的实体记录
//     *
//     * @param ids 要删除的主键ID。
//     * @return 删除的条数。
//     */
//    int delete(List<String> ids);
//
//    /**
//     * 通过主键查询数据库实体对象。
//     *
//     * @param id 实体的主键
//     * @return null或者对应的实体。
//     */
//    T get(String id);
//
//    /**
//     * 通过主键批量查询数据库实体对象。
//     * 如果当前实体存在排序字段showOrder，
//     * 则返回结果会返回按showOrder升序排序后的结果。
//     *
//     * @param ids 实体的主键
//     * @return 输入主键对应的参数列表。
//     */
//    List<T> get(List<String> ids);
//
//    /**
//     * 查询当前公司的所有实体记录
//     * 如果当前实体存在排序字段showOrder，
//     * 则返回结果会返回按showOrder升序排序后的结果。
//     * 注意，这个方法最大返回结果是1000，如果超过一千条记录，
//     * 请自己写查询语句。
//     *
//     * @return 当前公司的所有该实体的数据库记录。
//     */
//    List<T> getAll();
//
//    /**
//     * 查询公司唯一一条记录该实体记录，
//     * 在该实体对应的数据库记录不止一条的情况下，
//     * 如果该实体记录存在排序字段showOrder，则返回按showOrder
//     * 升序排序后的第一条记录。否则返回的记录取决于数据库。
//     *
//     * @return 该数据对应的一条记录，或者为null，没有对应的记录。
//     */
//    T getCorpUnique();
//
//
//    /**
//     * 获取一对一关系的当前实体的数据库记录，
//     * 在该实体对应的数据库记录不止一条的情况下，
//     * 如果该实体记录存在排序字段showOrder，则返回按showOrder
//     * 升序排序后的第一条记录。否则返回的记录取决于数据库。
//     *
//     * @param fieldName 保存的对主题对象的引用（一般为主体对象的Id。）
//     * @param value     关联属性的值，一般为为主字段的值。
//     * @return 对应的实体。
//     */
//    T getOneToOne(String fieldName, Object value);
//
//    /**
//     * 根据某个字段的值，删除当前的实体，如果删除的实体个数不唯一。
//     * Value可以为null。如果Value为Null则删除该字段为空的记录。
//     *
//     * @param fieldName 保存的对主题对象的引用（一般为主体对象的Id。）
//     * @param value     关联属性的值，一般为为主字段的值。
//     */
//    int deleteOneToOne(String fieldName, Object value);
//
//    /**
//     * 获取一对多关系的当前实体的多个数据库记录，
//     * 如果该实体记录存在排序字段showOrder，则返回按showOrder
//     * 升序排序后的记录。否则返回的记录顺序取决于数据库。
//     * 注意，这个方法是同个某个列的值获取特定的实体，
//     * 注意，这个方法最大返回结果是1000，如果超过一千条记录，
//     * 请自己写查询语句。
//     * Value可以为null。如果Value为Null则删除该字段为空的记录。
//     *
//     * @param fieldName 保存的对主题对象的引用（一般为主体对象的Id。）
//     * @param value     关联属性的值，一般为主实体主键的值。
//     * @return 对应的当前数据库记录的列表。
//     */
//    List<T> getOneToMany(String fieldName, Object value);
//
//    /**
//     * 批量删除一对多关系的当前实体的0个或多个数据库记录。
//     * Value可以为null。如果Value为Null则删除该字段为空的记录。
//     *
//     * @param fieldName 保存的对主题对象的引用（一般为主体对象的Id。）
//     * @param value     关联属性的值，一般为主实体主键的值。
//     * @return 实际删除的实体个数。如果该数据在共享中心，删除个数是乘2后的。
//     */
//    int deleteOneToMany(String fieldName, Object value);


}
