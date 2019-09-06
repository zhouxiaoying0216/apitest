package com.url;

import com.env.Env_test;

/**
 * TestUrl 类中的 testUrl 值，继承自 Env_test 类，
 * 通过继承 extends Env_xxx 来变更不同的环境，实现对多套环境的测试工作。
 *
 * @author difeng
 */
public class TestUrl extends Env_test {

    // 新增用户信息 addUser
    public static final String addUser_url = testUrl + "v2/addUser";

    // 编辑用户信息 editUser
    public static final String editUser_url = testUrl + "v2/editUser";

    // 查找用户信息 findUser - 可以根据任意字段查找
    public static final String findUser_url = testUrl + "v2/findUser";

    // 查找用户信息 findUserById - 只能根据 id 查找
    public static final String findUserById_url = testUrl + "v2/findUserById";

    // 查询用户接口 - sqlSessionTemplate.selectList - 返回 json 格式字符串
    public static final String findUser_JSONArray_toJSONString_url = testUrl + "v2/findUser_JSONArray_toJSONString";

    // 查询用户接口 - sqlSessionTemplate.selectOne - 返回 json 格式字符串
    public static final String findUser_JSON_toJSONString_url = testUrl + "v2/findUser_JSON_toJSONString";

    // 删除用户信息 removeUser - 可以根据任意字段删除
    public static final String removeUser_url = testUrl + "v2/removeUser";

    // 删除用户信息 removeUserById - 只能根据 id 删除
    public static final String removeUserById_url = testUrl + "v2/removeUserById";

}
