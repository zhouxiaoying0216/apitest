package com.env;

/**
 * 测试环境：
 * 由 TestUrl 测试地址，来继承 Env_test 类（或其他环境），
 * 来实现多个环境（ip + port）的切换。
 * 如：测试环境1，测试环境2，研发环境，生产环境等。
 *
 * @author difeng
 */
public class Env_test {

    public static final String testUrl = "http://localhost:1111/";

    public static final String testKey = "key.key.key.key.key";

}
