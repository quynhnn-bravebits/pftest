package org.pftest.annotations;

import org.pftest.enums.Author;
import org.pftest.enums.Category;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CustomAnnotation {

    // This is not a method
    public Author[] author();

    // public String[] category();
    public Category[] category();
}
