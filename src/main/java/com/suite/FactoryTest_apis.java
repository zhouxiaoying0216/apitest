package com.suite;

import com.apis.add.AddUserTest;
import com.apis.edit.EditUserTest;
import com.apis.find.FindUserByIdTest;
import com.apis.find.FindUserTest;
import com.apis.remove.RemoveUserByIdTest;
import com.apis.remove.RemoveUserTest;
import org.testng.annotations.Factory;

/**
 * @author difeng
 */
public class FactoryTest_apis {

    /**
     * @Factory 可以批量运行“测试类”
     */
    @Factory
    public Object[] factoryMethod() {
        return new Object[]{
                new AddUserTest(),
                new EditUserTest(),
                new FindUserByIdTest(),
                new FindUserTest(),
                new RemoveUserByIdTest(),
                new RemoveUserTest(),
        };
    }

    /**
     * 可以添加多个 @Factory
     */
//    @Factory
    public Object[] factoryMethod1() {
        return new Object[]{
                new AddUserTest(),
                new EditUserTest(),
                new FindUserTest(),
        };
    }
}
