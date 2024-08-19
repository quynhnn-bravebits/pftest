package org.pftest.listeners;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GroupOrderInterceptor implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        methods.sort(Comparator.comparing(method -> Arrays.stream(method.getMethod().getGroups())
                .sorted()
                .findFirst()
                .orElse("")));
        return methods;
    }
}
