/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.strategy;

/**
 * @author HaoBin
 * @version $Id: Role.java, v0.1 2018/6/13 8:51 HaoBin
 */
public abstract class Role {

    protected String name;

    protected IDefendBehavior defendBehavior;
    protected IDisplayBehavior displayBehavior;
    protected IRunBehavior runBehavior;
    protected IAttackBeHavior attackBehavior;

    public Role setDefendBehavior(IDefendBehavior defendBehavior)
    {
        this.defendBehavior = defendBehavior;
        return this;
    }

    public Role setDisplayBehavior(IDisplayBehavior displayBehavior)
    {
        this.displayBehavior = displayBehavior;
        return this;
    }

    public Role setRunBehavior(IRunBehavior runBehavior)
    {
        this.runBehavior = runBehavior;
        return this;
    }

    public Role setAttackBehavior(IAttackBeHavior attackBehavior)
    {
        this.attackBehavior = attackBehavior;
        return this;
    }

    protected void display()
    {
        displayBehavior.display();
    }

    protected void run()
    {
        runBehavior.run();
    }

    protected void attack()
    {
        attackBehavior.attack();
    }

    protected void defend()
    {
        defendBehavior.defend();
    }
}