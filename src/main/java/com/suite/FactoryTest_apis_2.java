package com.suite;

import com.apis_2.add.AddUserTest_success;
import com.apis_2.edit.EditUserTest_success;
import com.apis_2.find.FindUserByIdTest_success;
import com.apis_2.find.FindUserTest_success;
import com.apis_2.remove.RemoveUserByIdTest_error;
import com.apis_2.remove.RemoveUserByIdTest_success;
import com.apis_2.remove.RemoveUserTest_success;
import org.testng.annotations.Factory;

/**
 * @author difeng
 */
public class FactoryTest_apis_2 {

    /**
     * @Factory 可以批量运行“测试类”
     */
    @Factory
    public Object[] factoryMethod() {
        return new Object[]{
                new AddUserTest_success(),
                new EditUserTest_success(),
                new FindUserByIdTest_success(),
                new FindUserTest_success(),
                new RemoveUserByIdTest_error(),
                new RemoveUserByIdTest_success(),
                new RemoveUserTest_success(),
        };
    }

    /**
     * 可以添加多个 @Factory
     */
//    @Factory
    public Object[] factoryMethod1() {
        return new Object[]{
        };
    }
}
